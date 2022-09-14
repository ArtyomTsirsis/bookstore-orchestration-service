package com.example.bookstoreorchestrationservice.client;

import com.example.bookstoreorchestrationservice.dto.book.AddBookRequest;
import com.example.bookstoreorchestrationservice.dto.book.AddBookResponse;
import com.example.bookstoreorchestrationservice.dto.book.FindAllBooksResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@ConfigurationProperties("app.services.book-service")
public class BookClient {

    private final HttpClient httpClient;
    private static final String BOOK_URL = "http://localhost:8087/book";

    public ResponseEntity<FindAllBooksResponse> getAllBooks() {
        String url = BOOK_URL + "/allbooks";
        return httpClient.getForEntity(url, FindAllBooksResponse.class);
    }

    public ResponseEntity<AddBookResponse> addBook(AddBookRequest request) {
        String url = BOOK_URL + "/admin/add";
        HttpEntity<AddBookRequest> requestHttp = new HttpEntity<>(request);
        return httpClient.postForEntity(url, requestHttp, AddBookResponse.class);
    }

}
