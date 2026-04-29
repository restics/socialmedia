package com.restics.socialmedia.view;

import java.util.List;

import org.atmosphere.config.service.Post;

import com.restics.socialmedia.model.User;
// import com.restics.socialmedia.service.PostService; // Need to replace with new Post service
import com.restics.socialmedia.service.UserService;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "profile", layout = MainLayout.class)
@PageTitle("Profile | SocialMedia")
public class ProfileView extends VerticalLayout {

    private final PostService postService;

    public ProfileView(UserService userService, PostService postService) {
        this.postService = postService;

        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setSpacing(true);
        setPadding(true);

        User currentUser = userService.getCurrentUser();
        if (currentUser != null) {
            renderProfile(currentUser);
        } else {
            add(new H2("User not found"));
        }
    }

    private void renderProfile(User user) {
        VerticalLayout header = new VerticalLayout();
        header.setAlignItems(Alignment.CENTER);
        header.setPadding(true);
        header.getStyle()
                .set("background", "white")
                .set("border-radius", "15px")
                .set("box-shadow", "0 4px 6px rgba(0,0,0,0.1)")
                .set("margin-bottom", "20px");
        header.setMaxWidth("600px");

        Image avatar = new Image(
                "https://images.unsplash.com/photo-1650724073780-93ab55f77a65?w=500&auto=format&fit=crop",// ideally would use user.profilePicture() but our mock data doesn't have real URLs
                "Avatar");
        avatar.setWidth("100px");
        avatar.setHeight("100px");
        avatar.getStyle().set("border-radius", "50%");

        header.add(avatar);
        header.add(new H2(user.name()));

        Span email = new Span(user.email());
        email.getStyle().set("color", "gray");
        header.add(email);

        Paragraph bio = new Paragraph(user.bio());
        bio.getStyle().set("font-style", "italic");
        header.add(bio);

        add(header);

        // Feed of user's own posts
        VerticalLayout feed = new VerticalLayout();
        feed.setPadding(false);
        feed.setAlignItems(Alignment.CENTER);
        feed.setMaxWidth("600px");

        add(new H2("Personal Posts"));

        List<Post> posts = postService.findPostsByUser(user.userId());
        if (posts.isEmpty()) {
            feed.add(new Span("No posts yet."));
        } else {
            for (Post post : posts) {
                feed.add(new PostCard(post, postService, user.name()));
            }
        }

        add(feed);
    }
}
