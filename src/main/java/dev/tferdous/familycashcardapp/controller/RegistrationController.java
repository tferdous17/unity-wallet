package dev.tferdous.familycashcardapp.controller;

import dev.tferdous.familycashcardapp.model.entity.User;
import dev.tferdous.familycashcardapp.payload.request.UserRegistrationRequest;
import dev.tferdous.familycashcardapp.service.RegisterService;
import dev.tferdous.familycashcardapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/cashcards/v1/register")
public class RegistrationController {

    private final RegisterService registerService;
    private final UserService userService;

    @Autowired
    public RegistrationController(RegisterService registerService, UserService userService) {
        this.registerService = registerService;
        this.userService = userService;
    }

//    @RequestMapping("api/cashcards/v1/register")
//    public String signupTest() {
//        return "registration"; // return registration.html
//    }

    @PostMapping
    public ResponseEntity<Void> registerUser(@RequestBody UserRegistrationRequest request) {
        registerService.registerUser(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.findUserByUsername(username));
    }

}
