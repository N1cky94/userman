package be.archilios.usermanagement.views.util;

import com.vaadin.flow.component.notification.Notification;

public class AppNotification extends Notification {
    public AppNotification(String text) {
        super(text, 3000, Position.MIDDLE);
    }
}
