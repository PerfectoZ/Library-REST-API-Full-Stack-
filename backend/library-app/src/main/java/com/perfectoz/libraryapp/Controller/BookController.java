package com.perfectoz.libraryapp.Controller;

import com.perfectoz.libraryapp.Entity.Book;
import com.perfectoz.libraryapp.Entity.Checkout;
import com.perfectoz.libraryapp.Payload.BookDto;
import com.perfectoz.libraryapp.Payload.BookResponse;
import com.perfectoz.libraryapp.Repository.CheckoutRepository;
import com.perfectoz.libraryapp.Service.BookService;
import com.perfectoz.libraryapp.Util.AppConstants;
import com.perfectoz.libraryapp.Util.ExtractJWT;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
@CrossOrigin("http://localhost:3000")
public class BookController {
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{id}")
    ResponseEntity<BookDto> getBook(@PathVariable Long id) {
        return new ResponseEntity<>(bookService.getBookbyId(id), HttpStatus.OK);
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

    @GetMapping("/search")
    public ResponseEntity<Page<BookDto>> findByTitleContaining(
            @RequestParam("title") String title,
            Pageable pageable
    ) {
        Page<BookDto> books = bookService.findByTitleContaining(title, pageable);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/search/findByCategory")
    public ResponseEntity<Page<BookDto>> findByCategory(
            @RequestParam("category") String category,
            Pageable pageable
    ) {
        Page<BookDto> books = bookService.findByCategory(category, pageable);
        return ResponseEntity.ok(books);
    }

    @PutMapping("/secure/checkout")
    public ResponseEntity<BookDto> checkoutBook(@RequestParam Long bookId) throws Exception {
        String userEmail = "testuser@gmail.com";
        return ResponseEntity.ok(bookService.checkoutBook(userEmail, bookId));
    }

    @GetMapping("/secure/isCheckedOut/byUser")
    public ResponseEntity<Boolean> checkoutBookByUser(@RequestHeader(value = "Authorization") String token,
                                                      @RequestParam Long bookId) {
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        return ResponseEntity.ok(bookService.checkoutBookByUser(userEmail, bookId));
    }

    @GetMapping("/secure/currentLoans/count")
    public ResponseEntity<Integer> currentLoansCount(@RequestHeader(value = "Authorization") String token) {
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        return ResponseEntity.ok(bookService.currentLoansCount(userEmail));
    }

}
