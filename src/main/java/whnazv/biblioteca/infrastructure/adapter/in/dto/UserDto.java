package whnazv.biblioteca.infrastructure.adapter.in.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String role;

    public UserDto(Long id, String username, String password, String firstName,
                   String lastName, String email, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
    }
}
