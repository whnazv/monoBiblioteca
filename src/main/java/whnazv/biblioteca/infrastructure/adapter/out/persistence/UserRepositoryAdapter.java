package whnazv.biblioteca.infrastructure.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import whnazv.biblioteca.application.port.out.UserRepositoryPort;
import whnazv.biblioteca.domain.model.User;
import whnazv.biblioteca.infrastructure.adapter.out.persistence.entity.UserEntity;
import whnazv.biblioteca.infrastructure.adapter.out.persistence.repository.UserRepository;
import whnazv.biblioteca.infrastructure.mapper.entity.UserMapperEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Adaptador de persistencia para usuarios.
 * Implementa el puerto UserRepositoryPort utilizando JPA.
 * No contiene lógica de negocio ni validaciones.
 */
@Component
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final UserRepository repository;
    private final UserMapperEntity mapper;

    /**
     * Guarda o actualiza un usuario.
     */
    @Override
    public User save(User user) {
        UserEntity entity = mapper.toEntity(user);
        return mapper.toModel(repository.save(entity));
    }

    /**
     * Busca un usuario por ID.
     */
    @Override
    public Optional<User> findById(Long id) {
        return repository.findById(id).map(mapper::toModel);
    }

    /**
     * Busca un usuario por email.
     */
    @Override
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email).map(mapper::toModel);
    }

    /**
     * Busca un usuario por nombre de usuario.
     */
    @Override
    public Optional<User> findByUsername(String username) {
        return repository.findByUsername(username).map(mapper::toModel);
    }

    /**
     * Obtiene todos los usuarios.
     */
    @Override
    public List<User> findAll() {
        return repository.findAll().stream().map(mapper::toModel).toList();
    }

    /**
     * Elimina un usuario por ID.
     */
    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
