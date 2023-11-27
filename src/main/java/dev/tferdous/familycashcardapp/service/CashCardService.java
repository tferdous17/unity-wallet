package dev.tferdous.familycashcardapp.service;

import dev.tferdous.familycashcardapp.exceptions.CashCardNotFoundException;
import dev.tferdous.familycashcardapp.entity.CashCard;
import dev.tferdous.familycashcardapp.repository.CashCardRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CashCardService {
    private final CashCardRepository repository;

    /**
     * Inject CashCardRepository object
     * @param repository CashCardRepository object
     */
    @Autowired
    public CashCardService(CashCardRepository repository) {
        this.repository = repository;
    }

    /**
     * Return list of CashCards
     * @return list of CashCard
     */
    public List<CashCard> getAllCashCards() {
        return repository.findAll();
    }

    /**
     * Creates a new CashCard using the card param passed in
     * @param card CashCard to create
     * @return newly created CashCard
     */
    public CashCard createCashCard(CashCard card) {
        return repository.save(card);
    }

    /**
     * Allows you to find any CashCard by its ID
     * @param requestedId id of CashCard to find
     * @return CashCard if it exists (optional)
     */
    public Optional<CashCard> findById(Long requestedId) {
        return repository.findById(requestedId);
    }

    /**
     * Updates any CashCard if its owned by a particular person
     * Only the cash amount is update-able
     * @param id ID of CashCard
     * @param card CashCard being used to update current card with
     * @param principal Entity for cash card owner
     * @return status code 204 if success else 404
     */
    public ResponseEntity<Void> updateCard(Long id, CashCard card, Principal principal) {
        CashCard cardToUpdate = repository.findByIdAndOwner(id, principal.getName());
        if (cardToUpdate != null) {
            cardToUpdate.setAmount(card.getAmount());
            return ResponseEntity.noContent().build(); // request successfully completed but no msg body
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Deletes a card owner's CashCard
     * @param id ID of card to delete
     * @param principal Entity for cash card owner
     * @return status code 204 if success else 404
     */
    public ResponseEntity<Void> deleteCard(Long id, Principal principal) {
        CashCard cardToDelete = repository.findByIdAndOwner(id, principal.getName());
        if (cardToDelete != null) {
            repository.delete(cardToDelete);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Retrieves page of all cash cards owned by a particular person
     * @param owner Owner of cash cards
     * @param pageable Pageable object used for pagination
     * @return page of all owned cards
     */
    public Page<CashCard> getAllCashCardsByOwner(String owner, Pageable pageable) {
        return repository.findByOwner(owner, PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                pageable.getSortOr(Sort.by(Sort.Direction.DESC, "amount")))
        );
    }

    /**
     * Finds a cash card using card ID and name of card owner
     * @param requestedId ID of card
     * @param name Name of owner
     * @return optional of cash card
     */
    public Optional<CashCard> findByIdAndOwner(Long requestedId, String name) {
        return Optional.ofNullable(repository.findByIdAndOwner(requestedId, name));
    }
}
