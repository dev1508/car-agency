package com.example.agency;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "operators")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Operator {
    @Id
    private ObjectId id;
    private String name;

    public Operator(String name) {
        this.name = name;
    }

    private List<Integer> bookedTimeSlots;
}

