package pragma.example.retoaws.infrastructure.in.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pragma.example.retoaws.domain.model.PersonDto;
import pragma.example.retoaws.domain.service.PersonService;

@RestController
@RequestMapping("/personas")
@Tag(name = "Personas", description = "Operaciones relacionadas con personas")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    @Operation(
            summary = "Guardar persona",
            description = "Crear registro de una nueva persona en el sistema"
    )
    public ResponseEntity<PersonDto> savePerson(@RequestBody @Valid PersonDto persona) {
        PersonDto saved = personService.savePerson(persona);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{nroIdentificacion}")
    @Operation(
            summary = "Obtener persona por ID",
            description = "Devuelve la información de una persona según su identificador único"
    )
    public ResponseEntity<PersonDto> findPerson(@PathVariable String nroIdentificacion) {
        return  ResponseEntity.ok(personService.findPerson(nroIdentificacion));
    }
}