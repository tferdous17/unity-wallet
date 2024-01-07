package dev.tferdous.familycashcardapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class SignUpController {
    @RequestMapping("api/cashcards/v1/signup")
    public String signupTest() {
        return "signup";
    }

}
