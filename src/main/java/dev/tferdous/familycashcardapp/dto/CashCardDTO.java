package dev.tferdous.familycashcardapp.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode
public class CashCardDTO {
    private Double amount;
    private String owner;
}
