package com.klimchuk.and.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by alexey on 13.05.17.
 */

public class InstaPost {

    @SerializedName("created_time")
    @Expose
    private String createdTime;
    @SerializedName("profile_picture")
    @Expose
    private String profilePicture;
    @SerializedName("likes")
    @Expose
    private Integer likes;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("profile_username")
    @Expose
    private String profileUsername;
    @SerializedName("tags")
    @Expose
    private List<String> tags = null;
    @SerializedName("id")
    @Expose
    private String id;

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getProfileUsername() {
        return profileUsername;
    }

    public void setProfileUsername(String profileUsername) {
        this.profileUsername = profileUsername;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}