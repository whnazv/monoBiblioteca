package whnazv.biblioteca.application.port.in;

import whnazv.biblioteca.domain.model.User;

import java.util.List;

public interface UserUseCase {

    User registerUser(User user);

    List<User> findAllUsers();

    User findById(Long id);

    User findByUsername(String username);

    User findByEmail(String email);
}
