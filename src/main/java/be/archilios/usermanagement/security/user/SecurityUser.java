package be.archilios.usermanagement.security.user;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter(value = lombok.AccessLevel.PACKAGE)
@ToString
public class SecurityUser {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Setter(value = AccessLevel.PRIVATE)
    private Long id;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private ApplicationRole role;
    @Column(name = "account_activated")
    private boolean activeAccount;
}
