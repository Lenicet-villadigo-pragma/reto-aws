package pragma.example.retoaws.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pragma.example.retoaws.application.service.PersonServiceImpl;
import pragma.example.retoaws.domain.repository.PersonRepository;
import pragma.example.retoaws.domain.service.PersonService;
import pragma.example.retoaws.infrastructure.out.persistence.PersonJpaRepository;
import pragma.example.retoaws.infrastructure.out.persistence.PersonRepositoryImpl;

@Configuration
public class BeanConfig {

    @Bean
    public PersonRepository personRepository(PersonJpaRepository personJpaRepository) {
        return new PersonRepositoryImpl(personJpaRepository);
    }

    @Bean
    public PersonService personService(PersonRepository personRepository) {
        return new PersonServiceImpl(personRepository);
    }
}
