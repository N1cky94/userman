package be.archilios.usermanagement.core.users;

public record CreateNewUserCommand(
        String email,
        String firstname,
        String lastname,
        
        boolean activate
) {
}
