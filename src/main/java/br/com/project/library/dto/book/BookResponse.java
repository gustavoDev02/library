package br.com.project.library.dto.book;

import br.com.project.library.enums.BookTypes;
import br.com.project.library.models.Book;

import java.time.LocalDate;

public record BookResponse(
        Long id,
        String title,
        String author,
        String genre,
        String publisher,
        LocalDate releaseDate,
        BookTypes bookType
) {
    public static BookResponse from(Book b) {
        return new BookResponse(
                b.getId(),
                b.getTitle(),
                b.getAuthor(),
                b.getGenre(),
                b.getPublisher(),
                b.getReleaseDate(),
                b.getBookType()
        );
    }
}
