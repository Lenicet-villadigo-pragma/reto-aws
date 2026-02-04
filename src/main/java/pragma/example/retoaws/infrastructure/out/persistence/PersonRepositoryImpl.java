package pragma.example.retoaws.infrastructure.out.persistence;

import pragma.example.retoaws.domain.model.PersonDto;
import pragma.example.retoaws.domain.repository.PersonRepository;

import java.util.Optional;

public class PersonRepositoryImpl implements PersonRepository {

    private final PersonJpaRepository jpaRepository;

    public PersonRepositoryImpl(PersonJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public PersonDto save(PersonDto personDto) {
        PersonEntity entity = new PersonEntity();
        entity.setIdNumber(personDto.getNroIdentificacion());
        entity.setName(personDto.getNombre());
        entity.setEmail(personDto.getEmail());
        PersonEntity saved = jpaRepository.save(entity);
        PersonDto result = new PersonDto();
        result.setNroIdentificacion(saved.getIdNumber());
        result.setNombre(saved.getName());
        result.setEmail(saved.getEmail());
        return result;
    }

    @Override
    public Optional<PersonDto> findByIdNumber(String idNumber) {
        return jpaRepository.findById(idNumber)
                .map(entity -> {
                    PersonDto personDto = new PersonDto();
                    personDto.setNroIdentificacion(entity.getIdNumber());
                    personDto.setNombre(entity.getName());
                    personDto.setEmail(entity.getEmail());
                    return personDto;
                });
    }
}