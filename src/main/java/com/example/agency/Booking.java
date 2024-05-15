package com.example.agency;

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
    private ObjectId id;
    private Integer startTime;
    private Integer operator;

    public Booking(Integer startTime, Integer operator) {
        this.operator = operator;
        this.startTime = startTime;
    }
}
