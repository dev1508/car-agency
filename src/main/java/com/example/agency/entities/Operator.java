package com.example.agency.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "operators")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Operator {
    @Id
    private String id;
    private String name;
    private List<Integer> bookedTimeSlots;

    public Operator(String name) {
        this.name = name;
        this.bookedTimeSlots = new ArrayList<>();
    }
}
