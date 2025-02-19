package com.ibb.rest;

import com.ibb.model.Book;
import com.ibb.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class) // Enables Mockito for JUnit 5
class BookRestControllerTest {

    private MockMvc mockMvc;

    @Mock // Mock BookService
    private BookService bookService;

    @InjectMocks // Inject the mock into the controller
    private BookRestController bookRestController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(bookRestController).build();
    }

    @Test
    void testGetRandomBooks_DefaultParams() throws Exception {
        List<Book> mockBooks = List.of(
                new Book(1, "Book One", "Author One", 123456, "#ff5733"),
                new Book(2, "Book Two", "Author Two", 654321, "#33ff57"),
                new Book(1, "Book Three", "Author Three", 123456, "#ff5733"),
                new Book(2, "Book Four", "Author Four", 654321, "#33ff57")
        );

        when(bookService.getBooks(4, 0)).thenReturn(mockBooks);

        mockMvc.perform(get("/api/random-books")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(4))
                .andExpect(jsonPath("$[0].title").value("Book One"))
                .andExpect(jsonPath("$[1].author").value("Author Two"));

        verify(bookService, times(1)).getBooks(4, 0);
    }

    @Test
    void testGetRandomBooks_WithCustomParams() throws Exception {
        List<Book> mockBooks = List.of(
                new Book(3, "Custom Book", "Custom Author", 987654, "#5733ff")
        );

        when(bookService.getBooks(1, 2)).thenReturn(mockBooks);

        mockMvc.perform(get("/api/random-books")
                .param("count", "1")
                .param("index", "2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].id").value(3))
                .andExpect(jsonPath("$[0].title").value("Custom Book"));

        verify(bookService, times(1)).getBooks(1, 2);
    }

    @Test
    void testGetRandomBooks_EmptyResponse() throws Exception {
        when(bookService.getBooks(anyInt(), anyInt())).thenReturn(List.of());

        mockMvc.perform(get("/api/random-books")
                .param("count", "5")
                .param("index", "10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(0));

        verify(bookService, times(1)).getBooks(5, 10);
    }

    @Test
    void testGetRandomBooks_InvalidParams() throws Exception {
        mockMvc.perform(get("/api/random-books")
                .param("count", "-1")
                .param("index", "-5")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(bookService, times(1)).getBooks(-1, -5);
    }
}
