package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.aerospike.repository.config.EnableAerospikeRepositories;

@SpringBootApplication
@EnableAerospikeRepositories(repositoryBaseClass = BatchReactiveAerospikeRepositoryImpl.class)
public class SpringDataAerospikeExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataAerospikeExampleApplication.class, args);
    }

}
