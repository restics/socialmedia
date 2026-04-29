package com.restics.socialmedia.view;

import java.util.List;

import org.atmosphere.config.service.Post;

import com.restics.socialmedia.service.PostService; // Need to replace with new Post service
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;

public class PostCard extends VerticalLayout {

    private int currentLikes;

    public PostCard(Post post, PostService postService, String currentUser, boolean isReply) {
        this.currentLikes = post.likes();

        // Post card styling
        getStyle().set("border", "1px solid #e0e0e0").set("border-radius", "12px").set("padding", "16px");
        if (isReply) getStyle().set("background", "#f9f9f9").set("box-shadow", "none");
        else getStyle().set("box-shadow", "0 1px 3px rgba(0,0,0,0.1)");
        setWidthFull();

        Span authorSpan = new Span(post.author());
        authorSpan.getStyle().set("font-weight", "bold");
        
        Span timeSpan = new Span(post.getFormattedTimestamp());
        timeSpan.getStyle().set("color", "gray").set("margin-left", "auto");

        HorizontalLayout header = new HorizontalLayout(authorSpan, timeSpan);
        header.setWidthFull();

        // Content
        Paragraph contentText = new Paragraph(post.text());
        add(header, contentText);
        
        if (post.imageUrl() != null && !post.imageUrl().isBlank()) {
            Image image = new Image(post.imageUrl(), "Post Image");
            image.setMaxWidth("100%");
            add(image);
        }

        HorizontalLayout actions = new HorizontalLayout();
        
        // Like
        Button likeBtn = new Button(" " + currentLikes, new Icon(VaadinIcon.HEART));
        likeBtn.addThemeVariants(ButtonVariant.LUMO_TERTIARY, ButtonVariant.LUMO_ERROR);
        likeBtn.addClickListener(e -> {
            postService.likePost(post.postId());
            currentLikes++;
            likeBtn.setText(" " + currentLikes);
        });
        actions.add(likeBtn);

        if (!isReply) {
            // Reply
            Button replyBtn = new Button(" " + post.replies(), new Icon(VaadinIcon.COMMENT));
            replyBtn.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
            replyBtn.addClickListener(e -> openReplyDialog(post, postService, currentUser));
            actions.add(replyBtn);
        }

        // Edit/Delete for Owner
        if (currentUser != null && currentUser.equalsIgnoreCase(post.author())) {
            Button editBtn = new Button("Edit", new Icon(VaadinIcon.EDIT));
            editBtn.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
            editBtn.addClickListener(e -> openEditDialog(post, postService, contentText));
            
            Button deleteBtn = new Button("Delete", new Icon(VaadinIcon.TRASH));
            deleteBtn.addThemeVariants(ButtonVariant.LUMO_TERTIARY, ButtonVariant.LUMO_ERROR);
            deleteBtn.addClickListener(e -> openDeleteDialog(post, postService));
            
            actions.add(editBtn, deleteBtn);
        }

        add(actions);
    }

    private void openReplyDialog(Post post, PostService postService, String currentUser) {
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle("Replies");
        VerticalLayout list = new VerticalLayout();
        
        List<Post> replies = postService.findReplies(post.postId());
        replies.forEach(r -> list.add(new PostCard(r, postService, currentUser, true)));
        
        TextArea field = new TextArea("Your reply");
        field.setWidthFull();
        
        Button submit = new Button("Reply", e -> {
            postService.replyToPost(post.postId(), field.getValue());
            dialog.close();
            Notification.show("Reply posted");
        });
        submit.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        
        dialog.add(list, new Hr(), field);
        dialog.getFooter().add(new Button("Cancel", e -> dialog.close()), submit);
        dialog.open();
    }

    private void openEditDialog(Post post, PostService postService, Paragraph textComponent) {
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle("Edit Post");
        TextArea field = new TextArea();
        field.setValue(post.text());
        field.setWidthFull();
        
        Button save = new Button("Save", e -> {
            String newText = field.getValue();
            postService.updatePost(post.postId(), newText);
            textComponent.setText(newText);
            dialog.close();
            Notification.show("Updated");
        });
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        
        dialog.add(field);
        dialog.getFooter().add(new Button("Cancel", e -> dialog.close()), save);
        dialog.open();
    }

    private void openDeleteDialog(Post post, PostService postService) {
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle("Delete Post?");
        dialog.add(new Paragraph("Are you sure?"));
        
        Button delete = new Button("Delete", e -> {
            postService.deletePost(post.postId());
            this.setVisible(false);
            dialog.close();
        });
        delete.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
        
        dialog.getFooter().add(new Button("Cancel", e -> dialog.close()), delete);
        dialog.open();
    }

    public PostCard(Post post, PostService postService, String currentUser) {
        this(post, postService, currentUser, false);
    }
}
