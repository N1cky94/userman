package be.archilios.usermanagement.core.users;

import java.util.List;

public interface UserUseCases {
    List<UserInfo> fetchAllUsers();
    UserInfo createNewUser(CreateNewUserCommand newUser);
    void activateUserAccount(ActivateUserCommand activatedUser);
}
