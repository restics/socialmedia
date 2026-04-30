package com.restics.socialmedia;


import com.restics.socialmedia.model.User;
import com.vaadin.flow.spring.annotation.VaadinSessionScope;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@VaadinSessionScope
public class CurrentUser {
    private User user;

    public User get() { return user; }
    public void set(User user) { this.user = user; }
    public boolean isLoggedIn() { return user != null; }
}
