package be.archilios.usermanagement.core.users;

import be.archilios.usermanagement.security.user.ApplicationRole;

public record ActivateUserCommand(
        String email,
        ApplicationRole role
) {
}
