package be.archilios.usermanagement.views.admin.users;

import be.archilios.usermanagement.core.users.CreateNewUserCommand;
import be.archilios.usermanagement.core.users.UserUseCases;
import be.archilios.usermanagement.views.util.AppNotification;
import be.archilios.usermanagement.views.util.Updatable;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dialog.Dialog;
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
    
    private final TextField firstnameField;
    private final TextField lastnameField;
    private final EmailField emailField;
    private final Checkbox isAdminField;
    
    public NewUserDialog(UserUseCases userService, Updatable updatableParent) {
        this.userService = userService;
        this.updatableParent = updatableParent;
        
        this.firstnameField = new TextField("First Name");
        this.lastnameField = new TextField("Last Name");
        this.emailField = new EmailField("Email");
        this.isAdminField = new Checkbox("Admin");
        
        configureFields();
        
        saveButton = createSaveButton();
        cancelButton = createCancelButton();
        configureDialogLayout();
        configureDialogHeaders();
    }
    
    private void configureFields() {
        firstnameField.setRequired(true);
        lastnameField.setRequired(true);
        emailField.setRequired(true);
        
        isAdminField.setEnabled(false);
        
        firstnameField.setRequiredIndicatorVisible(true);
        lastnameField.setRequiredIndicatorVisible(true);
        emailField.setRequiredIndicatorVisible(true);
        
        firstnameField.setClearButtonVisible(true);
        lastnameField.setClearButtonVisible(true);
        emailField.setClearButtonVisible(true);
    }
    
    private void configureDialogLayout() {
        VerticalLayout formLayout = new VerticalLayout(
                firstnameField,
                lastnameField,
                emailField,
                isAdminField
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
                    if (
                            firstnameField.isEmpty() ||
                            lastnameField.isEmpty() ||
                            emailField.isEmpty()
                    ) {
                        AppNotification.show("Please fill in all required fields");
                    } else {
                        CreateNewUserCommand newUserCommand = new CreateNewUserCommand(
                                emailField.getValue(),
                                firstnameField.getValue(),
                                lastnameField.getValue(),
                                isAdminField.getValue()
                        );
                        try {
                            userService.createNewUser(newUserCommand);
                            updatableParent.update();
                            
                            this.close();
                        } catch(DataIntegrityViolationException dive) {
                            AppNotification.show("Something went wrong while saving!\n" +
                                    "Check if the email address is not already in use.");
                        
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
                    firstnameField.clear();
                    lastnameField.clear();
                    emailField.clear();
                    
                    this.close();
                }
        );
        
        return result;
    }
    
    private void configureDialogHeaders() {
        this.setHeaderTitle("New User");
        this.getFooter().add(saveButton, cancelButton);
    }
    
    
}
