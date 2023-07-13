package com.nikki.newsappjava.model;

public class NewsModels {
    public String content, image, title, fileName, description;


    public NewsModels(String content, String image, String title, String fileName, String description) {
        this.content = content;
        this.image = image;
        this.title = title;
        this.fileName = fileName;
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
