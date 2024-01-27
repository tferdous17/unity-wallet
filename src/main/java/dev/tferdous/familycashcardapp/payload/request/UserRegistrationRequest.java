package dev.tferdous.familycashcardapp.payload.request;

import lombok.*;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationRequest {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
}
