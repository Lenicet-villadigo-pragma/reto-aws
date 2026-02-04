package pragma.example.retoaws.domain.service;

import pragma.example.retoaws.domain.model.PersonDto;

public interface PersonService {
    PersonDto savePerson(PersonDto personDto);
    PersonDto findPerson(String idNumber);
}