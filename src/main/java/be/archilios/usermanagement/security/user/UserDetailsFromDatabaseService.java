package be.archilios.usermanagement.security.user;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Primary
@RequiredArgsConstructor
public class UserDetailsFromDatabaseService implements UserDetailsService {
    private final SecurityUserRepository securityUserRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SecurityUser user = securityUserRepository.findByEmail(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User with email " + username + " not found")
                );
        
        return new UserDetailsFromSecurityUser(user);
    }
}
