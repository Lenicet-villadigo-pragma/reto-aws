package pragma.example.retoaws.infrastructure.in.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import pragma.example.retoaws.domain.model.PersonDto;
import pragma.example.retoaws.domain.service.PersonService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonController.class)
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PersonService personService;

    @Autowired
    private ObjectMapper objectMapper;

    private PersonDto personDto;

    @BeforeEach
    void setUp() {
        personDto = new PersonDto();
        personDto.setNroIdentificacion("123456");
        personDto.setNombre("Juan Perez");
        personDto.setEmail("juan.perez@example.com");
    }

    @Test
    void savePerson_ShouldReturnSavedPerson() throws Exception {
        //Arrange
        when(personService.savePerson(any(PersonDto.class))).thenReturn(personDto);

        //Act & Assert
        mockMvc.perform(post("/personas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(personDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nroIdentificacion").value("123456"))
                .andExpect(jsonPath("$.nombre").value("Juan Perez"))
                .andExpect(jsonPath("$.email").value("juan.perez@example.com"));
    }

    @Test
    void findPerson_ShouldReturnPerson_WhenExists() throws Exception {
        //Arrange
        when(personService.findPerson(anyString())).thenReturn(personDto);

        //Act & Assert
        mockMvc.perform(get("/personas/{nroIdentificacion}", "123456"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nroIdentificacion").value("123456"))
                .andExpect(jsonPath("$.nombre").value("Juan Perez"));
    }

    @Test
    void savePerson_ShouldReturnBadRequest_WhenInvalidData() throws Exception {
        //Arrange
        PersonDto invalidPerson = new PersonDto(); // Missing fields

        //Act & Assert
        mockMvc.perform(post("/personas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidPerson)))
                .andExpect(status().isBadRequest());
    }
}
