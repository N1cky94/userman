package be.archilios.usermanagement.core.users;

import java.util.List;

import static org.reflections.util.ConfigurationBuilder.build;

class UserMother {
    private Long id = 1L;
    private String email = "nick@archilios.be";
    private String firstName = "Nick";
    private String lastName = "Bauters";
    private boolean active = true;
    
    public static UserMother get() {
        return new UserMother();
    }
    
    public User build() {
        return new User(id, email, firstName, lastName, active);
    }
    
    public List<User> buildList() {
        return List.of(build());
    }
    
    public UserInfo buildAsUserInfo() {
        return new UserInfo(id, email, firstName, lastName, active);
    }

}