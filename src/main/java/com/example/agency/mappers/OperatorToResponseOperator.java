package com.example.agency.mappers;

import com.example.agency.domains.responses.ResponseOperator;
import com.example.agency.entities.Operator;
import com.example.agency.utils.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class OperatorToResponseOperator {
    @Autowired
    private Helper helper;

    public ResponseOperator mapToResponseOperator(Operator operator) {
        ResponseOperator responseOperator = new ResponseOperator();
        responseOperator.id = operator.getId();
        responseOperator.name = operator.getName();
        responseOperator.bookedTimeSlots = new ArrayList<>();
        for (Integer bookedTimeSlot : operator.getBookedTimeSlots()) {
            String slot = helper.slot(bookedTimeSlot,-1);
            responseOperator.bookedTimeSlots.add(slot);
        }

        return responseOperator;
    }
}
