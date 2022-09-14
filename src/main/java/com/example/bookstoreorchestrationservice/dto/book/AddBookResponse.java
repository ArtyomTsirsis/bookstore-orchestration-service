package com.example.bookstoreorchestrationservice.dto.book;

import com.example.bookstoreorchestrationservice.domain.book.Book;
import lombok.Data;

@Data
public class AddBookResponse {

    private Book book;

}
