package be.archilios.usermanagement.security.user;

public class UserMother {
    private Long id = 1L;
    private String email = "nick@archilios.be";
    private String password = "password";
    private ApplicationRole role = ApplicationRole.USER;
    private boolean activeAccount = true;
    
    public static UserMother get() {
        return new UserMother();
    }
    
    public UserMother asAdmin() {
        this.role = ApplicationRole.ADMIN;
        return this;
    }
    
    public SecurityUser build() {
        return new SecurityUser(id, email, password, role, activeAccount);
    }
}
