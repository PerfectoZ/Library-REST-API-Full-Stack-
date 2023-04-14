package com.perfectoz.libraryapp.Service;

import com.perfectoz.libraryapp.Entity.Book;
import com.perfectoz.libraryapp.Entity.Checkout;
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
    public Page<BookDto> findByCategory(String category, Pageable pageable);
    public BookDto checkoutBook(String userEmail, Long bookId) throws Exception ;
    public Boolean checkoutBookByUser(String userEmail, Long bookId);
    public Integer currentLoansCount(String userEmail);
}
