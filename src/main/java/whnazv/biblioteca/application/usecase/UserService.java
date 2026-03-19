package whnazv.biblioteca.application.usecase;

import lombok.RequiredArgsConstructor;
import whnazv.biblioteca.application.port.in.UserUseCase;
import whnazv.biblioteca.application.port.out.UserRepositoryPort;
import whnazv.biblioteca.domain.exception.NotFoundException;
import whnazv.biblioteca.domain.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserUseCase {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public User registerUser(User user) {
        return userRepositoryPort.save(user);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepositoryPort.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepositoryPort.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
    }

    @Override
    public User findByUsername(String username) {
        return userRepositoryPort.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
    }

    @Override
    public User findByEmail(String email) {
        return userRepositoryPort.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
    }
}
