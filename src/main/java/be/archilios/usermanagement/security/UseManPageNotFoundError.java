package be.archilios.usermanagement.security;

import be.archilios.usermanagement.views.error.AccessDeniedView;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.ErrorParameter;
import com.vaadin.flow.router.NotFoundException;
import com.vaadin.flow.router.RouteNotFoundError;
import jakarta.servlet.http.HttpServletResponse;

import java.io.Serial;

public class UseManPageNotFoundError extends RouteNotFoundError {
    @Serial
    private static final long serialVersionUID = 21358157369L;
    
    @Override
    public int setErrorParameter(final BeforeEnterEvent event, final ErrorParameter<NotFoundException> parameter) {
        event.forwardTo(AccessDeniedView.class);
        return HttpServletResponse.SC_NOT_FOUND;
    }
}
