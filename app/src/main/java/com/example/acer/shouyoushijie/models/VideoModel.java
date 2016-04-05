package com.example.acer.shouyoushijie.models;

import java.util.List;

/**
 * Created by acer on 2016/4/3.
 */
public class VideoModel implements IVideoModel{
    private int total;
    private List<VideosEntity> videos;

    public VideoModel(List<VideosEntity> videos) {
        this.videos = videos;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setVideos(List<VideosEntity> videos) {
        this.videos = videos;
    }

    public int getTotal() {
        return total;
    }

    public List<VideosEntity> getVideos() {
        return videos;
    }
}
