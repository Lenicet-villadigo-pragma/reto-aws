package pragma.example.retoaws.application.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pragma.example.retoaws.application.exception.PersonExistException;
import pragma.example.retoaws.application.exception.PersonNotFoundException;
import pragma.example.retoaws.domain.model.PersonDto;
import pragma.example.retoaws.domain.repository.PersonRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonServiceImpl personService;

    private PersonDto personDto;

    @BeforeEach
    void setUp() {
        personDto = new PersonDto();
        personDto.setNroIdentificacion("123456");
        personDto.setNombre("Juan Perez");
        personDto.setEmail("juan.perez@example.com");
    }

    @Test
    void savePerson_ShouldSavePerson_WhenNotExists() {
        // Arrange
        when(personRepository.findByIdNumber(personDto.getNroIdentificacion())).thenReturn(Optional.empty());
        when(personRepository.save(any(PersonDto.class))).thenReturn(personDto);

        // Act
        PersonDto savedPerson = personService.savePerson(personDto);

        // Assert
        assertNotNull(savedPerson);
        assertEquals(personDto.getNroIdentificacion(), savedPerson.getNroIdentificacion());
        verify(personRepository, times(1)).save(personDto);
    }

    @Test
    void savePerson_ShouldThrowPersonExistException_WhenAlreadyExists() {
        // Arrange
        when(personRepository.findByIdNumber(personDto.getNroIdentificacion())).thenReturn(Optional.of(personDto));

        // Act & Assert
        assertThrows(PersonExistException.class, () -> personService.savePerson(personDto));
        verify(personRepository, never()).save(any(PersonDto.class));
    }

    @Test
    void findPerson_ShouldReturnPerson_WhenExists() {
        // Arrange
        when(personRepository.findByIdNumber("123456")).thenReturn(Optional.of(personDto));

        // Act
        PersonDto foundPerson = personService.findPerson("123456");

        // Assert
        assertNotNull(foundPerson);
        assertEquals("123456", foundPerson.getNroIdentificacion());
    }

    @Test
    void findPerson_ShouldThrowPersonNotFoundException_WhenNotExists() {
        // Arrange
        when(personRepository.findByIdNumber("999")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(PersonNotFoundException.class, () -> personService.findPerson("999"));
    }
}
