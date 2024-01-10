package dev.tferdous.familycashcardapp.service;

import dev.tferdous.familycashcardapp.exceptions.EmailAlreadyExistsException;
import dev.tferdous.familycashcardapp.model.entity.User;
import dev.tferdous.familycashcardapp.payload.request.UserRegistrationRequest;
import dev.tferdous.familycashcardapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    private final UserRepository userRepository;

    @Autowired
    public RegisterService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(UserRegistrationRequest request) {
        if (userRepository.findByEmail(request.email()).isPresent()) {
            throw new EmailAlreadyExistsException(request.email());
        }

        User user = new User(request.firstName(), request.lastName(), request.username(), request.email(), request.password());
        userRepository.save(user);
    }

}
