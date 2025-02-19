/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.ibb.service;

import com.ibb.model.Book;
import com.ibb.model.BookRating;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import org.mockito.MockitoAnnotations;

class BookServiceTest {

    private BookService bookService;

    @Mock
    private AllRatingsService allRatingsService;

    @BeforeEach
    void setUp() {
        // Initialize mocks and the BookService before each test
        MockitoAnnotations.openMocks(this);
        bookService = new BookService(allRatingsService);
    }

    @Test
    void testGenerateRandomBook() {
        // When: generating a random book
        Book book = bookService.generateRandomBook();

        // Then: verify the book has a valid id, title, and author
        assertNotNull(book.getId());
        assertNotNull(book.getTitle());
        assertNotNull(book.getAuthor());
    }

    @Test
    void testGenerateRandomBooks() {
        // When: generating a list of random books
        List<Book> books = bookService.generateRandomBooks(3);

        // Then: verify the list contains the expected number of books
        assertEquals(3, books.size());
    }

    @Test
    void testGetBooksWithExistingBooks() {
        // Given: there are already books in the list
        List<Book> initialBooks = bookService.getBooks(5, 0);

        // Capture the current size of booksList
        int sizeBefore = initialBooks.size();

        // When: we call getBooks() again with a different index, to avoid modifying the list during iteration
        List<Book> books = bookService.getBooks(3, sizeBefore); // Start at the end of the list

        List<Book> bookList = bookService.getBooksList();
        // Then: the books list should be returned correctly without modifying the list concurrently
        assertEquals(sizeBefore + 3, bookList.size());
        assertEquals(3, books.size());
    }

    @Test
    void testGetBooksWithEmptyList() {
        // When: we request books when the list is empty
        List<Book> books = bookService.getBooks(5, 0);

        // Then: the method should generate new random books
        assertEquals(5, books.size());
    }

    @Test
    void testGenerateRandomRatingsForBook() {
        List<Book> books = bookService.getBooks(5, 0);

        verify(allRatingsService, atLeast(5)).addRating(any(BookRating.class));

//        List<BookRating> ratings = allRatingsService.getAllBookRatings();
//        assertEquals(ratings.size(), 1);
//
//        assertTrue(ratings.get(0).getRating() >= 1 && ratings.get(0).getRating() <= 5, "Value should be between 1 and 5");
//        assertEquals(ratings.get(0).getRating(), allRatingsService.getAverageRating(book.getId()));
//        assertEquals(ratings.get(0).getBookId(), book.getId());
//        assertEquals(allRatingsService.getTotalRatings(book.getId()), 1);
    }
}
