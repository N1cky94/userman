package be.archilios.usermanagement.views.admin.users;

import be.archilios.usermanagement.core.users.CreateNewUserCommand;
import be.archilios.usermanagement.core.users.UserUseCases;
import be.archilios.usermanagement.views.util.Updatable;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import org.springframework.dao.DataIntegrityViolationException;

public class NewUserDialog extends Dialog {
    private final UserUseCases userService;
    private final Updatable updatableParent;
    
    private final Button saveButton;
    private final Button cancelButton;
    
    private final TextField firstNameField;
    private final TextField lastNameField;
    private final EmailField emailField;
    private final Checkbox activateField;
    
    public NewUserDialog(UserUseCases userService, Updatable updatableParent) {
        this.userService = userService;
        this.updatableParent = updatableParent;
        
        this.firstNameField = new TextField("First Name");
        this.lastNameField = new TextField("Last Name");
        this.emailField = new EmailField("Email");
        this.activateField = new Checkbox("Activate");
        
        configureFields();
        
        saveButton = createSaveButton();
        cancelButton = createCancelButton();
        
        configureDialogLayout();
        configureDialogHeaders();
    }
    
    private void configureFields() {
        firstNameField.setRequired(true);
        lastNameField.setRequired(true);
        emailField.setRequired(true);
        
        firstNameField.setRequiredIndicatorVisible(true);
        lastNameField.setRequiredIndicatorVisible(true);
        emailField.setRequiredIndicatorVisible(true);
        
        firstNameField.setClearButtonVisible(true);
        lastNameField.setClearButtonVisible(true);
        emailField.setClearButtonVisible(true);
    }
    
    private void configureDialogLayout() {
        VerticalLayout formLayout = new VerticalLayout(
                firstNameField,
                lastNameField,
                emailField,
                activateField
        );
        
        formLayout.setPadding(false);
        formLayout.setSpacing(false);
        formLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
        formLayout.getStyle().set("width", "18rem").set("max-width", "100%");
        
        add(formLayout);
    }
    
    private Button createSaveButton() {
        Button result = new Button("Save");
        result.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        result.addClickListener(
                event -> {
                    if (firstNameField.isEmpty() || lastNameField.isEmpty() || emailField.isEmpty()) {
                        new Notification("Please fill in all required fields", 3000, Notification.Position.MIDDLE).open();
                    } else {
                        CreateNewUserCommand newUser = new CreateNewUserCommand(
                                emailField.getValue(),
                                firstNameField.getValue(),
                                lastNameField.getValue(),
                                activateField.getValue()
                        );
                        
                        try {
                            userService.createNewUser(newUser);
                            updatableParent.update();
                            
                            this.close();
                        } catch (DataIntegrityViolationException dive) {
                            new Notification("Something went wrong while saving!\n" +
                                    "Check if the email address is not already in use.", 3000, Notification.Position.MIDDLE).open();
                        }
                    }
                }
        );
        
        return result;
    }
    
    private Button createCancelButton() {
        Button result = new Button("Cancel");
        result.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        result.addClickListener(
                event -> {
                    firstNameField.clear();
                    lastNameField.clear();
                    emailField.clear();
                    
                    this.close();
                }
        );
        
        return result;
    }
    
    private void configureDialogHeaders() {
        this.setCloseOnEsc(true);
        this.setDraggable(true);
        this.setHeaderTitle("New User");
        this.getFooter().add(saveButton, cancelButton);
    }
}
