pluginManagement {
    repositories {
        maven { url = uri("https://maven.vaadin.com/vaadin-prereleases") }
        gradlePluginPortal()
    }
    plugins {
        id("com.vaadin") version "25.1.3"
    }
}

rootProject.name = "socialmedia"