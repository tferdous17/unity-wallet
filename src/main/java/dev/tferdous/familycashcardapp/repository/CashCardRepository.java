package dev.tferdous.familycashcardapp.repository;

import dev.tferdous.familycashcardapp.cashcard.CashCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CashCardRepository extends JpaRepository<Long, CashCard> {
}
