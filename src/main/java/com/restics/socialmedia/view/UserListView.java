package com.restics.socialmedia.view;

import com.restics.socialmedia.model.User;
import com.restics.socialmedia.service.UserService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("users")  // This view lives at localhost:8080/users
public class UserListView extends VerticalLayout {

    public UserListView(UserService userService) {
        // Create a table that displays User objects
        Grid<User> grid = new Grid<>(User.class, false);

        // Define which columns to show
        grid.addColumn(User::userId).setHeader("User Id");
        grid.addColumn(User::name).setHeader("Username");
        grid.addColumn(User::password).setHeader("Password");
        grid.addColumn(User::email).setHeader("Email");
        grid.addColumn(User::profilePicture).setHeader("Profile Picture");
        grid.addColumn(User::bio).setHeader("Bio");
        grid.addColumn(User::createdAt).setHeader("Created At");

        // Load data from database
        grid.setItems(userService.getAllUsers());

        // Add the grid to the page
        add(grid);

        // Make it fill the page
        setSizeFull();
        grid.setSizeFull();
    }
}