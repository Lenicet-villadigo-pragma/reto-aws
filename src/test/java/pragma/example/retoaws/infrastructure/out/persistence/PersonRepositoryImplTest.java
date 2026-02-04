package pragma.example.retoaws.infrastructure.out.persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pragma.example.retoaws.domain.model.PersonDto;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonRepositoryImplTest {

    @Mock
    private PersonJpaRepository jpaRepository;

    @InjectMocks
    private PersonRepositoryImpl personRepository;

    private PersonDto personDto;
    private PersonEntity personEntity;

    @BeforeEach
    void setUp() {
        personDto = new PersonDto();
        personDto.setNroIdentificacion("123456");
        personDto.setNombre("Juan Perez");
        personDto.setEmail("juan.perez@example.com");

        personEntity = new PersonEntity();
        personEntity.setIdNumber("123456");
        personEntity.setName("Juan Perez");
        personEntity.setEmail("juan.perez@example.com");
    }

    @Test
    void save_ShouldReturnSavedPersonDto() {
        // Arrange
        when(jpaRepository.save(any(PersonEntity.class))).thenReturn(personEntity);

        // Act
        PersonDto result = personRepository.save(personDto);

        // Assert
        assertNotNull(result);
        assertEquals(personDto.getNroIdentificacion(), result.getNroIdentificacion());
        assertEquals(personDto.getNombre(), result.getNombre());
        assertEquals(personDto.getEmail(), result.getEmail());
        verify(jpaRepository, times(1)).save(any(PersonEntity.class));
    }

    @Test
    void findByIdNumber_WhenExists_ShouldReturnPersonDto() {
        // Arrange
        when(jpaRepository.findById("123456")).thenReturn(Optional.of(personEntity));

        // Act
        Optional<PersonDto> result = personRepository.findByIdNumber("123456");

        // Assert
        assertTrue(result.isPresent());
        assertEquals("123456", result.get().getNroIdentificacion());
        assertEquals("Juan Perez", result.get().getNombre());
    }

    @Test
    void findByIdNumber_WhenNotExists_ShouldReturnEmpty() {
        // Arrange
        when(jpaRepository.findById("999")).thenReturn(Optional.empty());

        // Act
        Optional<PersonDto> result = personRepository.findByIdNumber("999");

        // Assert
        assertFalse(result.isPresent());
    }
}
