package com.example.agency.services;

import com.example.agency.domains.responses.FreeResponseOperator;
import com.example.agency.entities.Operator;
import com.example.agency.mappers.OperatorToResponseOperator;
import com.example.agency.repositories.OperatorRepository;
import com.example.agency.domains.responses.ResponseOperator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OperatorService {
    private static final Logger log = LoggerFactory.getLogger(OperatorService.class);

    @Autowired
    private OperatorRepository operatorRepository;

    @Autowired
    private OperatorToResponseOperator operatorToResponseOperator;

    // Creates a new Operator
    public ResponseOperator createOperator(Map<String,String> payload) {
        String name = payload.get("name");

        Operator operator = operatorRepository.insert(new Operator(name));

        return operatorToResponseOperator.mapToResponseOperator(operator);
    }

    // Get all operator details
    public List<ResponseOperator> allOperators() {
        List<Operator> allOperators = operatorRepository.findAll();

        List<ResponseOperator> resp = new ArrayList<>();

        for (Operator operator : allOperators) {
            ResponseOperator responseOperator = operatorToResponseOperator.mapToResponseOperator(operator);
            resp.add(responseOperator);
        }

        return resp;
    }

    public ResponseOperator deleteOperatorFromCollection(String id) {
        Optional<Operator> operator = operatorRepository.findById(id);

        operatorRepository.deleteById(id);

        return operator.map(value -> operatorToResponseOperator.mapToResponseOperator(value)).orElse(null);
    }

    // get specific operator details
    public ResponseOperator getOperator(String value, String type) {
        Optional<Operator> operator;
        if (type.equals("name")) {
            operator = operatorRepository.findOperatorByName(value);
        } else {
            operator = operatorRepository.findById(value);
        }
        return operator.map(op -> operatorToResponseOperator.mapToResponseOperator(op)).orElse(null);
    }

    // get specific operator free slots
    public FreeResponseOperator getFreeTimeForOperator(String value, String type) {
        Optional<Operator> operator;
        if (type.equals("name")) {
            operator = operatorRepository.findOperatorByName(value);
        } else {
            operator = operatorRepository.findById(value);
        }
        if(operator.isEmpty()) {
            return null;
        }

        FreeResponseOperator responseOperator = new FreeResponseOperator(
                operator.get().getId(), operator.get().getName());
        responseOperator.freeTimeSlots = missingSlots(operator.get().getBookedTimeSlots());
        if(responseOperator.freeTimeSlots == null) {
            responseOperator.freeTimeSlots = new ArrayList<>();
        }

        return responseOperator;
    }

    public ArrayList<String> missingSlots(List<Integer> bookedSlots) {

        boolean[] present = new boolean[24];

        for (Integer num : bookedSlots) {
            present[num] = true;
        }

        List<Integer> missingSlots = new ArrayList<>();
        for (int i = 0; i < present.length; i++) {
            if (!present[i]) {
                missingSlots.add(i);
            }
        }

        ArrayList<String> ranges = new ArrayList<>();
        for (int i = 0; i < missingSlots.size(); i++) {
            Integer start = missingSlots.get(i);
            while (i < missingSlots.size() - 1 && missingSlots.get(i+1) == missingSlots.get(i) + 1) {
                i++;
            }
            int end = missingSlots.get(i) + 1;

            ranges.add(start.toString() + "-" + end);
        }

        return ranges;
    }
}
