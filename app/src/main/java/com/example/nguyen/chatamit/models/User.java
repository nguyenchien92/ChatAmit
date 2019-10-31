package com.example.nguyen.chatamit.models;

import java.io.Serializable;

public class User implements Serializable {
    private String name, time, description;
    private int imageUser;
    private int dataType;

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public User(String name, String time, String description, int imageUser, int dataType) {
        this.name = name;
        this.time = time;
        this.description = description;
        this.imageUser = imageUser;
        this.dataType = dataType;
    }

    public User(int ic_logout, String description, int dataType) {
        this.imageUser = ic_logout;
        this.description = description;
        this.dataType = dataType;
    }
    public User(String name, int dataType)
    {
        this.name = name;
        this.dataType = dataType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImageUser() {
        return imageUser;
    }

    public void setImageUser(int imageUser) {
        this.imageUser = imageUser;
    }
}
