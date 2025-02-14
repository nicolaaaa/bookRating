package com.ibb.rest;

import com.ibb.model.BookRating;
import com.ibb.service.AllRatingsService;
import com.ibb.service.RatingService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RatingRestController {

    private final AllRatingsService allRatingsService;
    private final RatingService userRatingService;

    public RatingRestController(AllRatingsService allRatingsService, RatingService userRatingService) {
        this.allRatingsService = allRatingsService;
        this.userRatingService = userRatingService;
    }

    @PostMapping("/rate-book")
    public Map<String, Object> rateBook(@RequestBody RatingRequest request, HttpSession session) {
        String sessionId = session.getId(); // Get session ID

        // Create rating object
        BookRating rating = new BookRating(request.getBookId(), request.getRating(), sessionId);

        // Store in both global and session-based services
        allRatingsService.addRating(rating);
        userRatingService.addRating(rating);

        // Prepare response
        Map<String, Object> response = new HashMap<>();
        response.put("userRating", request.getRating());
        response.put("averageRating", allRatingsService.getAverageRating(request.getBookId()));
        response.put("votesCount", allRatingsService.getTotalRatings(request.getBookId()));

        return response;
    }

    @GetMapping("/book-ratings/{bookId}")
    public Map<String, Object> getBookRatings(@PathVariable int bookId, HttpSession session) {
        String sessionId = session.getId(); // Get session ID

        Map<String, Object> response = new HashMap<>();
        response.put("userRating", userRatingService.getUserRating(bookId));
        response.put("averageRating", allRatingsService.getAverageRating(bookId));
        response.put("votesCount", allRatingsService.getTotalRatings(bookId));

        return response;
    }
}

class RatingRequest {

    private int bookId;
    private int rating;

    public int getBookId() {
        return bookId;
    }

    public int getRating() {
        return rating;
    }
}
