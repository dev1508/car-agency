package com.example.agency.services;

import com.example.agency.entities.Booking;
import com.example.agency.repositories.BookingRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    public List<Booking> allBookings() {
        return bookingRepository.findAll();
    }

    public Optional<Booking> getBooking(ObjectId id) {
        return bookingRepository.findById(id);
    }

//    public Booking createBooking(Map<String,Integer> payload) {
//        Integer startTime = payload.get("startTime");
//        Integer operator = payload.get("operator");
//
//        Booking booking = new Booking(startTime, operator);
//
//        bookingRepository.insert(booking);
//
//
//    }

}
