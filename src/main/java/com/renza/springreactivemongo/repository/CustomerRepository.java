package com.renza.springreactivemongo.repository;

import com.renza.springreactivemongo.model.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CustomerRepository extends ReactiveMongoRepository<Customer, Integer> {


}
