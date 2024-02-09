package be.archilios.usermanagement.views;

import be.archilios.usermanagement.security.SecurityService;
import be.archilios.usermanagement.views.admin.users.UserManagementView;
import be.archilios.usermanagement.views.dashboard.DashboardView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.vaadin.lineawesome.LineAwesomeIcon;

import static java.util.Objects.nonNull;

/**
 * The main view is a top-level placeholder for other views.
 */
public class MainLayout extends AppLayout {

    private H2 viewTitle;
    private final SecurityService security;

    public MainLayout(SecurityService security) {
        this.security = security;
        
        setPrimarySection(Section.DRAWER);
        addDrawerContent();
        addHeaderContent();
    }

    private void addHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.setAriaLabel("Menu toggle");

        viewTitle = new H2();
        viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

        addToNavbar(true, toggle, viewTitle);
        
        setLogoutButtonIfLogin();
    }
    
    private void setLogoutButtonIfLogin() {
        if (nonNull(security.getAuthenticatedUser())) {
            Button logoutButton = new Button("Logout");
            
            logoutButton.addClickListener(e -> {
                security.logout();
            });
            
            logoutButton.setIcon(VaadinIcon.SIGN_OUT.create());
            logoutButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
            logoutButton.getStyle().set("margin-left", "2rem");
            addToNavbar(logoutButton);
        }
    }

    private void addDrawerContent() {
        H1 appName = new H1("UserMan");
        appName.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);
        Header header = new Header(appName);

        Scroller userScroller = new Scroller(createNavigation());

        addToDrawer(header, userScroller);
        
        if (security.isAuthenticatedUserAdmin()) {
            Scroller adminScroller = new Scroller(createAdminNavigation());
            addToDrawer(adminScroller);
        }
        
        addToDrawer(createFooter());
    }

    private SideNav createNavigation() {
        SideNav nav = new SideNav();

        nav.addItem(new SideNavItem("Dashboard", DashboardView.class, LineAwesomeIcon.HOME_SOLID.create()));

        return nav;
    }
    
    private SideNav createAdminNavigation() {
        SideNav nav = new SideNav();
        nav.setLabel("Admin");
        nav.setCollapsible(true);
        
        nav.addItem(new SideNavItem("Users", UserManagementView.class, LineAwesomeIcon.USERS_COG_SOLID.create()));

        return nav;
    }

    private Footer createFooter() {
        Footer layout = new Footer();

        return layout;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }
}
