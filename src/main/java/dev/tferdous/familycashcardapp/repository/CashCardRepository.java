package dev.tferdous.familycashcardapp.repository;

import dev.tferdous.familycashcardapp.cashcard.CashCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashCardRepository extends JpaRepository<Long, CashCard> {
}
