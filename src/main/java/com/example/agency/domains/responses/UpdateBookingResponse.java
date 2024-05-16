package com.example.agency.domains.responses;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateBookingResponse {
    public String id;
    public String newTimeSlot;
    public String previousTimeSlot;

    public UpdateBookingResponse(String id, String newTimeSlot, String previousTimeSlot, String operatorName, String status) {
        this.id = id;
        this.newTimeSlot = newTimeSlot;
        this.previousTimeSlot = previousTimeSlot;
        this.operatorName = operatorName;
        this.status = status;
    }

    public UpdateBookingResponse(String id, String previousTimeSlot, String operatorName, String status) {
        this.id = id;
        this.previousTimeSlot = previousTimeSlot;
        this.operatorName = operatorName;
        this.status = status;
    }

    public String operatorName;
    public String status;
}
