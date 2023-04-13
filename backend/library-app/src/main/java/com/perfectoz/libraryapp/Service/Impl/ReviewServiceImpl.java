package com.perfectoz.libraryapp.Service.Impl;

import com.perfectoz.libraryapp.Entity.Book;
import com.perfectoz.libraryapp.Entity.Review;
import com.perfectoz.libraryapp.Payload.BookDto;
import com.perfectoz.libraryapp.Payload.ReviewDto;
import com.perfectoz.libraryapp.Repository.ReviewRepository;
import com.perfectoz.libraryapp.Service.ReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;
    private ModelMapper mapper;

    public ReviewServiceImpl(ReviewRepository reviewRepository, ModelMapper mapper) {
        this.reviewRepository = reviewRepository;
        this.mapper = mapper;
    }

    @Override
    public Page<ReviewDto> getReviewByBookId(Long bookId, Pageable pageable) {
        Page<Review> reviews = reviewRepository.findByBookId(bookId, pageable);
        List<ReviewDto> reviewDtos = reviews
                .stream()
                .map(review -> mapToDTO(review))
                .collect(Collectors.toList());
        return new PageImpl<>(reviewDtos, reviews.getPageable(), reviews.getTotalElements());
    }

    private ReviewDto mapToDTO(Review review) {
        ReviewDto reviewDto = mapper.map(review, ReviewDto.class);
        return reviewDto;
    }

    private Review mapToEntity(ReviewDto reviewDto) {
        Review review = mapper.map(reviewDto, Review.class);
        return review;
    }
}
