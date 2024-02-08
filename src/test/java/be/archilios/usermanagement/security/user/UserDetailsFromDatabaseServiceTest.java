package be.archilios.usermanagement.security.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserDetailsFromDatabaseServiceTest {
    
    @Mock
    private SecurityUserRepository securityUserRepository;
    private UserDetailsFromDatabaseService userDetailsFromDatabaseService;
    
    @Test
    @DisplayName("Given a valid username, when loadUserByUsername is called, then return UserDetails with correct username")
    void loadUserByUsername_ValidUsername_ReturnsUserDetails() {
        // Arrange
        SecurityUser securityUser = UserMother.get().build();
        String username = securityUser.getEmail();
        when(securityUserRepository.findByEmail(username)).thenReturn(Optional.of(securityUser));
        userDetailsFromDatabaseService = new UserDetailsFromDatabaseService(securityUserRepository);
        
        // Act
        UserDetails userDetails = userDetailsFromDatabaseService.loadUserByUsername(username);
        
        // Assert
        assertEquals(username, userDetails.getUsername(), "The username in the returned UserDetails should match the input username");
    }
    
    @Test
    @DisplayName("Given an invalid username, when loadUserByUsername is called, then throw UsernameNotFoundException")
    void loadUserByUsername_InvalidUsername_ThrowsUsernameNotFoundException() {
        // Arrange
        String username = "invalid@example.com";
        when(securityUserRepository.findByEmail(username)).thenReturn(Optional.empty());
        userDetailsFromDatabaseService = new UserDetailsFromDatabaseService(securityUserRepository);
        
        // Act & Assert
        UsernameNotFoundException exception = assertThrows(UsernameNotFoundException.class, () -> userDetailsFromDatabaseService.loadUserByUsername(username), "A UsernameNotFoundException should be thrown if the username is not found");
        assertEquals("User not found", exception.getMessage(), "The exception message should be 'User not found'");
    }
    
}