package hr.fer.progi.backend.repository.exception;

import hr.fer.progi.backend.repository.exception.InputIsNullException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler { // umjesto error 500 prikazuje error message

    @ExceptionHandler(InputIsNullException.class)
    public ResponseEntity<String> handleInputIsNullException(InputIsNullException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
