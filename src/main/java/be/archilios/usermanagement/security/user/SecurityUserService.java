package be.archilios.usermanagement.security.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityUserService implements SecurityUserUseCases {
    private final SecurityUserRepository securityUserRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public void activateUser(ActivateSecurityUserCommand command) {
        SecurityUser user = securityUserRepository.findByEmail(command.email()).orElseThrow();
        
        if (user.isActiveAccount()) {
            throw new IllegalStateException("User is already activated");
        } else {
            user.setActiveAccount(true);
            user.setRole(command.role());
            user.setPassword(encryptPassword(command.password()));
            
            securityUserRepository.save(user);
        }
    }
    
    private String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
