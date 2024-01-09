package dev.tferdous.familycashcardapp.mapper;

import dev.tferdous.familycashcardapp.dto.CashCardDTO;
import dev.tferdous.familycashcardapp.model.entity.CashCard;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CashCardMapper {

    public CashCardDTO toDTO(CashCard card) {
        return CashCardDTO.builder()
                .amount(card.getAmount())
                .owner(card.getOwner())
                .build();
    }
}
