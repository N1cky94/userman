package be.archilios.usermanagement.views.admin.users;

import be.archilios.usermanagement.core.users.UserInfo;
import be.archilios.usermanagement.core.users.UserUseCases;
import be.archilios.usermanagement.views.MainLayout;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.LumoIcon;
import jakarta.annotation.security.RolesAllowed;

import java.util.List;

@PageTitle("User Management")
@Route(value = "admin/users", layout = MainLayout.class)
@RolesAllowed("ROLE_ADMIN")
//@AnonymousAllowed
public class UserManagementView extends VerticalLayout {
    private final UserUseCases userService;
    
    private final Grid<UserInfo> usersGrid = new Grid<>(UserInfo.class);
    private GridListDataView<UserInfo> usersDataView;
    
    public UserManagementView(UserUseCases userService) {
        this.userService = userService;
        
        update();
        configureUsersGrid();
        setSizeFull();
        
        add(
            new H1("User Management View"),
            createSearchBar(),
            usersGrid,
            createListActionHeader()
        );
        
    }
    
    private void update() {
        List<UserInfo> users = userService.fetchAllUsers();
        this.usersDataView = usersGrid.setItems(users);
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
        usersGrid.addComponentColumn(
                user -> {
                    Button editButton = new Button(LumoIcon.EDIT.create());
                    editButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
                    editButton.addClickListener(
                            event -> {
                                Notification dialog = new Notification("Not implemented yet", 3000);
                                dialog.setPosition(Notification.Position.MIDDLE);
                                dialog.open();
                            }
                    );
                    editButton.setWidth(50, Unit.PIXELS);
                    return editButton;
                }
        ).setFlexGrow(0);
        usersGrid.addComponentColumn(
                user -> {
                    MenuBar userOptionsMenu = new MenuBar();
                    userOptionsMenu.addThemeVariants(MenuBarVariant.LUMO_TERTIARY);
                    
                    userOptionsMenu.addItem(new H4("Account Management")).setEnabled(false);
                    userOptionsMenu.addItem("Edit user").setEnabled(false);
                    userOptionsMenu.addItem("Activate/Deactivate").setEnabled(false);
                    userOptionsMenu.addItem("Delete user").setEnabled(false);
                    userOptionsMenu.addItem("Reset password").setEnabled(false);
                    userOptionsMenu.addItem(new Hr()).setEnabled(false);
                    userOptionsMenu.addItem(new H4("Messages")).setEnabled(false);
                    userOptionsMenu.addItem("Send message").setEnabled(false);
                    
                    return userOptionsMenu;
                }
        ).setFlexGrow(0);
        
        
        usersGrid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        usersGrid.setSelectionMode(Grid.SelectionMode.MULTI);
    }
    
    private HorizontalLayout createSearchBar() {
        HorizontalLayout result = new HorizontalLayout();
        result.setWidthFull();
        result.setAlignItems(Alignment.BASELINE);
        
        TextField searchField = new TextField();
        searchField.setPlaceholder("Search");
        searchField.setPrefixComponent(VaadinIcon.SEARCH.create());
        searchField.setClearButtonVisible(true);
        searchField.setWidth(50, Unit.PERCENTAGE);
        searchField.setValueChangeMode(ValueChangeMode.ON_CHANGE);
        searchField.addValueChangeListener(e -> usersDataView.refreshAll());
        
        usersDataView.addFilter(
                user -> {
                    String searchTerm = searchField.getValue().trim().toLowerCase();
                    
                    boolean firstNameMatch = user.firstName().toLowerCase().contains(searchTerm);
                    boolean lastNameMatch = user.lastName().toLowerCase().contains(searchTerm);
                    boolean emailMatch = user.email().toLowerCase().contains(searchTerm);
                    
                    return firstNameMatch || lastNameMatch || emailMatch;
                }
        );
        
        result.add(searchField);
        return result;
    }
    
    private HorizontalLayout createListActionHeader() {
        HorizontalLayout result = new HorizontalLayout();
        result.setWidthFull();
        result.setJustifyContentMode(JustifyContentMode.START);
        
        result.add(
                createAddNewUserButton(),
                createRemoveUsersButton(),
                createMessageAllSelectedUsersButton()
        );
        
        return result;
    }
    
    private Button createAddNewUserButton() {
        Button result = new Button("New User", VaadinIcon.PLUS_CIRCLE.create());
        result.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        result.addClickListener(
                event -> {
                    Notification dialog = new Notification("Not implemented yet", 3000);
                    dialog.open();
                }
        );
        
        result.setEnabled(false);
        return result;
    }
    
    private Button createRemoveUsersButton() {
        Button result = new Button("Remove", VaadinIcon.TRASH.create());
        result.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        result.addClickListener(
                event -> {
                    Notification dialog = new Notification("Not implemented yet", 3000);
                    dialog.open();
                }
        );
        
        result.setEnabled(false);
        return result;
    }
    
    private Button createMessageAllSelectedUsersButton() {
        Button result = new Button("Message", VaadinIcon.ENVELOPE.create());
        result.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        result.addClickListener(
                event -> {
                    Notification dialog = new Notification("Not implemented yet", 3000);
                    dialog.open();
                }
        );
        
        result.setEnabled(false);
        return result;
    }
    
    
}
