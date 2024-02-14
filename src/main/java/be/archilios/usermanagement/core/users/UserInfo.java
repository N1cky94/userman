package be.archilios.usermanagement.core.users;

public record UserInfo(
        Long id,
        String email,
        String firstName,
        String lastName,
        boolean active
) {
    public static UserInfo from(User user) {
        return new UserInfo(
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.isActive()
        );
    }
}
