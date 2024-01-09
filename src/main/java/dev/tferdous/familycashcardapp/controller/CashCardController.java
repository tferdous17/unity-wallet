package dev.tferdous.familycashcardapp.controller;

import dev.tferdous.familycashcardapp.model.entity.CashCard;
import dev.tferdous.familycashcardapp.service.CashCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

/**
 * RESTful web service controller that returns JSON data
 * Handles HTTP requests for cash card creation, deletion, retrieval, etc
 * @author Tasnim Ferdous
 */
@RestController
@RequestMapping("api/cashcards/v1")
public class CashCardController {
    private final CashCardService cashCardService;

    /**
     * Automatically injects cash card service object
     * @param cashCardService CashCardService object
     */
    @Autowired
    public CashCardController(CashCardService cashCardService) {
        this.cashCardService = cashCardService;
    }

    /**
     * Retrieves a specific cash card by its ID if being accessed by the actual card owner
     * @param requestedId Query param for ID
     * @param principal Entity for cash card owner
     * @return status code 200 if found + card info, else 404
     */
    @GetMapping("/{requestedId}")
    public ResponseEntity<CashCard> getCashCardById(@PathVariable Long requestedId, Principal principal) {
        Optional<CashCard> cashCardOptional = cashCardService.findByIdAndOwner(requestedId, principal.getName());
        return cashCardOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Returns a list of all existing cash cards for any particular user
     * Card list is automatically sorted by balance in descending order
     * @param principal Entity for cash card owner
     * @param pageable Pageable object
     * @return status code 200 + list of cards
     */
    @GetMapping
    public ResponseEntity<List<CashCard>> getAllCashCards(Principal principal, Pageable pageable) {
        Page<CashCard> page = cashCardService.getAllCashCardsByOwner(principal.getName(), pageable);
        return ResponseEntity.ok(page.getContent());
    }

    /**
     * POST method to create new cash cards utilizing a request body containing a CashCard object
     * Builds a unique URI for each new card
     * @param card CashCard object to be passed in through request body
     * @param ucb Components builder to build a URI for each new card
     * @return status code 201
     */
    @PostMapping
    private ResponseEntity<Void> createCashCard(@RequestBody CashCard card, UriComponentsBuilder ucb) {
        CashCard newCard = cashCardService.createCashCard(card);
        URI locationOfNewCard = ucb
                .path("cashcards/v1/{id}")
                .buildAndExpand(newCard.getId())
                .toUri();
        return ResponseEntity.created(locationOfNewCard).build();
    }

    /**
     * Updates an existing cash card for card owners
     * @param id Query param for id of card to update
     * @param card CashCard object with the updated info
     * @param principal Entity for card owner
     * @return status code 204 if success, else 404
     */
    @PutMapping(path = "{id}")
    private ResponseEntity<Void> updateCard(@PathVariable Long id, @RequestBody CashCard card, Principal principal) {
        return cashCardService.updateCard(id, card, principal);
    }

    /**
     * Allows owners to delete cash cards they own
     * @param id Query param for id of card to delete
     * @param principal Entity for card owner
     * @return status code 204 if success, else 404
     */
    @DeleteMapping(path = "{id}")
    private ResponseEntity<Void> deleteCard(@PathVariable Long id, Principal principal) {
        return cashCardService.deleteCard(id, principal);
    }
}
