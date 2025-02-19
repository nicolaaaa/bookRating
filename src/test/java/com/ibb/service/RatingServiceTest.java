/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.ibb.service;

import com.ibb.model.BookRating;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RatingServiceTest {

    private RatingService ratingService;

    @BeforeEach
    void setUp() {
        // Initialize the RatingService before each test
        ratingService = new RatingService();
    }

    @Test
    void testAddRatingNew() {
        // Given: a new rating for a book
        BookRating rating = new BookRating(1, 5); // Book ID 1, Rating 5

        // When: we add the rating
        ratingService.addRating(rating);

        // Then: the rating should be added successfully
        assertEquals(5, ratingService.getUserRating(1));
    }

    @Test
    void testAddRatingUpdate() {
        // Given: an initial rating for a book
        BookRating rating1 = new BookRating(1, 3); // Book ID 1, Rating 3
        ratingService.addRating(rating1);

        // When: we update the rating for the same book
        BookRating rating2 = new BookRating(1, 4); // Book ID 1, Rating 4
        ratingService.addRating(rating2);

        // Then: the rating should be updated to the new value
        assertEquals(4, ratingService.getUserRating(1));
    }

    @Test
    void testGetUserRatingWhenNoRating() {
        // Given: no ratings have been added yet

        // When: we try to get a rating for a non-existent book
        int rating = ratingService.getUserRating(1); // Book ID 1

        // Then: the rating should be 0 (default when no rating exists)
        assertEquals(0, rating);
    }

    @Test
    void testAddMultipleRatings() {
        // Given: multiple books and their ratings
        BookRating rating1 = new BookRating(1, 5); // Book ID 1, Rating 5
        BookRating rating2 = new BookRating(2, 3); // Book ID 2, Rating 3
        ratingService.addRating(rating1);
        ratingService.addRating(rating2);

        // When: we retrieve the ratings for both books
        int ratingForBook1 = ratingService.getUserRating(1);
        int ratingForBook2 = ratingService.getUserRating(2);

        // Then: the ratings should match the expected values
        assertEquals(5, ratingForBook1);
        assertEquals(3, ratingForBook2);
    }
}
