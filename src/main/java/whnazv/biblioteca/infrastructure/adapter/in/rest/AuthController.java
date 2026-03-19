package whnazv.biblioteca.infrastructure.adapter.in.rest;

import lombok.RequiredArgsConstructor;
import whnazv.biblioteca.application.port.in.AuthUseCase;
import whnazv.biblioteca.application.port.in.UserUseCase;
import whnazv.biblioteca.domain.model.User;
import whnazv.biblioteca.infrastructure.adapter.in.dto.UserLoginDto;
import whnazv.biblioteca.infrastructure.adapter.in.dto.UserRegisterDto;
import whnazv.biblioteca.infrastructure.adapter.in.dto.UserResponse;
import whnazv.biblioteca.infrastructure.mapper.dto.UserMapperDto;
import whnazv.biblioteca.infrastructure.mapper.dto.UserRegisterMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST encargado de gestionar las operaciones de autenticación.
 * 
 * Expone los endpoints para:
 * - Registrar nuevos usuarios.
 * - Iniciar sesión y obtener un token o resultado del login.
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserUseCase userUseCase;
    private final AuthUseCase authUseCase;
    private final UserRegisterMapper registerMapper;
    private final UserMapperDto userMapperDto;

    /**
     * Registra un nuevo usuario en el sistema.
     *
     * @param dto Datos del usuario a registrar.
     * @return El usuario registrado en formato DTO.
     */
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRegisterDto dto) {
        User user = registerMapper.toDomain(dto);
        User saved = userUseCase.registerUser(user);
        return ResponseEntity.ok(userMapperDto.toResponse(saved));
    }

    /**
     * Inicia sesión con las credenciales proporcionadas.
     *
     * @param dto Credenciales del usuario (email y contraseña).
     * @return Token o resultado del proceso de autenticación.
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginDto dto) {
        String token = authUseCase.login(dto.getEmail(), dto.getPassword());
        return ResponseEntity.ok(token);
    }
}
