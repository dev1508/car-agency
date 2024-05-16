package com.example.agency.repositories;

import com.example.agency.entities.Operator;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperatorRepository extends MongoRepository<Operator, String> {
}
