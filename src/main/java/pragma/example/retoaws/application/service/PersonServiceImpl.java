package pragma.example.retoaws.application.service;

import pragma.example.retoaws.application.exception.PersonExistException;
import pragma.example.retoaws.application.exception.PersonNotFoundException;
import pragma.example.retoaws.domain.model.PersonDto;
import pragma.example.retoaws.domain.repository.PersonRepository;
import pragma.example.retoaws.domain.service.PersonService;

public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public PersonDto savePerson(PersonDto personDto) {
        personAlreadyExist(personDto.getNroIdentificacion());
        return personRepository.save(personDto);
    }

    @Override
    public PersonDto findPerson(String idNumber) {
        return personRepository.findByIdNumber(idNumber)
                .orElseThrow(() -> new PersonNotFoundException(
                        String.format("La persona con el número de identificación %s no fue encontrada.", idNumber)
                ));
    }

    private void personAlreadyExist(String idNumber) {
      if (personRepository.findByIdNumber(idNumber).isPresent()) {
            throw new PersonExistException(String.format("La persona con el número de identificación %s ya existe.", idNumber));
      }
    }
}