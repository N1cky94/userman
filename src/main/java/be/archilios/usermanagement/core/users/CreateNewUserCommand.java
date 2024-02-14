package be.archilios.usermanagement.core.users;

public record CreateNewUserCommand(
        String email,
        String firstName,
        String lastName,
        boolean activate
) {
}
