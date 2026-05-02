package com.restics.socialmedia.view;

import com.restics.socialmedia.CurrentUser;
import com.restics.socialmedia.model.User;
import com.restics.socialmedia.service.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.util.List;

@Route(value = "users", layout = MainLayout.class)
public class UserListView extends VerticalLayout {

    public UserListView(UserService userService, CurrentUser currentUser) {

        User me = currentUser.get();

        // Search bar implemented right here for now, feel free to add service
        TextField searchField = new TextField();
        searchField.setPlaceholder("Search users...");
        searchField.setWidth("300px");

        // Grid
        Grid<User> grid = new Grid<>(User.class, false);

        grid.addColumn(User::userId).setHeader("User Id");
        grid.addColumn(User::name).setHeader("Username");
        grid.addColumn(User::email).setHeader("Email");
        grid.addColumn(User::bio).setHeader("Bio");
        grid.addColumn(User::createdAt).setHeader("Created At");

        // Follow button column
        grid.addComponentColumn(user -> {
            // Don't show button for yourself
            if (me != null && me.userId() == user.userId()) {
                return new Button("You");
            }

            boolean isFollowing = userService.isFollowing(me.userId(), user.userId());

            Button followBtn = new Button(isFollowing ? "Unfollow" : "Follow");

            followBtn.addClickListener(e -> {
                if (userService.isFollowing(me.userId(), user.userId())) {
                    userService.unfollow(me.userId(), user.userId());
                    followBtn.setText("Follow");
                } else {
                    userService.follow(me.userId(), user.userId());
                    followBtn.setText("Unfollow");
                }
            });

            return followBtn;
        }).setHeader("Action");

        // Initial data load
        List<User> users = userService.getAllUsers();
        grid.setItems(users);

        // Frontend Search logic
        searchField.addValueChangeListener(e -> {
            String query = e.getValue().toLowerCase();

            List<User> filtered = userService.getAllUsers().stream()
                    .filter(u -> u.name().toLowerCase().contains(query)
                              || u.email().toLowerCase().contains(query))
                    .toList();

            grid.setItems(filtered);
        });

        add(searchField, grid);

        setSizeFull();
        grid.setSizeFull();
    }
}
