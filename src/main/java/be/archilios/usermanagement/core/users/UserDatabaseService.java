package be.archilios.usermanagement.core.users;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDatabaseService implements UserUseCases {
    private final UserRepository userRepository;
    
    public List<UserInfo> fetchAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(UserInfo::from)
                .toList();
    }
    
    public UserInfo createNewUser(CreateNewUserCommand newUser) {
        User user = new User(
                null,
                newUser.email(),
                newUser.firstname(),
                newUser.lastname(),
                newUser.activate()
        );
        
        user = userRepository.save(user);
        return UserInfo.from(user);
    }
}
