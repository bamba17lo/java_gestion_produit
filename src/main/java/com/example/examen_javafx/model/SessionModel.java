package com.example.examen_javafx.model;

public class SessionModel {

    private static SessionModel instance;
    private String userId;

    public SessionModel() {

    }

    public static SessionModel getInstance() {
        if (instance == null) {
            instance = new SessionModel();
        }
        return instance;
    }

    public void login(String userId) {
        this.userId = userId;
    }

    public void logout() {
        this.userId = null;
    }

    public boolean isLoggedIn() {
        return userId != null;
    }

    public String getUserId() {
        return userId;
    }
}
