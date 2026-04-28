package br.com.project.library.dto.reader;

import br.com.project.library.models.Reader;

public record ReaderResponse(
         Long id,
         String name,
         String email,
         String phone
) {
    public static ReaderResponse fromEntity(Reader r){
        return new ReaderResponse(
                r.getId(),
                r.getName(),
                r.getEmail(),
                r.getPhone()
        );
    }
}
