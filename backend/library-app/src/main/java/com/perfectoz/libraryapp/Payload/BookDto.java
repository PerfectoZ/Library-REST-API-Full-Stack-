package com.perfectoz.libraryapp.Payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private String title;
    private String author;
    private String description;
    private int copies;
    private int copiesAvailable;
    private String img;
    private String category;
}
