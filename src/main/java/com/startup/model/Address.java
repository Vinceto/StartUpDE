package com.startup.model;

public class Address {
    private int id;
    private String name;
    private String number;
    private int userId;

    public Address(int id, String name, String number, int userId) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.userId = userId;
    }

    public Address() {

    }

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
