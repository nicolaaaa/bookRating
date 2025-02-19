package com.ibb.rest;

import com.ibb.model.BookRating;
import com.ibb.service.AllRatingsService;
import com.ibb.service.RatingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class) // Use Mockito extension
class RatingRestControllerTest {

    private MockMvc mockMvc;

    @Mock // Mock dependencies instead of @MockBean
    private AllRatingsService allRatingsService;

    @Mock
    private RatingService ratingService;

    @InjectMocks // Automatically injects mocks into this controller
    private RatingRestController ratingRestController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(ratingRestController).build();
    }

    @Test
    void testGetBookRatings() throws Exception {
        // Mock the service responses
        when(ratingService.getUserRating(1)).thenReturn(4);
        when(allRatingsService.getAverageRating(1)).thenReturn(4.2);
        when(allRatingsService.getTotalRatings(1)).thenReturn(10);

        mockMvc.perform(get("/api/book-ratings/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userRating").value(4))
                .andExpect(jsonPath("$.averageRating").value(4.2))
                .andExpect(jsonPath("$.votesCount").value(10));

        // Verify service methods were called
        verify(ratingService, times(1)).getUserRating(1);
        verify(allRatingsService, times(1)).getAverageRating(1);
        verify(allRatingsService, times(1)).getTotalRatings(1);
    }

    @Test
    void testRateBook() throws Exception {
        when(allRatingsService.getAverageRating(anyInt())).thenReturn(4.2);
        when(allRatingsService.getTotalRatings(anyInt())).thenReturn(5);

        mockMvc.perform(post("/api/rate-book")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"bookId\": 1, \"rating\": 5}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userRating").value(5))
                .andExpect(jsonPath("$.averageRating").value(4.2))
                .andExpect(jsonPath("$.votesCount").value(5));

        verify(allRatingsService, times(1)).addRating(any(BookRating.class));
        verify(ratingService, times(1)).addRating(any(BookRating.class));
    }
}
