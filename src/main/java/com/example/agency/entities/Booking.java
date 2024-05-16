package com.example.agency.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "bookings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    @Id
    private String id;
    private Integer startTime;
    private String operatorId;

    public Booking(Integer startTime, String operatorId) {
        this.operatorId = operatorId;
        this.startTime = startTime;
    }
}
