package com.example;


import lombok.Builder;
import lombok.Value;
import org.springframework.data.annotation.Id;

@Value
@Builder(toBuilder = true)
public class Customer {
    @Id
    String id;
}
