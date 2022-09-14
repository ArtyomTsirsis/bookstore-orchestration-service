package com.example.bookstoreorchestrationservice.domain.book;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Book {

    private String author;
    private String name;
    private int pages;
    private double price;
    private LocalDateTime creationTime;

}
