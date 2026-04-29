package br.com.project.library.services;

import br.com.project.library.models.Reader;
import br.com.project.library.repositories.ReaderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ReaderService {

    private ReaderRepository repository;

    public ReaderService(ReaderRepository repository){
        this.repository = repository;
    }

    public Reader addReader(Reader reader) {
        return repository.save(reader);
    }

    public List<Reader> findAll() {
        return repository.findAll();
    }

    public Reader updateReader(Long id, Reader newReader) {
        var optionalReader = getReaderById(id);
        if (optionalReader.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reader não encontrado");
        }
        newReader.setId(id);
        return repository.save(newReader);
    }

    public Optional<Reader> getReaderById(Long id) {
        return repository.findById(id);
    }

    public void deleteReader(Long id) {
        var optionalReader = getReaderById(id);
        if (optionalReader.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reader não encontrado");
        }

        repository.deleteById(id);
    }
}
