package com.example.library.model;

public class Member {
    private int id;
    private String name;
    private String position;
    private String contact;

    public Member(int id, String name, String position, String contact) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.contact = contact;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
