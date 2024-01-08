package dev.tferdous.familycashcardapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Customer {
    @Id
    @Column(nullable = false, unique = true, updatable = false)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String firstName;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String lastName;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String username;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String email;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String password;

    public Customer() { }

    public Customer(String firstName, String lastName, String username, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
