package be.archilios.usermanagement.security.user;

public class SecurityUserMother {
    private Long id = 1L;
    private String email = "nick@archilios.be";
    private String password = "password";
    private ApplicationRole role = ApplicationRole.USER;
    private boolean activeAccount = true;
    
    public static SecurityUserMother get() {
        return new SecurityUserMother();
    }
    
    public SecurityUserMother asAdmin() {
        this.role = ApplicationRole.ADMIN;
        return this;
    }
    
    public SecurityUser build() {
        return new SecurityUser(id, email, password, role, activeAccount);
    }
}
