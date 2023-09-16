package dev.tferdous.familycashcardapp.controller;

import dev.tferdous.familycashcardapp.service.CashCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CashCardController {
    private CashCardService cashCardService;

    @Autowired
    public CashCardController(CashCardService cashCardService) {
        this.cashCardService = cashCardService;
    }
}
