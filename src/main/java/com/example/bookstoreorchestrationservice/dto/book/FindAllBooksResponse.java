package com.example.bookstoreorchestrationservice.dto.book;

import com.example.bookstoreorchestrationservice.domain.book.Book;
import lombok.Data;

import java.util.List;

@Data
public class FindAllBooksResponse {

    private List<Book> books;

}
