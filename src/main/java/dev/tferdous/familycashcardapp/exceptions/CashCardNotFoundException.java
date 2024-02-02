package dev.tferdous.familycashcardapp.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Custom CashCardNotFound exception that's thrown when
 * a particular CashCard is not found within the data storage
 */
public class CashCardNotFoundException extends RuntimeException {

    public static final HttpStatus STATUS = HttpStatus.NOT_FOUND;

    /**
     * Throws an error if cash card with param id not found
     * @param id ID of cash card
     */
    public CashCardNotFoundException(Long id) {
        super("Entity not found: Cash Card with id = " + id);
    }
}
