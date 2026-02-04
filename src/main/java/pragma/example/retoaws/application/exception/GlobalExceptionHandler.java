package pragma.example.retoaws.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePersonaNotFound(PersonNotFoundException ex) {
        ErrorResponse error = new ErrorResponse("PERSONA_NO_ENCONTRADA", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PersonExistException.class)
    public ResponseEntity<ErrorResponse> handlePersonaExist(PersonExistException ex) {
        ErrorResponse error = new ErrorResponse("PERSONA_EXISTE", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

}