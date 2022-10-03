package com.example.demo.controller;

import com.example.demo.book.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DummyBookController {
    private static final int ID_FOR_SUCCESS = 1;
    private static final int ID_FOR_ERROR = 3;
    private static final String PATH = "/books";
    private static final String ID_TEMPLATE = "/{id}";
    private static final String URI = PATH + ID_TEMPLATE;
    private static final String APPLICATION_JSON = "application/json";

    private static final Logger LOGGER = LoggerFactory.getLogger(DummyBookController.class);


    @GetMapping(value = URI, produces = APPLICATION_JSON)
    public @ResponseBody Book processGetBookRequest(@PathVariable int id) {
        LOGGER.info("Handling book request for id: {}", id);
        return findBookById(id);
    }

    private Book findBookById(int id) {
        switch (id) {
            case ID_FOR_SUCCESS -> {
                return successBook();
            }
            case ID_FOR_ERROR -> {
                return errorBook(id);
            }
            default -> {
                return notFoundBook(id);
            }
        }
    }

    private Book successBook() {
        Book book = new Book(ID_FOR_SUCCESS, "THE BOOK", "AUTHOR");
        LOGGER.debug("Returning book {}", book);
        return book;
    }

    private Book errorBook(int id) {
        try {
            simulateException();
        } catch (RuntimeException ex) {
            LOGGER.error("Error while obtaining book with id: {}, exception: {}", id, ex);
            throw ex;
        }
        return null;
    }

    private Book notFoundBook(int id) {
        LOGGER.warn("Book with id {} could not be found", id);
        return null;
    }

    private void simulateException() {
        throw new RuntimeException();
    }
}
