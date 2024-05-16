package com.example.agency.utils;

import com.example.agency.entities.Booking;
import com.example.agency.entities.Operator;
import com.example.agency.repositories.OperatorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Helper {
    private static final Logger log = LoggerFactory.getLogger(Helper.class);
    @Autowired
    private OperatorRepository operatorRepository;

    public String slot(Integer start, int end) {
        if(end == -1) {
            end = start + 1;
        }
        return start.toString() + "-" + Integer.toString(end);
    }

    public String operatorName(Booking booking) {
        String operatorId = booking.getOperatorId();
        Optional<Operator> operator = operatorRepository.findById(operatorId);
        if(operator.isEmpty()) {
            log.info("here{}", operatorId);
            return null;
        }
        return operator.get().getName();
    }
}
