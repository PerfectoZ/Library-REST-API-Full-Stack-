package com.perfectoz.libraryapp.Payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {
    private String userEmail;
    private Date date;
    private double rating;
    private Long bookId;
    private String reviewDescription;
}
