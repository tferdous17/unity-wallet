package dev.tferdous.familycashcardapp.service;

import dev.tferdous.familycashcardapp.exceptions.EmailAlreadyExistsException;
import dev.tferdous.familycashcardapp.model.entity.User;
import dev.tferdous.familycashcardapp.model.enums.Role;
import dev.tferdous.familycashcardapp.payload.request.UserRegistrationRequest;
import dev.tferdous.familycashcardapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public RegisterService(UserRepository userRepository, @Lazy BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public void registerUser(UserRegistrationRequest request) {
        if (userRepository.findByEmail(request.email()).isPresent()) {
            throw new EmailAlreadyExistsException(request.email());
        }

        User user = new User(
                request.firstName(),
                request.lastName(),
                request.username(),
                request.email(),
                passwordEncoder.encode(request.password()),
                Role.USER
        );

        userRepository.save(user);
    }

}
