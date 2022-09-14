package com.example.bookstoreorchestrationservice.controller;

import com.example.bookstoreorchestrationservice.core.AddBookService;
import com.example.bookstoreorchestrationservice.core.FindAllBookService;
import com.example.bookstoreorchestrationservice.core.UserAuthorizationService;
import com.example.bookstoreorchestrationservice.domain.book.Book;
import com.example.bookstoreorchestrationservice.dto.book.AddBookRequest;
import com.example.bookstoreorchestrationservice.dto.user.UserValidationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/bookstore")
@RequiredArgsConstructor
public class BookStoreController {

    private final AddBookService addBookService;
    private final UserAuthorizationService authorization;
    private final FindAllBookService allBookService;

    @GetMapping(value = "/")
    public String getMainView(Model model) {
        refreshAuthorizationStatus(model);
        model.addAttribute("request", new UserValidationRequest());
        return "mainPage";
    }

    @GetMapping(value = "/book/add")
    public String getBookAddForm(Model model) {
        refreshAuthorizationStatus(model);
        model.addAttribute("request", new AddBookRequest());
        return "addBook";
    }

    @GetMapping(value = "book/all")
    public String getAllBooks(Model model) {
        refreshAuthorizationStatus(model);
        List<Book> allBooks = allBookService.getAllBooks();
        model.addAttribute("books", allBooks);
        if (authorization.getUser().getLogin() == null) {
            model.addAttribute("request", new UserValidationRequest());
        }
        return "allBooks";
    }

    @PostMapping("user/login")
    public String logIn(@ModelAttribute UserValidationRequest request, Model model) {
        authorization.singIn(request);
        refreshAuthorizationStatus(model);
        return "redirect:/bookstore/";
    }

    @PostMapping(value = "user/logout")
    public String logout(Model model) {
        authorization.logOut();
        refreshAuthorizationStatus(model);
        return "redirect:/bookstore/";
    }

    @PostMapping(value = "/book/add")
    public String addBook(@ModelAttribute AddBookRequest bookRequest, Model model) {
        refreshAuthorizationStatus(model);
        addBookService.addBook(bookRequest);
        return "redirect:/bookstore/book/all";
    }

    private void refreshAuthorizationStatus(Model model) {
        if (model.containsAttribute("status")) {
            model.asMap().merge("status", authorization.getUser().getStatus(), (s1, s2) -> s2);
        } else {
            model.addAttribute("status", authorization.getUser().getStatus());
        }
        if (!model.containsAttribute("user")) {
            model.addAttribute("user", authorization.getUser().getLogin());
        }
    }

}
