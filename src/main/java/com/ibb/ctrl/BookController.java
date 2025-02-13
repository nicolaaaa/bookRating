/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ibb.ctrl;

import com.ibb.model.Book;
import com.ibb.model.BookRatingInfo;
import com.ibb.service.AllRatingsService;
import com.ibb.service.BookService;
import com.ibb.util.ShelveGenerator;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookController {

    private final BookService bookService;
    private AllRatingsService ratingService;

    public BookController(BookService bookService, AllRatingsService ratingService) {
        this.bookService = bookService;
        this.ratingService = ratingService;

    }

    @GetMapping("/books")
    public String getBooks(Model model) {
        List<String> shelves = new ArrayList<>();

        for (int i = 0; i < 5; i++) { // Generate 4 shelves
            shelves.add(ShelveGenerator.generateRandomShelf());
        }

        model.addAttribute("shelves", shelves);
        List<Book> books = bookService.generateRandomBooks(8);
        Map<Integer, BookRatingInfo> bookRatingsMap = books.stream()
                .collect(Collectors.toMap(
                        Book::getId, // Key: Book ID
                        book -> new BookRatingInfo(
                                ratingService.getAverageRating(book.getId()),
                                ratingService.getTotalRatings(book.getId()))
                ));
        model.addAttribute("books", books);
        model.addAttribute("bookRatings", bookRatingsMap);

        return "books";
    }
}
