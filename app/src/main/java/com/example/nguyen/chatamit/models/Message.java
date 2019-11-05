package com.example.nguyen.chatamit.models;

public class Message {
    private String content;
    private User user;
    private Sticker mSticker;

    public Sticker getmSticker() {
        return mSticker;
    }

    public void setmSticker(Sticker mSticker) {
        this.mSticker = mSticker;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
