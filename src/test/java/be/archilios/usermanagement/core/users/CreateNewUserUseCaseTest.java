package be.archilios.usermanagement.core.users;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
    void correctlyCreateNewUser() {
    
    }
}
