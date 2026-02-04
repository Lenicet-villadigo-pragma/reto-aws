package pragma.example.retoaws.domain.repository;

import pragma.example.retoaws.domain.model.PersonDto;

import java.util.Optional;

public interface PersonRepository {
    PersonDto save(PersonDto personDto);
    Optional<PersonDto> findByIdNumber(String idNumber);
}