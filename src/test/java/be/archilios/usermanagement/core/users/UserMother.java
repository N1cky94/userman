package be.archilios.usermanagement.core.users;

import java.util.ArrayList;
import java.util.List;

public class UserMother {
    private Long id = 1L;
    private String email = "nick@archilios.be";
    private String firstName = "Nick";
    private String lastName = "Bauters";
    private boolean activeAccount = true;
    
    public static UserMother get() {
        return new UserMother();
    }
    
    public User build() {
        return new User(id, email, firstName, lastName, activeAccount);
    }
    
    public List<User> buildList() {
        List<User> users = new ArrayList<>();
        users.add(build());
        return users;
    }
}
