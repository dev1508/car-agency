package com.example.agency.domains.responses;

import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class ResponseOperator {
    public String id;
    public String name;
    public ArrayList<String> bookedTimeSlots;
}

