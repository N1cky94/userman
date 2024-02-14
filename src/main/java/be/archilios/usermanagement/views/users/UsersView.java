package be.archilios.usermanagement.views.users;

import be.archilios.usermanagement.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

@PageTitle("Users")
@Route(value = "users", layout = MainLayout.class)
@Uses(Icon.class)
@RolesAllowed("ROLE_ADMIN")
public class UsersView extends Composite<VerticalLayout> {

    public UsersView() {
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
    }
}
