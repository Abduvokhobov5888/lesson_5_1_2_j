package com.example.a512j.Model;

public class ItemModel {
    int lottieId;
    String title, description;

    public ItemModel(int lottieId, String title, String description) {
        this.lottieId = lottieId;
        this.title = title;
        this.description = description;
    }

    public ItemModel() {
    }

    public int getLottieId() {
        return lottieId;
    }

    public void setLottieId(int lottieId) {
        this.lottieId = lottieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
