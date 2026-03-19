package whnazv.biblioteca.application.port.out;

import whnazv.biblioteca.domain.model.User;

import java.util.List;
import java.util.Optional;

/**
 * Puerto de salida para la persistencia de usuarios.
 * Define únicamente operaciones CRUD puras, sin lógica de negocio.
 * Preparado para permitir múltiples implementaciones (JPA, Mongo, API externa).
 */
public interface UserRepositoryPort {

    /**
     * Guarda o actualiza un usuario.
     *
     * @param user entidad de dominio User
     * @return usuario persistido
     */
    User save(User user);

    /**
     * Busca un usuario por su ID.
     *
     * @param id identificador único
     * @return Optional con el usuario si existe
     */
    Optional<User> findById(Long id);

    /**
     * Busca un usuario por email.
     *
     * @param email email único del usuario
     * @return Optional con el usuario si existe
     */
    Optional<User> findByEmail(String email);

    /**
     * Busca un usuario por nombre de usuario.
     *
     * @param username nombre de usuario
     * @return Optional con el usuario si existe
     */
    Optional<User> findByUsername(String username);

    /**
     * Obtiene todos los usuarios.
     *
     * @return lista de usuarios
     */
    List<User> findAll();

    /**
     * Elimina un usuario por ID.
     *
     * @param id identificador único
     */
    void deleteById(Long id);
}
