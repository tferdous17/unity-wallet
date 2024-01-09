package dev.tferdous.familycashcardapp.mapper;

import dev.tferdous.familycashcardapp.dto.UserDTO;
import dev.tferdous.familycashcardapp.model.entity.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    public UserDTO toDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }
}
