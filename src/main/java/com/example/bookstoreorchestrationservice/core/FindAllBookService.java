package com.example.bookstoreorchestrationservice.core;

import com.example.bookstoreorchestrationservice.client.BookClient;
import com.example.bookstoreorchestrationservice.domain.book.Book;
import com.example.bookstoreorchestrationservice.dto.book.FindAllBooksResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FindAllBookService {

    private final BookClient client;

    public List<Book> getAllBooks() {
        ResponseEntity<FindAllBooksResponse> response = client.getAllBooks();
        if (response != null && response.getStatusCode().is2xxSuccessful()) {
            List<Book> books = response.getBody().getBooks();
            return books.stream().sorted(Comparator.comparing(Book::getCreationTime).reversed()).toList();
        }
        return new ArrayList<>();
    }

}
