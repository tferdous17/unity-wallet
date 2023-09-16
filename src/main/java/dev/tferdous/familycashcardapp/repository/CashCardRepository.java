package dev.tferdous.familycashcardapp.repository;

import dev.tferdous.familycashcardapp.entity.CashCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CashCardRepository extends JpaRepository<CashCard, Long> {
}
