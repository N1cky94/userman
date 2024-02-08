package be.archilios.usermanagement.views.admin.users;

import be.archilios.usermanagement.core.users.UserUseCases;
import be.archilios.usermanagement.views.MainLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@PageTitle("User Management")
@Route(value = "admin/users", layout = MainLayout.class)
@AnonymousAllowed
public class UserManagementView extends VerticalLayout {
    private final UserUseCases userService;
    
    public UserManagementView(UserUseCases userService) {
        this.userService = userService;
        
        setSizeFull();
        add(new H1("User Management View"));
        
    }
}
