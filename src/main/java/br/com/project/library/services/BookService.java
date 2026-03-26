package br.com.project.library.services;

import br.com.project.library.models.Book;
import br.com.project.library.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service // tornando o BookService um componente para ser injetado
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getBook(){return bookRepository.findAll();}

    public Book addBook(Book book){
        return bookRepository.save(book);
    }

    public Optional<Book> getBookById(Long id){
     return bookRepository.findById(id);
    }

    public void deleteBook(Long id){
        //criando uma variavel que é o retorno do Livro por id
        var optionalBook = getBookById(id);

        //se o nao tiver livro retorna o throw Statys
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

        return bookRepository.save(newBook);
    }
}
