package br.com.project.library.dto.reader;

import br.com.project.library.models.Reader;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public record ReaderRequest(

         @NotBlank(message = "Name is required")
         String name,

         @NotBlank(message = "CPF is required")
         @CPF(message = "CPF is invalid")
         String cpf,

         @NotBlank(message = "Email is required")
         @Email(message = "Email is invalid")
         String email,

         @Size(min = 9, max = 67)
         String password,

         @Size(min = 11, max = 14)
         String phone
) {
    public Reader toEntity(){
        return Reader.builder()
                .name(name)
                .cpf(cpf)
                .email(email)
                .password(password)
                .phone(phone)
                .build();
    }
}
