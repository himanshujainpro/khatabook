package io.khatabook.handler;

import io.khatabook.exception.DataNotExistException;
import io.khatabook.exception.DuplicateEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {


    @ExceptionHandler({DuplicateEntityException.class})
    public ResponseEntity<String> handleDuplicateEntity(DuplicateEntityException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler({DataNotExistException.class})
    public ResponseEntity<String> handleDataNotExist(DataNotExistException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
}
