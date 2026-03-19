package whnazv.biblioteca.infrastructure.mapper.dto;

import whnazv.biblioteca.domain.model.User;
import whnazv.biblioteca.infrastructure.adapter.in.dto.UserRegisterDto;
import org.springframework.stereotype.Component;

@Component
public class UserRegisterMapper {

    public User toDomain(UserRegisterDto dto) {
    return new User(
            null,
            dto.username(),
            dto.password(),
            dto.firstName(),
            dto.lastName(),
            dto.email(),
            "CLIENTE"
    );
}
}
