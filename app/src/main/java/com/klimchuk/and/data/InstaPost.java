package com.klimchuk.and.data;

import java.util.ArrayList;

/**
 * Created by alexey on 13.05.17.
 */

public class InstaPost {

    private String id;

    private String imageUrl;

    private int likesCount;

    private String date;

    private String userImageUrl;

    private String userName;

    private ArrayList<Tag> tags;

    public InstaPost(String id, String imageUrl, int likesCount, String date, String userImageUrl, String userName) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.likesCount = likesCount;
        this.date = date;
        this.userImageUrl = userImageUrl;
        this.userName = userName;
    }

    public InstaPost() {

    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUserImageUrl() {
        return userImageUrl;
    }

    public void setUserImageUrl(String userImageUrl) {
        this.userImageUrl = userImageUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}