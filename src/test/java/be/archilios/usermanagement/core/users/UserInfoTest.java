package be.archilios.usermanagement.core.users;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserInfoTest {
    private final User EXPECTED_USER = UserMother.get().build();
    private final UserInfo EXPECTED_USER_INFO = UserInfo.from(EXPECTED_USER);
    
    @Test
    @DisplayName("Given a User, when the from method is called, then a UserInfo is correctly created")
    void testTheFromMethod() {
        // Assert
        assertEquals(EXPECTED_USER.getId(), EXPECTED_USER_INFO.id(), "The id should be correctly mapped");
        assertEquals(EXPECTED_USER.getEmail(), EXPECTED_USER_INFO.email(), "The email should be correctly mapped");
        assertEquals(EXPECTED_USER.getFirstName(), EXPECTED_USER_INFO.firstName(), "The first name should be correctly mapped");
        assertEquals(EXPECTED_USER.getLastName(), EXPECTED_USER_INFO.lastName(), "The last name should be correctly mapped");
        assertEquals(EXPECTED_USER.isActive(), EXPECTED_USER_INFO.active(), "The active status should be correctly mapped");
    }
}
