package dev.tferdous.familycashcardapp.controller;

import dev.tferdous.familycashcardapp.entity.CashCard;
import dev.tferdous.familycashcardapp.service.CashCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("cashcards/v1")
public class CashCardController {
    private final CashCardService cashCardService;

    @Autowired
    public CashCardController(CashCardService cashCardService) {
        this.cashCardService = cashCardService;
    }

    @GetMapping("/{requestedId}")
    public ResponseEntity<CashCard> getCashCards(@PathVariable Long requestedId) {
        Optional<CashCard> cashCardOptional = cashCardService.findById(requestedId);
        return cashCardOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public void createCashCard(@RequestBody CashCard card) {
        cashCardService.createCashCard(card);
    }

}
