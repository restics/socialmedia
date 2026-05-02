
package com.restics.socialmedia.view;

import com.restics.socialmedia.CurrentUser;
import com.restics.socialmedia.model.User;
// import com.restics.socialmedia.service.PostService; Need to replace with new Post service 
import com.restics.socialmedia.service.PostService;
import com.restics.socialmedia.service.UserService;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "main-feed", layout = MainLayout.class)
@PageTitle("Timeline | SocialMedia")
public class MainFeedView extends VerticalLayout {

    public MainFeedView(CurrentUser cu, PostService postService, UserService userService) {
        // Get the current user's name so that we can pass it to the PostCard for the edit/delete buttons
        User currentUser = cu.get();
        String currentUserName = currentUser != null ? currentUser.name() : null;

        // Centersthe feed
        setAlignItems(Alignment.CENTER);
        setMaxWidth("600px");
        getStyle().set("margin", "0 auto");

        add(new H2("Timeline"));
        Paragraph subtitle = new Paragraph("No algorithms, just pure chronological feed.");
        subtitle.getStyle().set("color", "gray");
        add(subtitle);
        // Create Post Button
Button createPostButton = new Button("Create Post");

// Dialog for creating a post
Dialog dialog = new Dialog();
dialog.setWidth("400px");

TextArea contentField = new TextArea("What's on your mind?");
contentField.setWidthFull();

Button submitButton = new Button("Post", e -> {
    String content = contentField.getValue();

    if (content == null || content.isBlank()) {
        Notification.show("Post cannot be empty");
        return;
    }

    if (currentUser == null) {
        Notification.show("You must be logged in");
        return;
    }

    // Call service
    postService.createPost(currentUser.userId(), content);

    dialog.close();
    Notification.show("Post created!");

    // Refresh feed 
    getUI().ifPresent(ui -> ui.getPage().reload());
});

dialog.add(contentField, submitButton);

// Open dialog on click
createPostButton.addClickListener(e -> dialog.open());

// Add button 
add(createPostButton);
        postService.findAllPosts().forEach(post -> { // display all posts
            add(new PostCard(post, postService, currentUser));
        });
    }
}
