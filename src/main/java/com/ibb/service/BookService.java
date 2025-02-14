/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ibb.service;

import com.github.javafaker.Faker;
import com.ibb.model.Book;
import com.ibb.model.BookRating;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class BookService {

    private final Faker faker = new Faker();
    private final Random random = new Random();
    private int bookIdCounter = 1;
    private final AllRatingsService ratingService;
    private final List<Book> booksList = new ArrayList<>();
    private static final Logger LOG = Logger.getLogger(BookService.class.getName());

    public BookService(AllRatingsService ratingService) {
        this.ratingService = ratingService;
    }

    public List<Book> getBooks(int count, int index) {
        int toIndex = index + count;
        if (toIndex >= booksList.size()) {
            LOG.log(Level.INFO, "Bookslist empty -> generate random books");
            booksList.addAll(generateRandomBooks(count));
        }
        return booksList.subList(index, toIndex);
    }

    public Book generateRandomBook() {
        Book book = new Book(
                bookIdCounter++,
                faker.book().title(),
                faker.book().author(),
                random.nextInt(999999999),
                getRandomColor()
        );
        LOG.log(Level.INFO, "-> generate random ratings for books");
        generateRandomRatingsForBook(book.getId());
        return book;
    }

    public List<Book> generateRandomBooks(int count) {
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            books.add(generateRandomBook());
        }
        return books;
    }

    private void generateRandomRatingsForBook(int bookId) {
        int numberOfRatings = random.nextInt(21);
        for (int i = 0; i < numberOfRatings; i++) {
            int rating = random.nextInt(5) + 1;
            ratingService.addRating(new BookRating(bookId, rating, "test"));
        }
    }

    private String getRandomColor() {
        String[] colors = {"#5a8b5d", "#ae696f", "#c9ae92", "#5e6a58", "#A833FF", "#33FFF3", "#907350", "#5f8971", "#36563e", "#81272e", "#9e391a", "#dd9933", "#7a5c00", "#3e1407"};
        return colors[random.nextInt(colors.length)];
    }

}
