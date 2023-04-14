package com.perfectoz.libraryapp.Service.Impl;

import com.perfectoz.libraryapp.Entity.Book;
import com.perfectoz.libraryapp.Entity.Checkout;
import com.perfectoz.libraryapp.Exceptions.ResourceNotFoundException;
import com.perfectoz.libraryapp.Payload.BookDto;
import com.perfectoz.libraryapp.Payload.BookResponse;
import com.perfectoz.libraryapp.Repository.BookRepository;
import com.perfectoz.libraryapp.Repository.CheckoutRepository;
import com.perfectoz.libraryapp.Service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;
    private ModelMapper mapper;
    private CheckoutRepository checkoutRepository;

    public BookServiceImpl(BookRepository bookRepository, ModelMapper mapper, CheckoutRepository checkoutRepository) {
        this.bookRepository = bookRepository;
        this.mapper = mapper;
        this.checkoutRepository = checkoutRepository;
    }

    @Override
    public BookDto getBookbyId(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Book","id",id)
        );
        return mapToDTO(book);
    }

    @Override
    public BookResponse getAllBooks(int pageNo, int pageSize, String sortBy, boolean desc) {
        Sort sort = desc ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Book> books = bookRepository.findAll(pageable);
        List<Book> listOfBooks = books.getContent();
        List<BookDto> content =  listOfBooks.stream().map(bk -> mapToDTO(bk)).collect(Collectors.toList());
        BookResponse bookResponse = new BookResponse();
        bookResponse.setBooks(content);
        bookResponse.setPageNo(books.getNumber());
        bookResponse.setPageSize(books.getSize());
        bookResponse.setTotalElements(books.getTotalElements());
        bookResponse.setTotalPages(books.getTotalPages());
        bookResponse.setLast(books.isLast());
        return bookResponse;
    }

    @Override
    public BookDto createBook(BookDto bookDto) {
        Book book = mapToEntity(bookDto);
        Book newbook = bookRepository.save(book);
        BookDto bookResponse = mapToDTO(newbook);
        return bookResponse;
    }

    @Override
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Book", "id", id)
        );
        bookRepository.delete(book);
    }

    @Override
    public BookDto updateBook(BookDto bookDto, Long id) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Book", "id", id)
        );
        book.setAuthor(bookDto.getAuthor());
        book.setDescription(bookDto.getDescription());
        book.setImg(bookDto.getImg());
        book.setCopies(bookDto.getCopies());
        book.setTitle(bookDto.getTitle());
        book.setCopiesAvailable(bookDto.getCopiesAvailable());
        book.setCategory(bookDto.getCategory());
        Book updatedBook = bookRepository.save(book);
        return mapToDTO(updatedBook);
    }

    @Override
    public Page<BookDto> findByTitleContaining(String title, Pageable pageable) {
        Page<Book> books = bookRepository.findByTitleContaining(title, pageable);
        List<BookDto> bookDtos = books
                .stream()
                .map(book -> mapToDTO(book))
                .collect(Collectors.toList());
        return new PageImpl<>(bookDtos, books.getPageable(), books.getTotalElements());
    }

    @Override
    public Page<BookDto> findByCategory(String category, Pageable pageable) {
        Page<Book> books = bookRepository.findByCategory(category, pageable);
        List<BookDto> bookDtos = books
                .stream()
                .map(book -> mapToDTO(book))
                .collect(Collectors.toList());
        return new PageImpl<>(bookDtos, books.getPageable(), books.getTotalElements());
    }

    @Override
    public BookDto checkoutBook(String userEmail, Long bookId) throws Exception {
        Optional<Book> book = bookRepository.findById(bookId);
        Checkout validateCheckout = checkoutRepository.findByUserEmailAndBookId(userEmail, bookId);

        if(!book.isPresent() || validateCheckout != null || book.get().getCopiesAvailable() <= 0) {
            throw new Exception("Book doesn't exist or already checked out by user");
        }
        book.get().setCopiesAvailable(book.get().getCopiesAvailable()-1);
        bookRepository.save(book.get());

        Checkout checkout = new Checkout(
                userEmail,
                LocalDate.now().toString(),
                LocalDate.now().plusDays(7).toString(),
                book.get().getId()
        );

        checkoutRepository.save(checkout);
        return mapToDTO(book.get());
    }

    @Override
    public Boolean checkoutBookByUser(String userEmail, Long bookId) {
        Checkout validateCheckout = checkoutRepository.findByUserEmailAndBookId(userEmail, bookId);
        return validateCheckout!=null;
    }

    @Override
    public Integer currentLoansCount(String userEmail) {
        return checkoutRepository.findBooksByUserEmail(userEmail).size();
    }

    private BookDto mapToDTO(Book book) {
        BookDto bookDto = mapper.map(book, BookDto.class);
        return bookDto;
    }

    private Book mapToEntity(BookDto bookDto) {
        Book book = mapper.map(bookDto, Book.class);
        return book;
    }
}
