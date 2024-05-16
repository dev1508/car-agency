package com.example.agency.domains.responses;

public class CreateBookingResponse {
    public String id;
    public String timeSlot;

    public CreateBookingResponse(String id, String timeSlot, String operatorName) {
        this.id = id;
        this.timeSlot = timeSlot;
        this.operatorName = operatorName;
    }

    public String operatorName;
}
