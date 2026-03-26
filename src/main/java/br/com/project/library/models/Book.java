package br.com.project.library.models;

import br.com.project.library.enums.BookTypes;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "TB_BOOK ")
public class Book {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private String publisher;
    private LocalDate releaseDate;
    private BookTypes bookType;
}
