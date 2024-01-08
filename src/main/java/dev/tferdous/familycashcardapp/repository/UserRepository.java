package dev.tferdous.familycashcardapp.repository;

import dev.tferdous.familycashcardapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByFullName(String firstName, String lastName);
    User findByUsername(String username);
}
