package dev.tferdous.familycashcardapp.repository;

import dev.tferdous.familycashcardapp.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByFullName(String firstName, String lastName);
    Optional<User> findByUsername(String username);
}
