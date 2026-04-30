package com.restics.socialmedia.view;

import com.restics.socialmedia.CurrentUser;
import com.restics.socialmedia.model.User;
// import com.restics.socialmedia.service.PostService; // Need to replace with new Post service
import com.restics.socialmedia.service.PostService;
import com.restics.socialmedia.service.UserService;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

// tbh I don't think we even have mock data for following people
// now we do - michael(restics)

@Route(value = "following-feed", layout = MainLayout.class)
@PageTitle("Following | SocialMedia")
public class FollowingFeedView extends VerticalLayout {

    public FollowingFeedView(CurrentUser cu, PostService postService, UserService userService) {
        User currentUser = cu.get();
        String currentUserName = currentUser != null ? currentUser.name() : null;

        // Centers the feed
        setAlignItems(Alignment.CENTER);
        setMaxWidth("600px");
        getStyle().set("margin", "0 auto");

        add(new H2("Following Feed"));
        Paragraph subtitle = new Paragraph("Posts from people you follow.");
        subtitle.getStyle().set("color", "gray");
        add(subtitle);

        assert currentUser != null;
        postService.findFollowingPosts(currentUser.userId()).forEach(post -> {
            add(new PostCard(post, postService, currentUser));
        });
    }
}
