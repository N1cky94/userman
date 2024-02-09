package be.archilios.usermanagement.views.error;

import be.archilios.usermanagement.views.MainLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@PageTitle("Oopss! Access Denied!")
@Route(value = "error/400", layout = MainLayout.class)
@PermitAll
public class AccessDeniedView extends VerticalLayout {
    public AccessDeniedView() {
        addClassName("acces-denied-view");
        setSizeFull();
        H2 title = new H2("Oopss! Access Denied!");
        H3 subTitle = new H3("You are not allowed to access this page!");
        Div message = new Div();
        message.setText("You are not allowed to access this page. Please contact the administrator if you think this is a mistake.");
        
        setHeightFull();
        setAlignItems(Alignment.CENTER);
        setHorizontalComponentAlignment(Alignment.CENTER, title, message);
        add(title, subTitle, message);
    }
}
