package com.example.agency;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/operator")
public class OperatorController {

    @Autowired
    private OperatorService operatorService;

    @GetMapping
    public ResponseEntity<List<ResponseOperator>> getAllOperators() {
        return new ResponseEntity<>(operatorService.allOperators(), HttpStatus.OK);
    }

    @GetMapping("/{id}/bookings")
    public ResponseEntity<ResponseOperator> getOperator(@PathVariable ObjectId id) {
        return new ResponseEntity<>(operatorService.getOperator(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/free")
    public ResponseEntity<FreeResponseOperator> getFreeTimeForOperator(@PathVariable ObjectId id) {
        return new ResponseEntity<>(operatorService.getFreeTimeForOperator(id), HttpStatus.OK);
    }

//    @PostMapping
//    public ResponseEntity<ResponseOperator> createOperator(@RequestBody Map<String, Integer> payload) {
//        return new ResponseEntity<>()
//    }
}
