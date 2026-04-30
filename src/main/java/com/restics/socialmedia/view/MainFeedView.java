package com.restics.socialmedia.view;

import com.restics.socialmedia.model.User;
// import com.restics.socialmedia.service.PostService; Need to replace with new Post service 
import com.restics.socialmedia.service.PostService;
import com.restics.socialmedia.service.UserService;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "main-feed", layout = MainLayout.class)
@PageTitle("Timeline | SocialMedia")
public class MainFeedView extends VerticalLayout {

    public MainFeedView(PostService postService, UserService userService) {
        // Get the current user's name so that we can pass it to the PostCard for the edit/delete buttons
        User currentUser = userService.getCurrentUser();
        String currentUserName = currentUser != null ? currentUser.name() : null;

        // Centersthe feed
        setAlignItems(Alignment.CENTER);
        setMaxWidth("600px");
        getStyle().set("margin", "0 auto");

        add(new H2("Timeline"));
        Paragraph subtitle = new Paragraph("No algorithms, just pure chronological feed.");
        subtitle.getStyle().set("color", "gray");
        add(subtitle);

        postService.findAllPosts().forEach(post -> { // display all posts
            add(new PostCard(post, postService, currentUserName));
        });
    }
}
