package dev.tferdous.familycashcardapp.controller;

import dev.tferdous.familycashcardapp.entity.CashCard;
import dev.tferdous.familycashcardapp.service.CashCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
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
    public ResponseEntity<CashCard> getCashCardById(@PathVariable Long requestedId, Principal principal) {
        Optional<CashCard> cashCardOptional = cashCardService.findByIdAndOwner(requestedId, principal.getName());
        return cashCardOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<CashCard>> getAllCashCards(Principal principal, Pageable pageable) {
        Page<CashCard> page = cashCardService.getAllCashCardsByOwner(principal.getName(), pageable);
        return ResponseEntity.ok(page.getContent());
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
    private ResponseEntity<Void> updateCard(@PathVariable Long id, @RequestBody CashCard card, Principal principal) {
        return cashCardService.updateCard(id, card, principal);
    }

    @DeleteMapping(path = "{id}")
    private ResponseEntity<Void> deleteCard(@PathVariable Long id, Principal principal) {
        return cashCardService.deleteCard(id, principal);
    }
}
