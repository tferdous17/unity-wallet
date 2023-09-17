package dev.tferdous.familycashcardapp.controller;

import dev.tferdous.familycashcardapp.entity.CashCard;
import dev.tferdous.familycashcardapp.service.CashCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("fcc/v1")
public class CashCardController {
    private final CashCardService cashCardService;

    @Autowired
    public CashCardController(CashCardService cashCardService) {
        this.cashCardService = cashCardService;
    }

    @GetMapping
    public List<CashCard> getCashCards() {
        return cashCardService.getCashCards();
    }

    @PostMapping
    public void createCashCard(@RequestBody CashCard card) {
        cashCardService.createCashCard(card);
    }
}
