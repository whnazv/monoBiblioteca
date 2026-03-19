package whnazv.biblioteca.domain.model;

import lombok.Getter;
import lombok.Setter;

import lombok.NoArgsConstructor;

/**
 * Representa un usuario dentro del dominio.
 * Es una entidad con identidad propia y reglas de negocio internas.
 *
 * Invariantes:
 * - El username no puede ser nulo ni vacío.
 * - El email debe tener un formato válido.
 * - La contraseña no puede ser nula ni vacía.
 * - El rol debe ser ADMIN o CLIENTE.
 *
 * Esta clase implementa un dominio rico: la lógica de negocio
 * relacionada con el usuario vive dentro de la entidad.
 */
@Setter
@Getter
@NoArgsConstructor
public class User {

    private Long id;
    private String username;
    private String password; 
    private String firstName;
    private String lastName;
    private String email;
    private String role;

    /**
     * Crea un usuario garantizando que cumple las reglas del dominio.
     */
    public User(Long id,
                String username,
                String password,
                String firstName,
                String lastName,
                String email,
                String role) {

        validateUsername(username);
        validatePassword(password);
        validateEmail(email);
        validateRole(role);

        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
    }



    /**
     * Cambia la contraseña del usuario.
     * La encriptación se realiza fuera del dominio.
     */
    public void changePassword(String newPassword) {
        validatePassword(newPassword);
        this.password = newPassword;
    }

    /**
     * Actualiza los datos personales del usuario.
     */
    public void updateProfile(String firstName, String lastName, String email) {
        validateEmail(email);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

  

    private void validateUsername(String username) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("El nombre de usuario no puede estar vacío.");
        }
    }

    private void validatePassword(String password) {
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("La contraseña no puede estar vacía.");
        }
    }

    private void validateEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("El email no es válido.");
        }
    }

    private void validateRole(String role) {
        if (!"ADMIN".equals(role) && !"CLIENTE".equals(role)) {
            throw new IllegalArgumentException("Rol inválido.");
        }
    }
}
