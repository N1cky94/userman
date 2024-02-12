package be.archilios.usermanagement.security.user;

public record ActivateSecurityUserCommand(
        String email,
        String password,
        ApplicationRole role
) {
}
