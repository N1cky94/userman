package be.archilios.usermanagement.views.admin.users;

import jakarta.annotation.security.RolesAllowed;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserManagementViewTest {
    
    @Test
    @DisplayName("Ensure UserManagementView is annotated with @RolesAllowed having 'ROLE_ADMIN' as its value")
    void navigateAsAdmin_SuccessfulNavigation() {
        Class<UserManagementView> clazz = UserManagementView.class;
        RolesAllowed roles = clazz.getAnnotation(RolesAllowed.class);
        
        assertNotNull(roles, "The UserManagementView should be annotated with @RolesAllowed");
        assertArrayEquals(new String[]{"ROLE_ADMIN"}, roles.value(), "The UserManagementView should only be accessible to users with the 'admin' role");
    }
    
    
}