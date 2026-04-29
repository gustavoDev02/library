package br.com.project.library.controllers;

import br.com.project.library.dto.book.BookRequest;
import br.com.project.library.dto.book.BookResponse;
import br.com.project.library.enums.BookTypes;
import br.com.project.library.models.Book;
import br.com.project.library.repositories.BookRepository;
import br.com.project.library.services.BookService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final BookRepository bookRepository;

    public BookController(BookService bookService, BookRepository bookRepository) {
        this.bookService = bookService;
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public Page<BookResponse> getBooks(@PageableDefault Pageable pageable){
        return bookService.getBooks(pageable).map(BookResponse::fromEntity);
    }

    @GetMapping(params = "title")
    public Page<BookResponse> findByTitle(@RequestParam String title, Pageable pageable){
        return bookRepository.findByTitleContainingIgnoreCase(title, pageable).map(BookResponse::fromEntity);
    }

    @GetMapping(params = "author")
    public Page<BookResponse> findByAuthor(@RequestParam String author, Pageable pageable){
        return bookService.getBooksByAuthor(author, pageable).map(BookResponse::fromEntity);
    }

    @GetMapping(params = "genre")
    public Page<BookResponse> findByGenre(@RequestParam String genre, Pageable pageable){
        return bookService.getBooksByGenre(genre, pageable).map(BookResponse::fromEntity);
    }

    @GetMapping(params = "publisher")
    public Page<BookResponse> findByPublisher(@RequestParam String publisher, Pageable pageable){
        return bookService.getBooksByPublisher(publisher, pageable).map(BookResponse::fromEntity);
    }

    @GetMapping(params = "type")
    public Page<BookResponse> findByType(@RequestParam BookTypes type, Pageable pageable){
        return bookService.getByType(type, pageable).map(BookResponse::fromEntity);
    }

    @GetMapping(params = {"start", "end"})
    public Page<BookResponse> findByDateRange(@RequestParam LocalDate start, @RequestParam LocalDate end, Pageable pageable){
        return bookService.getBooksByDate(start, end, pageable).map(BookResponse::fromEntity);
    }

    @PostMapping
    public ResponseEntity<BookResponse> addBook(@RequestBody @Valid BookRequest bookRequest){
        Book book = bookRequest.toEntity();
        Book savedBook = bookRepository.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(BookResponse.fromEntity(savedBook));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable Long id){
        return bookService.getBookById(id)
                .map((b) -> ResponseEntity.ok(BookResponse.fromEntity(b)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable Long id, @RequestBody @Valid BookRequest newBookRequest){
        Book book = bookService.upadateBook(id, newBookRequest.toEntity());
        return ResponseEntity.ok(BookResponse.fromEntity(book));
    }
}
