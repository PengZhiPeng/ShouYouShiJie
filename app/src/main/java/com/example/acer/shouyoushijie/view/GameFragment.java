package com.example.acer.shouyoushijie.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.acer.shouyoushijie.R;

/**
 * 找游戏页面
 * Created by acer on 2016/3/29.
 */
public class GameFragment extends Fragment {
    private View rootView;//复用rootview，使viewpager滑动时此页面不销毁。

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (null == rootView) {
            rootView = inflater.inflate(R.layout.fragment_game, container, false);

        }
        return rootView;
    }
}
