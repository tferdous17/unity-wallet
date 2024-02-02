package dev.tferdous.familycashcardapp.exceptions;

import org.springframework.http.HttpStatus;

public class EmailAlreadyExistsException extends RuntimeException {
    public static final HttpStatus STATUS = HttpStatus.CONFLICT;

    public EmailAlreadyExistsException(String email) {
        super("Email " + email + " already exists.");
    }
}
