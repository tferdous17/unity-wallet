package dev.tferdous.familycashcardapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class CashCard {
    @Id
    @SequenceGenerator(
            name = "card_sequence",
            sequenceName = "card_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "card_sequence"
    )
    private Long id;
    private Double amount;
    private String owner;

    public CashCard() {}

    public CashCard(Double amount, String owner) {
        this.amount = amount;
        this.owner = owner;
    }

}
