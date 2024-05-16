package com.example.agency.mappers;

import com.example.agency.domains.responses.ResponseOperator;
import com.example.agency.entities.Operator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class OperatorToResponseOperator {
    public ResponseOperator mapToResponseOperator(Operator operator) {
        ResponseOperator responseOperator = new ResponseOperator();
        responseOperator.id = operator.getId();
        responseOperator.name = operator.getName();
        for (Integer bookedTimeSlot : operator.getBookedTimeSlots()) {
            Integer slotEndTime = bookedTimeSlot + 1;

            String slot = bookedTimeSlot.toString() + slotEndTime.toString();
            responseOperator.bookedTimeSlots.add(slot);
        }
        if(responseOperator.bookedTimeSlots == null) {
            responseOperator.bookedTimeSlots = new ArrayList<>();
        }

        return responseOperator;
    }
}
