package br.com.project.library.repositories;

import br.com.project.library.enums.BookTypes;
import br.com.project.library.models.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface BookRepository extends JpaRepository<Book, Long>{
    Page<Book> findByTitleContainingIgnoreCase(String title, Pageable pageable);
    Page<Book> findByAuthorContainingIgnoreCase(String author, Pageable pageable);
    Page<Book> findByBookType(BookTypes type, Pageable pageable);
    Page<Book> findByReleaseDateBetween(LocalDate start, LocalDate end, Pageable pageable);
    Page<Book> findByPublisherContainingIgnoreCase(String publisher, Pageable pageable);
    Page<Book> findByGenreContainingIgnoreCase(String genre, Pageable pageable);

    Page<Book> findAllProjectedBy(Pageable pageable);
}
