/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ibb.rest;

import com.ibb.model.Book;
import com.ibb.service.BookService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookRestController {

    private static final Logger LOG = Logger.getLogger(BookRestController.class.getName());
    private final BookService bookService;

    @Autowired
    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/api/random-books")
    public List<Book> getRandomBooks(
            @RequestParam(required = false, defaultValue = "4") int count,
            @RequestParam(required = false, defaultValue = "0") int index) {
        int toIndex = index + count;
        LOG.log(Level.INFO, "Get Books between " + index + " - " + toIndex);
        return bookService.getBooks(count, index);
    }
}
