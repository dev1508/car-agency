package com.example.agency.repositories;

import com.example.agency.entities.Booking;
import com.example.agency.entities.Operator;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends MongoRepository<Booking, String> {
    Optional<List<Booking>> findBookingByOperatorId(String operatorId);
}
