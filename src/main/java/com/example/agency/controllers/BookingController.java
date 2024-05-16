package com.example.agency.controllers;

import com.example.agency.domains.requests.CreateBookingRequest;
import com.example.agency.domains.requests.UpdateBookingRequest;
import com.example.agency.domains.responses.CreateBookingResponse;
import com.example.agency.domains.responses.ResponseOperator;
import com.example.agency.domains.responses.UpdateBookingResponse;
import com.example.agency.entities.Booking;
import com.example.agency.exception.ErrorException;
import com.example.agency.exception.ResourceNotFoundException;
import com.example.agency.services.BookingService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping
    public ResponseEntity<List<CreateBookingResponse>> getAllBookings() {
        return new ResponseEntity<>(bookingService.allBookings(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreateBookingResponse> getSingleBooking(@PathVariable String id) {
        CreateBookingResponse bookingResponse = bookingService.getBooking(id);
        if(bookingResponse == null) {
            throw new ResourceNotFoundException("Resource with " + id + " not found.");
        }
        return new ResponseEntity<>(bookingResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CreateBookingResponse> createNewBooking(@RequestBody CreateBookingRequest payload) {
        CreateBookingResponse bookingResponse = bookingService.createBooking(payload);
        if(bookingResponse == null) {
            throw new ResourceNotFoundException("Could not create booking. " +
                    "Please check if the requested startTime is " +
                    "between 0 and 23, or the requested operatorName " +
                    "exists in the database, or, the operator has the " +
                    "requested time slot already booked."
            );
        }
        return new ResponseEntity<>(bookingResponse, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<UpdateBookingResponse> updateBooking(@RequestBody UpdateBookingRequest payload) {
        UpdateBookingResponse updateBookingResponse = bookingService.updateBooking(payload);
        if(updateBookingResponse == null) {
            throw new ResourceNotFoundException("Resource with " + payload.bookingId + " not found.");
        }
        return new ResponseEntity<>(updateBookingResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UpdateBookingResponse> deleteBooking(@PathVariable String id) {
        UpdateBookingResponse updateBookingResponse = bookingService.cancelBooking(id);
        if(updateBookingResponse == null) {
            throw new ResourceNotFoundException("Resource with " + id + " not found.");
        }
        return new ResponseEntity<>(updateBookingResponse, HttpStatus.OK);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorException handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ErrorException(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

}
