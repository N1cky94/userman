package be.archilios.usermanagement.views.admin.users;

import be.archilios.usermanagement.core.users.UserInfo;
import be.archilios.usermanagement.core.users.UserUseCases;
import be.archilios.usermanagement.views.MainLayout;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import jakarta.annotation.security.RolesAllowed;

import java.util.List;

@PageTitle("User Management")
@Route(value = "admin/users", layout = MainLayout.class)
//@RolesAllowed("ROLE_ADMIN")
@AnonymousAllowed
public class UserManagementView extends VerticalLayout {
    private final UserUseCases userService;
    
    private final Grid<UserInfo> usersGrid = new Grid<>(UserInfo.class);
    private GridListDataView<UserInfo> usersDataView;
    
    public UserManagementView(UserUseCases userService) {
        this.userService = userService;
        
        List<UserInfo> users = userService.fetchAllUsers();
        this.usersDataView = usersGrid.setItems(users);
        
        configureUsersGrid();
        
        setSizeFull();
        
        add(
            new H1("User Management View"),
            usersGrid
        );
        
    }
    
    private void configureUsersGrid() {
        usersGrid.addColumn(UserInfo::firstName).setHeader("First Name");
        usersGrid.addColumn(UserInfo::lastName).setHeader("Last Name");
        usersGrid.addColumn(UserInfo::email).setHeader("Email");
        usersGrid.addComponentColumn(
                user -> {
                    Checkbox activeAccount = new Checkbox();
                    activeAccount.setValue(user.active());
                    activeAccount.setEnabled(false);
                    return activeAccount;
                }
        ).setHeader("Active Account").setFlexGrow(0);
        
        usersGrid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        usersGrid.setSelectionMode(Grid.SelectionMode.MULTI);
    }
}
