package dev.tferdous.familycashcardapp.repository;

import dev.tferdous.familycashcardapp.model.entity.CashCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository layer with custom defined find methods
 */
@Repository
public interface CashCardRepository extends JpaRepository<CashCard, Long> {
    /**
     * This method is meant to retrieve a cash card using both the ID of the card
     * and the name of the owner
     * @param id ID of card
     * @param owner Owner of card
     * @return CashCard if found
     */
    CashCard findByIdAndOwner(Long id, String owner);

    /**
     * This method is meant to find all the cash cards owned by any particular owner
     * @param owner Owner of card(s)
     * @param amount PageRequest obj
     * @return Page of CashCard(s)
     */
    Page<CashCard> findByOwner(String owner, PageRequest amount);

}
