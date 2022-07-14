package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

import java.util.List;

@SpringBootTest
class ReactiveCustomerRepositoryTest {

    @Autowired
    private ReactiveCustomerRepository reactiveCustomerRepository;

    @Test
    void shouldUseBatchReadRepository() {
        // When
        StepVerifier.create(reactiveCustomerRepository.saveAll(
                List.of(
                        Customer.builder().id("1").build(),
                        Customer.builder().id("2").build(),
                        Customer.builder().id("3").build(),
                        Customer.builder().id("4").build())))
                .expectNextCount(4)
                .verifyComplete();

        // Then
        StepVerifier.create(reactiveCustomerRepository.findAllById(
                List.of("1","2","3","4")))
                .expectNextCount(4)
                .verifyComplete();
    }
}