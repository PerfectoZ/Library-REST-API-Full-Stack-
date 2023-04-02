package com.perfectoz.libraryapp.Controller;

import com.perfectoz.libraryapp.Payload.BookDto;
import com.perfectoz.libraryapp.Payload.BookResponse;
import com.perfectoz.libraryapp.Service.BookService;
import com.perfectoz.libraryapp.Util.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books/")
public class BookController {
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{id}")
    ResponseEntity<BookDto> getBook(@PathVariable Long id) {
        return new ResponseEntity<>(bookService.getBookbyId(id), HttpStatus.OK);
    }

    @GetMapping
    public BookResponse getAllBooks(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "desc", defaultValue = AppConstants.DEFAULT_DESC, required = false) boolean desc
    ) {
        return bookService.getAllBooks(pageNo, pageSize, sortBy, desc);
    }

    @PostMapping
    public ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto) {
        return ResponseEntity.ok(bookService.createBook(bookDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok("Deleted Successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable Long id, @RequestBody BookDto bookDto) {
        BookDto resp = bookService.updateBook(bookDto, id);
        return new ResponseEntity<>(bookDto, HttpStatus.OK);
    }
}
