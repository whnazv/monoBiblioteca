package whnazv.biblioteca.infrastructure.adapter.in.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;

import whnazv.biblioteca.application.port.in.UserUseCase;
import whnazv.biblioteca.infrastructure.adapter.in.dto.UserResponse;
import whnazv.biblioteca.infrastructure.mapper.dto.UserMapperDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para operaciones de consulta de usuarios.
 * Expone endpoints para obtener usuarios por distintos criterios.
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserUseCase userUseCase;
    private final UserMapperDto userMapperDto;

    /**
     * Obtiene todos los usuarios registrados.
     *
     * @return lista de usuarios en formato DTO de salida (UserResponse)
     */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userUseCase.findAllUsers()
                .stream()
                .map(userMapperDto::toResponse)
                .toList();
    }

    /**
     * Obtiene un usuario por su identificador único.
     *
     * @param id identificador del usuario
     * @return usuario encontrado en formato DTO de salida
     */
    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        return userMapperDto.toResponse(userUseCase.findById(id));
    }

    /**
     * Obtiene un usuario por su nombre de usuario.
     *
     * @param username nombre de usuario
     * @return usuario encontrado en formato DTO de salida
     */
    @GetMapping("/username/{username}")
    public UserResponse getByUsername(@PathVariable String username) {
        return userMapperDto.toResponse(userUseCase.findByUsername(username));
    }

    /**
     * Obtiene un usuario por su correo electrónico.
     *
     * @param email correo electrónico del usuario
     * @return usuario encontrado en formato DTO de salida
     */
    @GetMapping("/email/{email}")
    public UserResponse getByEmail(@PathVariable String email) {
        return userMapperDto.toResponse(userUseCase.findByEmail(email));
    }
}
