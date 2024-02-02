package dev.tferdous.familycashcardapp.exceptions;

import dev.tferdous.familycashcardapp.exceptions.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {

    @ExceptionHandler(CashCardNotFoundException.class)
    public ResponseEntity<Object> handleCashCardNotFoundException(CashCardNotFoundException exception) {
        ErrorResponse response = ErrorResponse.builder()
                .message(exception.getMessage())
                .status(CashCardNotFoundException.STATUS)
                .statusCode(CashCardNotFoundException.STATUS.value())
                .build();

        return ResponseEntity.status(CashCardNotFoundException.STATUS).body(response);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<Object> handleEmailAlreadyExistsException(EmailAlreadyExistsException exception) {
        ErrorResponse response = ErrorResponse.builder()
                .message(exception.getMessage())
                .status(EmailAlreadyExistsException.STATUS)
                .statusCode(EmailAlreadyExistsException.STATUS.value())
                .build();

        return ResponseEntity.status(EmailAlreadyExistsException.STATUS).body(response);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException exception) {
        ErrorResponse response = ErrorResponse.builder()
                .message(exception.getMessage())
                .status(UserNotFoundException.STATUS)
                .statusCode(UserNotFoundException.STATUS.value())
                .build();

        return ResponseEntity.status(UserNotFoundException.STATUS).body(response);
    }
}
