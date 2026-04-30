package com.restics.socialmedia.view;

import com.restics.socialmedia.service.RegistrationService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
// import com.restics.socialmedia.service.RegistrationService; // Need to replace with new Registration service
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route(value = "signup")
public class SignUpView extends VerticalLayout {
    private final RegistrationService registrationService;

    public SignUpView(RegistrationService registrationService) {
        this.registrationService = registrationService;
        VerticalLayout signUpBox = new VerticalLayout();
        signUpBox.add(new H1("Sign Up"));

        TextField usernameField = new TextField("Username");
        TextField passwordField = new TextField("Password");
        EmailField emailField = new EmailField("Email");
        Button signUpButton = new Button("Sign Up");

        signUpBox.add(usernameField, passwordField, emailField, signUpButton);
        signUpBox.setAlignItems(Alignment.CENTER);
        signUpBox.setJustifyContentMode(JustifyContentMode.CENTER);

        add(signUpBox);

        signUpButton.addClickListener(event -> {
            String username = usernameField.getValue();
            String password = passwordField.getValue();
            String email = emailField.getValue();

            if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
                Notification.show("Please fill in all fields")
                        .addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else {
                if (registrationService.register(username, password, email)) {
                    Notification.show("Registered successfully!")
                            .addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                    getUI().ifPresent(ui -> ui.navigate(LoginView.class));
                } else {
                    Notification.show("Registration failed. Username may already be taken.")
                            .addThemeVariants(NotificationVariant.LUMO_ERROR);
                }
            }
        });
    }
}
