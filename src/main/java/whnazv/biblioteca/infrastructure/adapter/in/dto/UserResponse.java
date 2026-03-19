package whnazv.biblioteca.infrastructure.adapter.in.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO de salida para exponer información pública del usuario.
 * No contiene contraseña.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
}
