package br.com.project.library.services;

import br.com.project.library.dto.book.BookRequest;
import br.com.project.library.dto.book.BookResponse;
import br.com.project.library.enums.BookTypes;
import br.com.project.library.models.Book;
import br.com.project.library.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service // tornando o BookService um componente para ser injetado
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Page<Book> getBooks(Pageable pageable){return bookRepository.findAll(pageable);}

    public Book addBook(Book book){
        return bookRepository.save(book);
    }

    public Optional<Book> getBookById(Long id){
     return bookRepository.findById(id);
    }

    public void deleteBook(Long id){
        var optionalBook = getBookById(id);

        if(optionalBook.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "livro não encontrado");
        }

        bookRepository.deleteById(id);
    }

    public Book upadateBook(Long id, Book newBook ){
        var optionalBook = getBookById(id);
        if(optionalBook.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "livro nao encontrado");
        }
        var book = optionalBook.get();
        newBook.setId(id);
        newBook.setReviews(book.getReviews());
        newBook.setReaders(book.getReaders());

        return bookRepository.save(newBook);
    }

    public Page<Book> getBooksByTitle(String title, Pageable pageable){
        return bookRepository.findByTitleContainingIgnoreCase(title, pageable);
    }
    public Page<Book> getBooksByAuthor(String author, Pageable pageable){
        return bookRepository.findByAuthorContainingIgnoreCase(author, pageable);
    }
    public Page<Book> getBooksByPublisher(String publisher, Pageable pageable){
        return bookRepository.findByPublisherContainingIgnoreCase(publisher, pageable);
    }
    public Page<Book> getBooksByDate(LocalDate start, LocalDate end, Pageable pageable){
        return bookRepository.findByReleaseDateBetween(start, end, pageable);
    }
    public Page<Book> getBooksByGenre(String genre, Pageable pageable){
        return  bookRepository.findByGenreContainingIgnoreCase(genre, pageable);
    }
    public Page<Book> getByType(BookTypes type, Pageable pageable){
        return  bookRepository.findByType(type, pageable);
    }
}
