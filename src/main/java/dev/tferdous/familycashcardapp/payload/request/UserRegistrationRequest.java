package dev.tferdous.familycashcardapp.payload.request;

public record UserRegistrationRequest(
        String firstName,
        String lastName,
        String username,
        String email,
        String password
) {
}
