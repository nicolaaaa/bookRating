package com.ibb.service;

import com.ibb.model.BookRating;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AllRatingsService {

    private final List<BookRating> allRatings = new ArrayList<>();

    // Add a rating (Global)
    public void addRating(BookRating rating) {
        allRatings.add(rating);
    }

    // Get the average rating for a book (Global)
    public double getAverageRating(int bookId) {
        List<Integer> ratings = allRatings.stream()
                .filter(r -> r.getBookId() == bookId)
                .map(BookRating::getRating)
                .toList();

        if (ratings.isEmpty()) {
            return 0.0;
        }
        return Math.round(ratings.stream().mapToInt(Integer::intValue).average().orElse(0.0) * 10) / 10.0;
    }

    // Get the total number of ratings for a book (Global)
    public int getTotalRatings(int bookId) {
        return (int) allRatings.stream()
                .filter(r -> r.getBookId() == bookId)
                .count();
    }

    public List<BookRating> getAllBookRatings() {
        return allRatings;
    }
}
