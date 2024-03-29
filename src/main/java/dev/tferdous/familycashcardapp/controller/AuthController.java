package dev.tferdous.familycashcardapp.controller;

import dev.tferdous.familycashcardapp.dto.UserDTO;
import dev.tferdous.familycashcardapp.mapper.UserMapper;
import dev.tferdous.familycashcardapp.model.entity.User;
import dev.tferdous.familycashcardapp.payload.request.UserRegistrationRequest;
import dev.tferdous.familycashcardapp.service.AuthService;
import dev.tferdous.familycashcardapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/cashcards/v1/auth")
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @Autowired
    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

//    @RequestMapping("api/cashcards/v1/register")
//    public String signupTest() {
//        return "registration"; // return registration.html
//    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegistrationRequest request) {
        return ResponseEntity.ok(authService.registerUser(request));
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(UserMapper.toDTO(userService.findUserByUsername(username)));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllRegisteredUsers() {
        return ResponseEntity.ok(userService.retrieveAllUsers().stream().map(UserMapper::toDTO).toList());
    }

}