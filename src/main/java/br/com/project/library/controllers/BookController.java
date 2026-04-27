package br.com.project.library.controllers;
import java.util.ArrayList;
import br.com.project.library.models.Book;
import br.com.project.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getBooks(){
        return bookService.getBook();
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        var books = bookService.addBook(book);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id){
        return bookService.getBookById(id)
                .map((b) -> ResponseEntity.ok(b))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book newBook){
        Book book = bookService.upadateBook(id, newBook);
        return ResponseEntity.ok(book);
    }
}
