package dev.tferdous.familycashcardapp.service;

import dev.tferdous.familycashcardapp.exceptions.CashCardNotFoundException;
import dev.tferdous.familycashcardapp.entity.CashCard;
import dev.tferdous.familycashcardapp.repository.CashCardRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CashCardService {
    private final CashCardRepository repository;

    @Autowired
    public CashCardService(CashCardRepository repository) {
        this.repository = repository;
    }

    public List<CashCard> getCashCards() {
        return repository.findAll();
    }

    public CashCard createCashCard(CashCard card) {
        return repository.save(card);
    }

    public Optional<CashCard> findById(Long requestedId) {
        return repository.findById(requestedId);
    }

    public List<CashCard> getAllCards() {
        return repository.findAll();
    }

    @Transactional
    public void updateCard(Long id, CashCard card) {
        repository.findById(id).map(cardToUpdate -> {
            cardToUpdate.setAmount(card.getAmount());
            cardToUpdate.setOwner(card.getOwner());
            return cardToUpdate;
        }).orElseThrow(() -> new CashCardNotFoundException(id));
    }
}
