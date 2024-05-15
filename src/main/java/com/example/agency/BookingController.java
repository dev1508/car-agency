package com.example.agency;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        return new ResponseEntity<>(bookingService.allBookings(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Booking>> getSingleBooking(@PathVariable ObjectId id) {
        return new ResponseEntity<>(bookingService.getBooking(id), HttpStatus.OK);
    }

//    @PostMapping
//    public ResponseEntity<Booking> createNewBooking(@RequestBody Map<String, Integer> payload) {
////        return new ResponseEntity<Booking>(bookingService.createBooking(payload), HttpStatus.OK);
//    }

//    @PutMapping("/{id}")
//    public ResponseEntity<>

}
