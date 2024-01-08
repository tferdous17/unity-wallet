package dev.tferdous.familycashcardapp.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class UserDTO {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
}
