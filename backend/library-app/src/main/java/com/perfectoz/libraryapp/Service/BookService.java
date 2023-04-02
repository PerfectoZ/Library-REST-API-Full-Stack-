package com.perfectoz.libraryapp.Service;

import com.perfectoz.libraryapp.Payload.BookDto;
import com.perfectoz.libraryapp.Payload.BookResponse;

public interface BookService {
    public BookDto getBookbyId(Long id);
    public BookResponse getAllBooks(int pageNo, int pageSize, String sortBy, boolean desc);
    public BookDto createBook(BookDto bookDto);
    public void deleteBook(Long id);
    public BookDto updateBook(BookDto bookDto, Long id);
}
