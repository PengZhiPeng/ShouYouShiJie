package com.example.acer.shouyoushijie.view;

import android.app.Activity;

import com.example.acer.shouyoushijie.models.VideosEntity;

import java.util.List;

/**
 * Created by acer on 2016/4/3.
 */
public interface IVideoView {
    Activity getActivity();
    void showVideosList(List<VideosEntity> videosList);
}
