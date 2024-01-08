package dev.tferdous.familycashcardapp.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
}
