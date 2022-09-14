package com.example.bookstoreorchestrationservice.core;

import com.example.bookstoreorchestrationservice.client.BookClient;
import com.example.bookstoreorchestrationservice.domain.book.Book;
import com.example.bookstoreorchestrationservice.dto.book.AddBookRequest;
import com.example.bookstoreorchestrationservice.dto.book.AddBookResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.management.openmbean.KeyAlreadyExistsException;

@Service
@RequiredArgsConstructor
public class AddBookService {

    private final BookClient client;

    public Book addBook(AddBookRequest request) throws KeyAlreadyExistsException {
        ResponseEntity<AddBookResponse> response = client.addBook(request);
        if (response != null && response.getStatusCode().is2xxSuccessful()) {
            return response.getBody().getBook();
        }
        throw new KeyAlreadyExistsException();
    }

}
