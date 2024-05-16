package com.example.agency.controllers;

import com.example.agency.domains.responses.FreeResponseOperator;
import com.example.agency.exception.ErrorException;
import com.example.agency.exception.ResourceNotFoundException;
import com.example.agency.services.OperatorService;
import com.example.agency.domains.responses.ResponseOperator;
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

    @GetMapping("/{type}/{value}/bookings")
    public ResponseEntity<ResponseOperator> getOperator(@PathVariable String value, @PathVariable String type) {
        ResponseOperator responseOperator = operatorService.getOperator(value, type);
        if(responseOperator == null) {
            throw new ResourceNotFoundException("Resource with " + type + " " + value + " not found.");
        }
        return new ResponseEntity<>(responseOperator, HttpStatus.OK);
    }

    @GetMapping("/{type}/{value}/free")
    public ResponseEntity<FreeResponseOperator> getFreeTimeForOperator(@PathVariable String value, @PathVariable String type) {
        FreeResponseOperator freeResponseOperator = operatorService.getFreeTimeForOperator(value, type);
        if(freeResponseOperator == null) {
            throw new ResourceNotFoundException("Resource with " + type + " " + value + " not found.");
        }
        return new ResponseEntity<>(freeResponseOperator, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseOperator> createOperator(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<>(operatorService.createOperator(payload), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseOperator> deleteOperator(@PathVariable String id) {
        ResponseOperator responseOperator = operatorService.deleteOperatorFromCollection(id);
        if(responseOperator == null) {
            throw new ResourceNotFoundException("Resource with ID " + id + " not found.");
        }
        return new ResponseEntity<>(responseOperator, HttpStatus.OK);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorException handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ErrorException(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }
}