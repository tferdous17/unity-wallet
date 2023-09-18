package dev.tferdous.familycashcardapp;

import dev.tferdous.familycashcardapp.entity.CashCard;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CashCardControllerTest {
    @Autowired
    TestRestTemplate restTemplate;
    CashCard card = new CashCard(104.51, "John Doe");

    @Test
    void shouldCreateANewCashCard() {
        ResponseEntity<Void> createResponse = restTemplate.postForEntity("/cashcards/v1", card, Void.class);
        assertSame(createResponse.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    void shouldReturnNotFoundForUnknownId() {
        ResponseEntity<String> getResponse = restTemplate.getForEntity("/cashcards/v1/100", String.class);
        assertSame(getResponse.getStatusCode(), HttpStatus.NOT_FOUND);
    }

}