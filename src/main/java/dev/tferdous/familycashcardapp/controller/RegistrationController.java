package dev.tferdous.familycashcardapp.controller;

import dev.tferdous.familycashcardapp.payload.request.UserRegistrationRequest;
import dev.tferdous.familycashcardapp.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegistrationController {

    private final RegisterService registerService;

    @Autowired
    public RegistrationController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @RequestMapping("api/cashcards/v1/register")
    public String signupTest() {
        return "registration"; // return registration.html
    }

    @PostMapping
    public ResponseEntity<Void> registerUser(@RequestBody UserRegistrationRequest request) {
        registerService.registerUser(request);
        return ResponseEntity.ok().build();
    }

}
