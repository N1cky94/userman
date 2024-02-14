package be.archilios.usermanagement.security.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserDetailsFromSecurityUserTest {
    @Test
    @DisplayName("UserDetails maps correctly from a SecurityUser")
    void testUserDetailsFromSecurityUser() {
        SecurityUser user = SecurityUserMother.get().build();
        
        UserDetailsFromSecurityUser userDetails = new UserDetailsFromSecurityUser(user);
        
        assertEquals(user.getEmail(), userDetails.getUsername(), "The username should be the email of the user");
        assertEquals(user.getPassword(), userDetails.getPassword(), "The password should be the password of the user");
        assertEquals(user.isActiveAccount(), userDetails.isEnabled(), "The user should be enabled if the account is active");
        assertTrue(userDetails.isAccountNonExpired(), "The account should never expire");
        assertTrue(userDetails.isAccountNonLocked(), "The account should never be locked");
        assertTrue(userDetails.isCredentialsNonExpired(), "The credentials should never expire");
        assertEquals("ROLE_USER", userDetails.getAuthorities().iterator().next().getAuthority(), "The role should be the role of the user");
        
    }
}
