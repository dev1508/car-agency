package com.example.agency;

import org.bson.types.ObjectId;

import java.util.List;

public class FreeResponseOperator {
    private ObjectId id;
    private String name;

    public FreeResponseOperator(ObjectId id, String name) {
        this.id = id;
        this.name = name;
    }

    public List<String> freeTimeSlots;
}
