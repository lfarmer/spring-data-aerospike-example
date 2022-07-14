package com.example;

import org.springframework.data.aerospike.repository.ReactiveAerospikeRepository;
import reactor.core.publisher.Flux;

public interface ReactiveCustomerRepository extends ReactiveAerospikeRepository<Customer, String> {
}
