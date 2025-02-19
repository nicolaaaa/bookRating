/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ibb.ctrl;

import com.ibb.model.Book;
import com.ibb.model.BookRatingInfo;
import com.ibb.service.AllRatingsService;
import com.ibb.service.BookService;
import com.ibb.service.RatingService;
import com.ibb.util.ShelveGenerator;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    private static final Logger LOG = Logger.getLogger(WebController.class.getName());

    private final BookService bookService;
    private final AllRatingsService allRatingService;
    private final RatingService userRatingService;

    @Autowired
    public WebController(BookService bookService, AllRatingsService allRatingService, RatingService userRatingService) {
        this.bookService = bookService;
        this.allRatingService = allRatingService;
        this.userRatingService = userRatingService;

    }

    @GetMapping("/books")
    public String getBooks(Model model) {
        List<String> shelves = new ArrayList<>();
        LOG.log(Level.INFO, "BOOKS page aufgerufen");

        for (int i = 0; i < 5; i++) {
            shelves.add(ShelveGenerator.generateRandomShelf());
        }

        model.addAttribute("shelves", shelves);
        LOG.log(Level.INFO, "Get Initial Books between 0 - 8");

        List<Book> books = bookService.getBooks(8, 0);
        Map<Integer, BookRatingInfo> bookRatingsMap = books.stream()
                .collect(Collectors.toMap(
                        Book::getId,
                        book -> new BookRatingInfo(
                                allRatingService.getAverageRating(book.getId()),
                                allRatingService.getTotalRatings(book.getId()),
                                userRatingService.getUserRating(book.getId()))
                ));
        LOG.log(Level.INFO, "Books erhalten: " + books);

        List<String> bookRatingInfoList = bookRatingsMap.values().stream()
                .map(BookRatingInfo::toString)
                .collect(Collectors.toList());
        LOG.log(Level.INFO, "Ratings erhalten: " + bookRatingInfoList);

        model.addAttribute("books", books);
        model.addAttribute("bookRatings", bookRatingsMap);

        return "books";
    }
}
