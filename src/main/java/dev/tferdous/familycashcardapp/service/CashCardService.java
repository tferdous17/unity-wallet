package dev.tferdous.familycashcardapp.service;

import dev.tferdous.familycashcardapp.entity.CashCard;
import dev.tferdous.familycashcardapp.repository.CashCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}
