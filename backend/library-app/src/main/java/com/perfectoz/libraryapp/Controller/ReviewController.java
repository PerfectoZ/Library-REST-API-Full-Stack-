package com.perfectoz.libraryapp.Controller;

import com.perfectoz.libraryapp.Payload.BookDto;
import com.perfectoz.libraryapp.Payload.ReviewDto;
import com.perfectoz.libraryapp.Service.ReviewService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/search/findByBook")
    public ResponseEntity<Page<ReviewDto>> findByBook(
            @RequestParam("bookId") Long bookId,
            Pageable pageable
    ) {
        Page<ReviewDto> reviews = reviewService.getReviewByBookId(bookId, pageable);
        return ResponseEntity.ok(reviews);
    }
}
