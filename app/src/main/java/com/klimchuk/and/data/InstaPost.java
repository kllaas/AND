package com.klimchuk.and.data;

/**
 * Created by alexey on 13.05.17.
 */

public class InstaPost {

    private String imageUrl;

    private String likesCount;

    private String date;

    private String userImageUrl;

    private String userName;

    public InstaPost(String imageUrl, String likesCount, String date, String userImageUrl, String userName) {
        this.imageUrl = imageUrl;
        this.likesCount = likesCount;
        this.date = date;
        this.userImageUrl = userImageUrl;
        this.userName = userName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(String likesCount) {
        this.likesCount = likesCount;
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