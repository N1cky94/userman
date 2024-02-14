package be.archilios.usermanagement.core.users;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public
class UserDatabaseService implements UserUseCases {
    private final UserRepository userRepository;
    
    @Override
    public List<UserInfo> fetchAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(UserInfo::from)
                .toList();
    }
    
    @Override
    public UserInfo createNewUser(CreateNewUserCommand newUser) {
        User user = new User(
                null,
                newUser.email(),
                newUser.firstName(),
                newUser.lastName(),
                newUser.activate()
        );
        
        user = userRepository.save(user);
        return UserInfo.from(user);
    }
}
