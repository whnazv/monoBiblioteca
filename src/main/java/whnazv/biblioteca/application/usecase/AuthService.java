package whnazv.biblioteca.application.usecase;

import lombok.RequiredArgsConstructor;
import whnazv.biblioteca.application.port.in.AuthUseCase;
import whnazv.biblioteca.application.port.out.UserRepositoryPort;
import whnazv.biblioteca.domain.model.User;
import whnazv.biblioteca.infrastructure.security.JwtUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService implements AuthUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final JwtUtil jwtUtil;

    @Override
    public String login(String email, String password) {

        User user = userRepositoryPort.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Credenciales inválidas"));

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (!encoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        null,
                        List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole()))
                );

        return jwtUtil.generateToken(auth);
    }
}
