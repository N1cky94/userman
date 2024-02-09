package be.archilios.usermanagement.core.users;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserDatabaseServiceTest {
    @Mock
    private UserRepository userRepository;
    
    private UserDatabaseService userDatabaseService;
    
    @BeforeEach
    void setUp() {
        userDatabaseService = new UserDatabaseService(userRepository);
    }
    
    @Test
    @DisplayName("Given a list of Users from the repository, when fetchAllUsers is called, then a list of UserInfo is correctly received")
    void fetchAllUsers() {
        // Arrange
        List<User> users = UserMother.get().buildList();
        when(userRepository.findAll()).thenReturn(users);
        
        // Act
        List<UserInfo> expectedUserInfo = userDatabaseService.fetchAllUsers();
        
        // Assert
        assertNotNull(expectedUserInfo, "The list of UserInfo should not be null");
        assertEquals(users.size(), expectedUserInfo.size(), "The list of UserInfo should have the same size as the list of Users");
        assertEquals(UserInfo.from(UserMother.get().build()), expectedUserInfo.getFirst(), "The UserInfo should be correctly mapped from the User");
    }
    
    @Test
    @DisplayName("Given an empty list of Users from the repository, when fetchAllUsers is called, then an empty list of UserInfo is returned")
    void fetchAllUsers_EmptyList() {
        // Arrange
        when(userRepository.findAll()).thenReturn(Collections.emptyList());
        
        // Act
        List<UserInfo> userInfos = userDatabaseService.fetchAllUsers();
        
        // Assert
        assertNotNull(userInfos, "The list of UserInfo should not be null");
        assertTrue(userInfos.isEmpty(), "The list of UserInfo should be empty");
    }
    
    @Test
    @DisplayName("Given the repository throws an exception, when fetchAllUsers is called, then the exception is propagated")
    void fetchAllUsers_Exception() {
        // Arrange
        when(userRepository.findAll()).thenThrow(new RuntimeException("Database error"));
        
        // Act & Assert
        assertThrows(RuntimeException.class, () -> userDatabaseService.fetchAllUsers());
    }
    
}