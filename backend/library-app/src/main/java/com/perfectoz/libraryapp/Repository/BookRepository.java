package com.perfectoz.libraryapp.Repository;

import com.perfectoz.libraryapp.Entity.Book;
import com.perfectoz.libraryapp.Payload.BookDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query(value = "SELECT * FROM book WHERE title ILIKE %?1%",
            nativeQuery = true)
    Page<Book> findByTitleContaining(String title, Pageable pageable);

    @Query("SELECT b FROM Book b WHERE lower(b.category) = lower(:category)")
    Page<Book> findByCategory(String category, Pageable pageable);
}
