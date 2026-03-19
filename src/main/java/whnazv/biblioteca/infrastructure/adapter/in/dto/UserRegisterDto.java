package whnazv.biblioteca.infrastructure.adapter.in.dto;

public record UserRegisterDto(
        String username,
        String firstName,
        String lastName,
        String email,
        String password
) {}