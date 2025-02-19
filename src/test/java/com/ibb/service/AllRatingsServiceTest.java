/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.ibb.service;

import com.ibb.model.BookRating;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AllRatingsServiceTest {

    private AllRatingsService allRatingsService;

    @BeforeEach
    void setUp() {
        // Initialize the AllRatingsService before each test
        allRatingsService = new AllRatingsService();
    }

    @Test
    void testAddRating() {
        // Given: a new rating for a book
        BookRating rating = new BookRating(1, 5); // Book ID 1, Rating 5

        // When: we add the rating
        allRatingsService.addRating(rating);

        // Then: the rating should be added to the list
        assertEquals(1, allRatingsService.getAllBookRatings().size());
        assertEquals(5, allRatingsService.getAverageRating(1));
    }

    @Test
    void testGetAverageRatingWithRatings() {
        // Given: ratings for a book
        allRatingsService.addRating(new BookRating(1, 5)); // Book ID 1, Rating 5
        allRatingsService.addRating(new BookRating(1, 3)); // Book ID 1, Rating 3
        allRatingsService.addRating(new BookRating(1, 4)); // Book ID 1, Rating 4

        // When: we calculate the average rating for the book
        double averageRating = allRatingsService.getAverageRating(1);

        // Then: the average should be calculated correctly
        assertEquals(4.0, averageRating);
    }

    @Test
    void testGetAverageRatingWithNoRatings() {
        // When: we calculate the average rating for a book with no ratings
        double averageRating = allRatingsService.getAverageRating(1);

        // Then: the average rating should be 0.0
        assertEquals(0.0, averageRating);
    }

    @Test
    void testGetTotalRatings() {
        // Given: ratings for a book
        allRatingsService.addRating(new BookRating(1, 5)); // Book ID 1, Rating 5
        allRatingsService.addRating(new BookRating(1, 3)); // Book ID 1, Rating 3
        allRatingsService.addRating(new BookRating(1, 4)); // Book ID 1, Rating 4

        // When: we get the total number of ratings for the book
        int totalRatings = allRatingsService.getTotalRatings(1);

        // Then: the total should be the correct number of ratings
        assertEquals(3, totalRatings);
    }

    @Test
    void testGetTotalRatingsWithNoRatings() {
        // When: we get the total number of ratings for a book with no ratings
        int totalRatings = allRatingsService.getTotalRatings(1);

        // Then: the total should be 0
        assertEquals(0, totalRatings);
    }

    @Test
    void testGetAllBookRatings() {
        // Given: some ratings for books
        allRatingsService.addRating(new BookRating(1, 5)); // Book ID 1, Rating 5
        allRatingsService.addRating(new BookRating(1, 4)); // Book ID 1, Rating 4
        allRatingsService.addRating(new BookRating(2, 3)); // Book ID 2, Rating 3

        // When: we get all book ratings
        List<BookRating> ratings = allRatingsService.getAllBookRatings();

        // Then: the list should contain all added ratings
        assertEquals(3, ratings.size());
        assertEquals(5, ratings.get(0).getRating());
        assertEquals(4, ratings.get(1).getRating());
        assertEquals(3, ratings.get(2).getRating());
    }
}
