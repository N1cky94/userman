package be.archilios.usermanagement.security.user;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter(value = lombok.AccessLevel.PRIVATE)
@ToString
public class SecurityUser {
    private Long id;
    private String email;
    private String password;
    private ApplicationRole role;
    private boolean activeAccount;
}
