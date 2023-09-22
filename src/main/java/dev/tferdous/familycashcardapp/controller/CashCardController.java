package dev.tferdous.familycashcardapp.controller;

import dev.tferdous.familycashcardapp.entity.CashCard;
import dev.tferdous.familycashcardapp.service.CashCardService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
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

    @GetMapping
    public ResponseEntity<List<CashCard>> getAllCashCards(Pageable pageable) {
        return ResponseEntity.ok(cashCardService.getAllCashCards(pageable));
    }

    @PostMapping
    private ResponseEntity<Void> createCashCard(@RequestBody CashCard card, UriComponentsBuilder ucb) {
        CashCard newCard = cashCardService.createCashCard(card);
        URI locationOfNewCard = ucb
                .path("cashcards/v1/{id}")
                .buildAndExpand(newCard.getId())
                .toUri();
        return ResponseEntity.created(locationOfNewCard).build();
    }

    @PutMapping(path = "{id}")
    private void updateCard(@PathVariable Long id, @RequestBody CashCard card) {
        cashCardService.updateCard(id, card);
    }

    @DeleteMapping(path = "{id}")
    private void deleteCard(@PathVariable Long id) {
        cashCardService.deleteCard(id);
    }
}
