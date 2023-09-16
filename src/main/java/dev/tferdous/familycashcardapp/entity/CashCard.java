package dev.tferdous.familycashcardapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class CashCard {
    @Id
    private Long id;
    private Double amount;
    private String owner;

    public CashCard() {}

    public CashCard(Double amount, String owner) {
        this.amount = amount;
        this.owner = owner;
    }

}
