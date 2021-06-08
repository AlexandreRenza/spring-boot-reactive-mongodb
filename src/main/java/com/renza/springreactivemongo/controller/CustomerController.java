package com.renza.springreactivemongo.controller;

import com.renza.springreactivemongo.model.Customer;
import com.renza.springreactivemongo.repository.CustomerRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.awt.*;
import java.time.Duration;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public Flux<Customer> getAllCustomers(){

        return customerRepository.findAll();

    }

    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Customer> getAllCustomersStream(){

        return customerRepository.findAll().delayElements(Duration.ofSeconds(2));

    }

    @GetMapping("/{id}")
    public Mono<Customer> getCustomerById(@PathVariable int id){

        return customerRepository.findById(id);

    }

    @PostMapping("/add")
    public Mono<Customer> addCustomer(@RequestBody Customer customer){

        customerRepository.save(customer).subscribe();

        return getCustomerById(customer.getId());


    }






}
