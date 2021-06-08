package com.renza.springreactivemongo;

import com.renza.springreactivemongo.model.Customer;
import com.renza.springreactivemongo.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SpringReactiveMongoApplication {


    @Bean
    CommandLineRunner customers(CustomerRepository customerRepository){

        return args -> {

            customerRepository.deleteAll()
                    .subscribe(null, null, ()-> {

                        List<Customer> customerList = Arrays.asList( new Customer(1, "Alexandre"),
                                                                     new Customer(2, "Sabrina"),
                                                                     new Customer(3, "Enzo"));

                        customerList.stream().forEach(i-> customerRepository
                                                        .save(i)
                                                        .subscribe(System.out::println));
                    });
        };


    }


    public static void main(String[] args) {
        SpringApplication.run(SpringReactiveMongoApplication.class, args);
    }

}
