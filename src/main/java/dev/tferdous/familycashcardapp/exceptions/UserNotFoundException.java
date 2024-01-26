package dev.tferdous.familycashcardapp.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("User not found.");
    }
}
