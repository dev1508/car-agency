package com.example.agency.domains.responses;

import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class FreeResponseOperator {
    private String id;
    private String name;

    public FreeResponseOperator(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public ArrayList<String> freeTimeSlots;
}
