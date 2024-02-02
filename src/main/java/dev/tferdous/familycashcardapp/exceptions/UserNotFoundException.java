package dev.tferdous.familycashcardapp.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends RuntimeException {

    public static final HttpStatus STATUS = HttpStatus.NOT_FOUND;

    public UserNotFoundException() {
        super("Specified user not found.");
    }

    public UserNotFoundException(Long id) {
        super("User with id = " + id + " was not found.");
    }
}
