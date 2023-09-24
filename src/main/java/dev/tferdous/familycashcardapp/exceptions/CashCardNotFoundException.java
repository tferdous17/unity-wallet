package dev.tferdous.familycashcardapp.exceptions;

public class CashCardNotFoundException extends RuntimeException {
    public CashCardNotFoundException(Long id) {
        super("Entity not found: Cash Card with id = " + id);
    }
}
