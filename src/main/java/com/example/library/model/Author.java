package com.example.library.model;

public class Author {
    private int authorId;
    private String name;
    private String contact;

    public Author(int authorId, String name, String contact) {
        this.authorId = authorId;
        this.name = name;
        this.contact = contact;
    }

    // Getters and Setters
    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
