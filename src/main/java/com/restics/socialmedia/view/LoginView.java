package com.restics.socialmedia.view;

import com.restics.socialmedia.CurrentUser;
import com.restics.socialmedia.service.AuthService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

// @Route("login")
// public class LoginView extends VerticalLayout {
//     public LoginView() {
//         setSizeFull();
//         setAlignItems(Alignment.CENTER);
//         setJustifyContentMode(JustifyContentMode.CENTER);

//         add(new H1("Social Media App"));

//         LoginForm loginForm = new LoginForm();
//         add(loginForm);
//     }
// }
@Route("") // Empty so that login page is the default page
public class LoginView extends VerticalLayout {
    private final AuthService authService;

    public LoginView(CurrentUser cu, AuthService authService) {
        this.authService = authService;
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        add(new H1("CSE412 Social Media - Team 7"));

        VerticalLayout loginBox = new VerticalLayout();

        loginBox.add(new H1("Login"));
        TextField usernameField = new TextField("Username");
        TextField passwordField = new TextField("Password");
        // EmailField emailField = new EmailField("Email");
        Button loginButton = new Button("Login");

        loginButton.addClickListener(event -> {
            String username = usernameField.getValue();
            String password = passwordField.getValue();

            if (authService.authenticate(cu, username, password)) {
                Notification.show("Login successful!")
                        .addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                getUI().ifPresent(ui -> ui.navigate(MainFeedView.class));
            } else {
                Notification.show("Login failed. Check credentials.")
                        .addThemeVariants(NotificationVariant.LUMO_ERROR);
            }
        });

        Button signUpButton = new Button("Don't have an account? Sign Up");
        signUpButton.addClickListener(event -> {
            getUI().ifPresent(ui -> ui.navigate(SignUpView.class));
        });

        loginBox.add(usernameField);
        loginBox.add(passwordField);
        // loginBox.add(emailField);
        loginBox.add(loginButton);
        loginBox.add(signUpButton);
        loginBox.setAlignItems(Alignment.CENTER);
        loginBox.setJustifyContentMode(JustifyContentMode.CENTER);

        add(loginBox);

        // getElement().setAttribute("theme", Lumo.LIGHT);

        // See login-rich-content.css
        // addClassName("login-rich-content");

        // LoginForm loginForm = new LoginForm();
        // loginForm.getElement().getThemeList().add("dark");

        // add(loginForm);
        // LoginOverlay loginOverlay = new LoginOverlay();
        // loginOverlay.setOpened(true);
        // loginOverlay.setTitle("CSE412 Social Media - Team 7");
        // loginOverlay.setDescription("Built with ♥ by Team 7");

        // loginOverlay.getElement().getThemeList().add("dark");

        // add(loginOverlay);
    }
}