package be.archilios.usermanagement.core.users;

import java.util.List;

public interface UserUseCases {
    List<UserInfo> fetchAllUsers();
}
