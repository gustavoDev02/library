package br.com.project.library.controllers;

import br.com.project.library.dto.reader.ReaderRequest;
import br.com.project.library.dto.reader.ReaderResponse;
import br.com.project.library.models.Reader;
import br.com.project.library.repositories.ReaderRepository;
import br.com.project.library.services.ReaderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/readers")
public class ReaderController {

    private final ReaderService service;
    private final ReaderRepository repository;

    public ReaderController(ReaderService service, ReaderRepository repository){
        this.service = service;
        this.repository = repository;
    }

    @GetMapping
    public List<ReaderResponse> findAll(){
        return service.findAll().stream()
                .map(ReaderResponse::fromEntity)
                .toList();    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Reader addReader(@RequestBody @Valid ReaderRequest readerRequest){
        return service.addReader(readerRequest.toEntity());
    }

    @PutMapping("{id}")
    public ResponseEntity<Reader> updateReader(@PathVariable Long id, @RequestBody Reader newReader){
        Reader reader = service.updateReader(id, newReader);
        return ResponseEntity.ok(reader);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteReader(@PathVariable Long id){
        service.deleteReader(id);
        return ResponseEntity.noContent().build();
    }

}
