package com.perfectoz.libraryapp.Service;

import com.perfectoz.libraryapp.Entity.Book;
import com.perfectoz.libraryapp.Payload.BookDto;
import com.perfectoz.libraryapp.Payload.BookResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
    public BookDto getBookbyId(Long id);
    public BookResponse getAllBooks(int pageNo, int pageSize, String sortBy, boolean desc);
    public BookDto createBook(BookDto bookDto);
    public void deleteBook(Long id);
    public BookDto updateBook(BookDto bookDto, Long id);
    public Page<BookDto> findByTitleContaining(String title, Pageable pageable);
}
