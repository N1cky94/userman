package be.archilios.usermanagement.views.admin.users;

import be.archilios.usermanagement.core.users.UserInfo;
import be.archilios.usermanagement.core.users.UserUseCases;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.component.menubar.MenuBar;

public class UserActionsMenuBar extends MenuBar {
    private final UserUseCases useCases;
    private final UserInfo userInfo;
    
    public UserActionsMenuBar(UserInfo userInfo, UserUseCases useCases) {
        this.useCases = useCases;
        this.userInfo = userInfo;
        
        addThemeVariants(MenuBarVariant.LUMO_TERTIARY);
        
        addItem(new H4("Account Management")).setEnabled(false);
        addItem("Edit user").setEnabled(false);
        addItem("Activate/Deactivate").setEnabled(false);
        addItem("Delete user").setEnabled(false);
        addItem("Reset password").setEnabled(false);
        addItem(new Hr()).setEnabled(false);
        addItem(new H4("Messages")).setEnabled(false);
        addItem("Send message").setEnabled(false);
    }
}
