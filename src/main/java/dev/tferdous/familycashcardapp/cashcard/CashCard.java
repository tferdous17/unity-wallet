package dev.tferdous.familycashcardapp.cashcard;

public class CashCard {
    private Long id;
    private Double amount;
    private String owner;

    public CashCard() {}

    public CashCard(Double amount, String owner) {
        this.amount = amount;
        this.owner = owner;
    }

}
