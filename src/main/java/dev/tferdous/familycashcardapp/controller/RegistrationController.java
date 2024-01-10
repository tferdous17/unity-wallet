package dev.tferdous.familycashcardapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegistrationController {
    @RequestMapping("api/cashcards/v1/register")
    public String signupTest() {
        return "registration";
    }

}
