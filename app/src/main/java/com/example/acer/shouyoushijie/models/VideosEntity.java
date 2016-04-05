package com.example.acer.shouyoushijie.models;

import java.util.List;

/**
 * Created by acer on 2016/4/3.
 */
public class VideosEntity {
    private String id;
    private String title;
    private String description;
    private String thumbnail;
    private String thumbnail_v2;
    private String is_panorama;
    private String duration;
    private String comment_count;
    private String favorite_count;
    private String up_count;
    private String down_count;
    private String published;
    private String copyright_type;
    private String public_type;
    private String state;
    private String category;
    private int view_count;
    private String tags;
    private int paid;
    private String link;
    private String player;

    private UserEntity user;
    private List<String> streamtypes;
    private List<?> operation_limit;

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setThumbnail_v2(String thumbnail_v2) {
        this.thumbnail_v2 = thumbnail_v2;
    }

    public void setIs_panorama(String is_panorama) {
        this.is_panorama = is_panorama;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setComment_count(String comment_count) {
        this.comment_count = comment_count;
    }

    public void setFavorite_count(String favorite_count) {
        this.favorite_count = favorite_count;
    }

    public void setUp_count(String up_count) {
        this.up_count = up_count;
    }

    public void setDown_count(String down_count) {
        this.down_count = down_count;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public void setCopyright_type(String copyright_type) {
        this.copyright_type = copyright_type;
    }

    public void setPublic_type(String public_type) {
        this.public_type = public_type;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setView_count(int view_count) {
        this.view_count = view_count;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public void setPaid(int paid) {
        this.paid = paid;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public void setStreamtypes(List<String> streamtypes) {
        this.streamtypes = streamtypes;
    }

    public void setOperation_limit(List<?> operation_limit) {
        this.operation_limit = operation_limit;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getThumbnail_v2() {
        return thumbnail_v2;
    }

    public String getIs_panorama() {
        return is_panorama;
    }

    public String getDuration() {
        return duration;
    }

    public String getComment_count() {
        return comment_count;
    }

    public String getFavorite_count() {
        return favorite_count;
    }

    public String getUp_count() {
        return up_count;
    }

    public String getDown_count() {
        return down_count;
    }

    public String getPublished() {
        return published;
    }

    public String getCopyright_type() {
        return copyright_type;
    }

    public String getPublic_type() {
        return public_type;
    }

    public String getState() {
        return state;
    }

    public String getCategory() {
        return category;
    }

    public int getView_count() {
        return view_count;
    }

    public String getTags() {
        return tags;
    }

    public int getPaid() {
        return paid;
    }

    public String getLink() {
        return link;
    }

    public String getPlayer() {
        return player;
    }

    public UserEntity getUser() {
        return user;
    }

    public List<String> getStreamtypes() {
        return streamtypes;
    }

    public List<?> getOperation_limit() {
        return operation_limit;
    }
}
