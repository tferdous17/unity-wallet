package dev.tferdous.familycashcardapp.exceptions;

public class CashCardNotFoundException extends RuntimeException {
    public CashCardNotFoundException(Long id) {
        super("Could not find cash card with id = " + id);
    }
}
