package br.com.project.library.models;

import br.com.project.library.enums.BookTypes;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Table(name = "TB_BOOK")
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private String genre;
    private String publisher;
    private LocalDate releaseDate;

    @Enumerated(EnumType.STRING)
    private BookTypes bookType;

    @ManyToMany(mappedBy = "books")
    private List<Reader> readers = new ArrayList<>();

    @OneToMany(mappedBy = "book")
    private List<Review> reviews = new ArrayList<>();
}
