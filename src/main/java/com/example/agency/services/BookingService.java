package com.example.agency.services;

import com.example.agency.domains.requests.CreateBookingRequest;
import com.example.agency.domains.requests.UpdateBookingRequest;
import com.example.agency.domains.responses.CreateBookingResponse;
import com.example.agency.domains.responses.UpdateBookingResponse;
import com.example.agency.entities.Booking;
import com.example.agency.entities.Operator;
import com.example.agency.repositories.BookingRepository;
import com.example.agency.repositories.OperatorRepository;
import com.example.agency.utils.Helper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookingService {
    private static final Logger log = LoggerFactory.getLogger(BookingService.class);
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private OperatorRepository operatorRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private Helper helper;

    public List<CreateBookingResponse> allBookings() {
        List<Booking> allTheBookings = bookingRepository.findAll();

        List<Operator> allOperators = operatorRepository.findAll();
        log.info(allOperators.toString());

        List<CreateBookingResponse> response = new ArrayList<>();
        for(Booking booking: allTheBookings) {
            String operatorName = helper.operatorName(booking);
            if(operatorName == null) {
                log.info("allBookings{}",booking);
                return null;
            }
            response.add(new CreateBookingResponse(
                    booking.getId(), helper.slot(booking.getStartTime(), -1), operatorName));
        }
        log.info(response.toString());

        return response;
    }

    public CreateBookingResponse getBooking(String id) {
        Optional<Booking> booking = bookingRepository.findById(id);
        if(booking.isEmpty()) {
            return null;
        }

        String operatorName = helper.operatorName(booking.get());
        if(operatorName == null) {
            log.info("getBooking{}",booking.get());
            return null;
        }

        return new CreateBookingResponse(
                booking.get().getId(), helper.slot(booking.get().getStartTime(),-1), operatorName);
    }

    public CreateBookingResponse createBooking(CreateBookingRequest request) {
        Integer startTime = request.startTime;

        // start time should be between 0 and 23;
        if(startTime < 0 || startTime > 23 ) {
            return null;
        }
        // start time should be between 0 and 23;

        String operatorName = request.operatorName;

        Optional<Operator> operator = operatorRepository.findOperatorByName(operatorName);
        log.info(operator.toString());
        // operator with the requested name does not exist;
        if(operator.isEmpty()) {
            return null;
        }
        // operator with the requested name does not exist;

        // operator does not have the requested time slot.
        if(operator.get().getBookedTimeSlots().contains(startTime)) {
            return null;
        }
        // operator does not have the requested time slot.

        List<Integer> updatedSlotTimes = operator.get().getBookedTimeSlots();
        updatedSlotTimes.add(startTime);

        Collections.sort(updatedSlotTimes);

        log.info(operator.get().getBookedTimeSlots().toString());

        mongoTemplate.update(Operator.class)
                .matching(Criteria.where("id").is(operator.get().getId()))
                .apply(new Update().set("bookedTimeSlots", updatedSlotTimes))
                .first();
        log.info(operatorRepository.findOperatorByName(operatorName).toString());

        Booking booking = bookingRepository.insert(new Booking(startTime, operator.get().getId()));

        CreateBookingResponse response = new CreateBookingResponse(booking.getId(), helper.slot(startTime,-1), operatorName);
        log.info(response.toString());

        return response;
    }

    public UpdateBookingResponse updateBooking(UpdateBookingRequest request) {
        String bookingId = request.bookingId;
        Optional<Booking> booking = bookingRepository.findById(bookingId);
        // if no booking exists
        if(booking.isEmpty()) {
            return null;
        }
        // if no booking exists

        if(request.newStartTime<0 || request.newStartTime>23) {
            return null;
        }

        Integer previousStartTime = booking.get().getStartTime();

        // if startTime of the booking already matches with the newStartTime
        if(Objects.equals(previousStartTime, request.newStartTime)) {
            return new UpdateBookingResponse(
                    bookingId, "",
                    helper.slot(previousStartTime, -1),
                    helper.operatorName(booking.get()), "NOT CHANGED");
        }
        // if startTime of the booking already matches with the newStartTime

        String operatorId = booking.get().getOperatorId();
        Optional<Operator> operator = operatorRepository.findById(operatorId);
        log.info(operator.toString());
        // operator with the requested id does not exist;
        if(operator.isEmpty()) {
            return null;
        }

        List<Integer> updatedSlotTimes = operator.get().getBookedTimeSlots();
        updatedSlotTimes.remove(previousStartTime);
        updatedSlotTimes.add(request.newStartTime);
        Collections.sort(updatedSlotTimes);

        mongoTemplate.update(Operator.class)
                .matching(Criteria.where("id").is(operatorId))
                .apply(new Update().set("bookedTimeSlots", updatedSlotTimes))
                .first();

        mongoTemplate.update(Booking.class)
                .matching(Criteria.where("id").is(bookingId))
                .apply(new Update().set("startTime", request.newStartTime))
                .first();

        return new UpdateBookingResponse(
                bookingId, helper.slot(request.newStartTime, -1),
                helper.slot(previousStartTime, -1),
                helper.operatorName(booking.get()), "UPDATED");
    }

    public UpdateBookingResponse cancelBooking(String id) {
        Optional<Booking> booking = bookingRepository.findById(id);
        if(booking.isEmpty()) {
            return null;
        }

        Integer previousStartTime = booking.get().getStartTime();

        bookingRepository.deleteById(id);

        String operatorId = booking.get().getOperatorId();
        Optional<Operator> operator = operatorRepository.findById(operatorId);
        log.info(operator.toString());
        // operator with the requested id does not exist;
        if(operator.isEmpty()) {
            return null;
        }

        List<Integer> updatedSlotTimes = operator.get().getBookedTimeSlots();
        updatedSlotTimes.remove(previousStartTime);
        Collections.sort(updatedSlotTimes);

        mongoTemplate.update(Operator.class)
                .matching(Criteria.where("id").is(operatorId))
                .apply(new Update().set("bookedTimeSlots", updatedSlotTimes))
                .first();

        return new UpdateBookingResponse(booking.get().getId(),
                helper.slot(booking.get().getStartTime(),-1), helper.operatorName(booking.get()), "CANCELLED");

    }

}
