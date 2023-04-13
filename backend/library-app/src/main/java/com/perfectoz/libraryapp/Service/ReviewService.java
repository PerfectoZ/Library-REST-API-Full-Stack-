package com.perfectoz.libraryapp.Service;

import com.perfectoz.libraryapp.Payload.ReviewDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

public interface ReviewService {
    public Page<ReviewDto> getReviewByBookId(Long bookId, Pageable pageable);
}
