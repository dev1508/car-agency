package com.example.agency;

import org.bson.types.ObjectId;

import java.util.List;

public class ResponseOperator {
    private ObjectId id;
    private String name;

    public ResponseOperator(ObjectId id, String name) {
        this.id = id;
        this.name = name;
    }

    public List<String> bookedTimeSlots;
}

