package com.example.mcroft.myapplication.First;

public class Product {
    private int imageId;
    private String title;
    private String description;
    private String range;
    private String mny;

    public Product(int imageId, String title, String description, String range, String mny) {
        this.imageId = imageId;
        this.title = title;
        this.description = description;
        this.range = range;
        this.mny = mny;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
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

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getMny() {
        return mny;
    }

    public void setMny(String mny) {
        this.mny = mny;
    }
}
