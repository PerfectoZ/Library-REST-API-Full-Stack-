package com.perfectoz.libraryapp.Repository;

import com.perfectoz.libraryapp.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
