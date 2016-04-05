package com.example.acer.shouyoushijie.presenter;

import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.acer.shouyoushijie.models.IVideoModel;
import com.example.acer.shouyoushijie.models.VideoModel;
import com.example.acer.shouyoushijie.models.VideosEntity;
import com.example.acer.shouyoushijie.view.IVideoView;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by acer on 2016/4/3.
 */
public class IVideoPresenterCompl implements IVideoPresenter {

    private IVideoView iVideoView;
    private IVideoModel iVideoModel;

    private RequestQueue mQueue;
    private List<VideosEntity> videosList;

    public IVideoPresenterCompl(IVideoView iVideoView) {
        this.iVideoView = iVideoView;
    }

    @Override
    public void loadDatas(String url) {
        mQueue = Volley.newRequestQueue(iVideoView.getActivity());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("TAG", response.toString());
                        Gson gson = new Gson();
                        iVideoModel = gson.fromJson(response.toString(), VideoModel.class);
                        videosList = iVideoModel.getVideos();

                        iVideoView.showVideosList(videosList);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.getMessage(), error);
            }
        });

        mQueue.add(jsonObjectRequest);
    }
}
