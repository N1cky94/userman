package be.archilios.usermanagement.security;

import be.archilios.usermanagement.security.user.SecurityUserMother;
import be.archilios.usermanagement.security.user.UserDetailsFromSecurityUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SecurityServiceTest {
    
    private UserDetailsFromSecurityUser expectedUser = new UserDetailsFromSecurityUser(SecurityUserMother.get().build());
    private UserDetailsFromSecurityUser expectedAdmin = new UserDetailsFromSecurityUser(SecurityUserMother.get().asAdmin().build());
    @Mock
    private Authentication auth;
    @Mock
    private SecurityContext secCtx;
    @InjectMocks
    private SecurityService securityService;
    
    @Test
    void getAuthenticatedUser() {
        // Arrange
        when(auth.getPrincipal()).thenReturn(expectedUser);
        when(secCtx.getAuthentication()).thenReturn(auth);
        when(secCtx.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(secCtx);
        
        UserDetails result = securityService.getAuthenticatedUser();
        
        assertEquals(expectedUser, result);
    }
    
    @Test
    void throwExceptionWhenNoUserIsAuthenticated() {
        // Arrange
        when(secCtx.getAuthentication()).thenReturn(null);
        SecurityContextHolder.setContext(secCtx);
        
        // Act & Assert
        assertThrows(NullPointerException.class, () -> securityService.getAuthenticatedUser());
    }
    
    @Test
    void isAuthenticatedUserAdmin() {
        // Arrange
        when(auth.getPrincipal()).thenReturn(expectedAdmin);
        when(secCtx.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(secCtx);
        
        boolean result = securityService.isAuthenticatedUserAdmin();
        
        assertTrue(result);
    }
    
    @Test
    void isAuthenticatedUserNotAdmin() {
        // Arrange
        when(auth.getPrincipal()).thenReturn(expectedUser);
        when(secCtx.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(secCtx);
        
        boolean result = securityService.isAuthenticatedUserAdmin();
        
        assertFalse(result);
    }
    
}