package pragma.example.retoaws.application.exception;

public class PersonExistException extends RuntimeException {
    public PersonExistException(String message) {
        super(message);
    }

}