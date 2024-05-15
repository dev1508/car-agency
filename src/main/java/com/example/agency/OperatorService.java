package com.example.agency;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class OperatorService {
    @Autowired
    private OperatorRepository operatorRepository;

    public ResponseOperator createOperator(Map<String,String> payload) {
        String name = payload.get("name");

        Operator operator = operatorRepository.insert(new Operator(name));

        return new ResponseOperator(operator.getId(), operator.getName());

    }

    public List<ResponseOperator> allOperators() {
        List<Operator> allOperators = operatorRepository.findAll();

        List<ResponseOperator> resp = new ArrayList<>();

        for (Operator allOperator : allOperators) {
            ResponseOperator respOp = new ResponseOperator(
                    allOperator.getId(), allOperator.getName());

            for (Integer bookedTimeSlot : allOperator.getBookedTimeSlots()) {
                Integer slotEndTime = bookedTimeSlot + 1;

                String slot = bookedTimeSlot.toString() + slotEndTime.toString();
                respOp.bookedTimeSlots.add(slot);
            }

            resp.add(respOp);
        }

        return resp;
    }

    public ResponseOperator getOperator(ObjectId id) {
        Optional<Operator> operator = operatorRepository.findById(id);

        if(operator.isPresent()) {
            ResponseOperator responseOperator = new ResponseOperator(
                    operator.get().getId(), operator.get().getName());
            for (Integer bookedTimeSlot : operator.get().getBookedTimeSlots()) {
                Integer slotEndTime = bookedTimeSlot + 1;

                String slot = bookedTimeSlot.toString() + slotEndTime.toString();
                responseOperator.bookedTimeSlots.add(slot);
            }

            return responseOperator;
        } else {
            throw new RuntimeException("No operator found with this order id");
        }
    }

    public FreeResponseOperator getFreeTimeForOperator(ObjectId id) {
        Optional<Operator> operator = operatorRepository.findById(id);

        if (operator.isPresent()) {
            FreeResponseOperator responseOperator = new FreeResponseOperator(
                    operator.get().getId(), operator.get().getName());
            responseOperator.freeTimeSlots = missingSlots(operator.get().getBookedTimeSlots());

            return responseOperator;
        } else {
            throw new RuntimeException("No operator found with this order id");
        }
    }

    public List<String> missingSlots(List<Integer> bookedSlots) {

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

        List<String> ranges = new ArrayList<>();
        for (int i = 0; i < missingSlots.size(); i++) {
            Integer start = missingSlots.get(i);
            while (i < missingSlots.size() - 1 && missingSlots.get(i+1) == missingSlots.get(i) + 1) {
                i++;
            }
            Integer end = missingSlots.get(i) + 1;

            ranges.add(start.toString() + "-" + end.toString());
        }

        return ranges;
    }
}
