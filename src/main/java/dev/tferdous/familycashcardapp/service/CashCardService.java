package dev.tferdous.familycashcardapp.service;

import dev.tferdous.familycashcardapp.entity.CashCard;
import dev.tferdous.familycashcardapp.repository.CashCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void createCashCard(CashCard card) {
        repository.save(card);
    }
}
