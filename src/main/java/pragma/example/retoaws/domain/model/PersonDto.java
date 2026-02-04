package pragma.example.retoaws.domain.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PersonDto {

    @NotBlank
    private String nroIdentificacion;
    @NotBlank
    private String nombre;
    @Email
    private String email;

}