package be.archilios.usermanagement.core.users;

import be.archilios.usermanagement.security.user.ActivateSecurityUserCommand;
import be.archilios.usermanagement.security.user.SecurityUserUseCases;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDatabaseService implements UserUseCases {
    private final UserRepository userRepository;
    private final SecurityUserUseCases securityUserService;
    
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
    
    @Override
    public void activateUserAccount(ActivateUserCommand activatedUser) {
        String password = generatePassword();
        
        ActivateSecurityUserCommand securityUserCommand = new ActivateSecurityUserCommand(
                activatedUser.email(),
                password,
                activatedUser.role()
        );
        
        securityUserService.activateUser(securityUserCommand);
        
        
    }
    
    private String generatePassword() {
        return "psw";
    }
}
