package com.restics.socialmedia.view;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility;

public class MainLayout extends AppLayout {

    public MainLayout() {
        createHeader();
    }

    // NEED TO: Add tabs to main layout and make them clickable and center them.
    private void createHeader() {
        H1 logo = new H1("CSE412 Social Media - Team 7");
        logo.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.MEDIUM);

        HorizontalLayout header = new HorizontalLayout(logo);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setWidthFull();
        header.addClassNames(LumoUtility.Padding.Vertical.NONE, LumoUtility.Padding.Horizontal.MEDIUM);

        HorizontalLayout tabsLayout = new HorizontalLayout();
        tabsLayout.getStyle().set("position", "absolute");
        tabsLayout.getStyle().set("left", "50%");
        tabsLayout.getStyle().set("transform", "translateX(-50%)");

        // tabsLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        // tabsLayout.setWidthFull();
        // tabsLayout.addClassNames(LumoUtility.Margin.Left.AUTO,
        // LumoUtility.Margin.Right.AUTO);

        Tab mainTab = new Tab(new RouterLink("Main", MainFeedView.class));
        Tab followingTab = new Tab(new RouterLink("Following", FollowingFeedView.class));
        Tab profileTab = new Tab(new RouterLink("Profile", ProfileView.class));
        Tab userListTab = new Tab(new RouterLink("Users", UserListView.class));

        Tabs tabs = new Tabs(mainTab, followingTab, profileTab, userListTab);

        tabsLayout.add(tabs);

        // add(tabs);

        addToNavbar(header, tabsLayout);
    }
}