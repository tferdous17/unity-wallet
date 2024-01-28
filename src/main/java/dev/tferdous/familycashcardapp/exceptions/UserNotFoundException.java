package dev.tferdous.familycashcardapp.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("Specified user not found.");
    }

    public UserNotFoundException(Long id) {
        super("User with id = " + id + " was not found.");
    }
}
