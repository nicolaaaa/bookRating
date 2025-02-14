package com.ibb.service;

import com.ibb.model.BookRating;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
@SessionScope // Only stores ratings for the current session
public class RatingService {

    private final List<BookRating> userRatings = new ArrayList<>();
    private static final Logger LOG = Logger.getLogger(RatingService.class.getName());

    // Add a rating (Session-based)
    public void addRating(BookRating rating) {
        // Remove old rating if the user already rated the book
        userRatings.removeIf(r -> r.getBookId() == rating.getBookId());
        userRatings.add(rating);
    }

    // Get the current user's rating for a book
    public Integer getUserRating(int bookId) {
        return userRatings.stream()
                .filter(r -> r.getBookId() == bookId)
                .map(BookRating::getRating)
                .findFirst()
                .orElse(0); // Return null if no rating exists
    }
}
