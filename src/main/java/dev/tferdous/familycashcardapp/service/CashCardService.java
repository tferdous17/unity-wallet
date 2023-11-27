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
    public List<CashCard> getCashCards() {
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

    public List<CashCard> getAllCards() {
        return repository.findAll();
    }

    public ResponseEntity<Void> updateCard(Long id, CashCard card, Principal principal) {
        CashCard cardToUpdate = repository.findByIdAndOwner(id, principal.getName());
        if (cardToUpdate != null) {
            cardToUpdate.setAmount(card.getAmount());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Void> deleteCard(Long id, Principal principal) {
        CashCard cardToDelete = repository.findByIdAndOwner(id, principal.getName());
        if (cardToDelete != null) {
            repository.delete(cardToDelete);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    public Page<CashCard> getAllCashCardsByOwner(String owner, Pageable pageable) {
        return repository.findByOwner(owner, PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                pageable.getSortOr(Sort.by(Sort.Direction.DESC, "amount")))
        );
    }

    public Optional<CashCard> findByIdAndOwner(Long requestedId, String name) {
        return Optional.ofNullable(repository.findByIdAndOwner(requestedId, name));
    }
}
