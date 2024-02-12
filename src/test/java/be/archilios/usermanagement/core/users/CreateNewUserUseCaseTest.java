package be.archilios.usermanagement.core.users;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateNewUserUseCaseTest {
    @Mock
    private UserRepository userRepository;
    
    private UserDatabaseService userDatabaseService;
    
    @BeforeEach
    void setUp() {
        userDatabaseService = new UserDatabaseService(userRepository);
    }
    
    @Test
    @DisplayName("Given a new user, when the user is saved, then the user should be correctly created, saved to the repository and returned")
    void correctlyCreateNewUser() {
        when(userRepository.save(any()))
                .thenReturn(
                    UserMother.get()
                            .asDeactivated()
                            .build()
                );
        CreateNewUserCommand newUser = UserMother.get()
                .asDeactivated()
                .buildAsNewUserCommand();
        UserInfo newUserInfo = UserMother.get()
                .asDeactivated()
                .buildAsUserInfo();
        
        UserInfo createdUser = userDatabaseService.createNewUser(newUser);
        
        assertNotNull(createdUser, "The created user should not be null");
        assertEquals(newUserInfo, createdUser, "The created user should be correctly returned from the database");
    }
}
