package br.com.project.library.dto.book;

import br.com.project.library.enums.BookTypes;
import br.com.project.library.models.Book;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record BookRequest (

        @NotBlank(message = "title is required")
        String title,

        @NotBlank(message = "author is required")
        String author,

        @NotBlank(message = "genre is required")
        String genre,

        @NotBlank(message = "publisher is required")
        String publisher,

        @PastOrPresent(message = "the release date must not be in future")
        LocalDate releaseDate,

        @NotBlank(message = "O tipo do livro é obrigatório")
        BookTypes bookType
)
{
    public Book toEntity() {
        return Book.builder()
                .title(title)
                .author(author)
                .genre(genre)
                .publisher(publisher)
                .releaseDate(releaseDate)
                .bookType(bookType)
                .build();
    }
}
