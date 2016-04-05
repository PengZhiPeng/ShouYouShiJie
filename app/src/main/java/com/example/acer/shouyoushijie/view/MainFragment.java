package com.example.acer.shouyoushijie.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.acer.shouyoushijie.R;
import com.example.acer.shouyoushijie.adapter.RecycleViewAdapter;
import com.example.acer.shouyoushijie.models.VideosEntity;
import com.example.acer.shouyoushijie.presenter.IVideoPresenter;
import com.example.acer.shouyoushijie.presenter.IVideoPresenterCompl;
import com.example.acer.shouyoushijie.presenter.MyItemClickListener;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.List;

/**
 * 主页。
 * Created by acer on 2016/3/29.
 */
public class MainFragment extends Fragment
        implements PullLoadMoreRecyclerView.PullLoadMoreListener, IVideoView {
    private View rootView;//复用rootview，使viewpager滑动时此页面不销毁。
    private CoordinatorLayout container;
    private PullLoadMoreRecyclerView mPullLoadMoreRecyclerView;
    private RecycleViewAdapter adapter;
    private View headerView;
    private IVideoPresenter iVideoPresenter;
    private int mPage = 1;
    private final String url = "https://openapi.youku.com/v2/videos/show_basic_batch.json" +
            "?client_id=1b590edfa7c4a505" +
            "&video_ids=XMTUxNjM2NTM0MA,XMTUxMzY0ODM4NA,XMTUwMTkzNzI5Mg," +
            "XMTUxMDU0NDg3Ng,XMTUxNjE1MTM1Mg,XMTUxNjY5OTA2NA,XMTUwMjIwNzEwNA," +
            "XMTUxMjczNzk2NA";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (null == rootView) {
            rootView = inflater.inflate(R.layout.fragment_main, container, false);
            initView();
            initDatas();
            iVideoPresenter = new IVideoPresenterCompl(this);
            iVideoPresenter.loadDatas(url);
        }
        return rootView;
    }

    private void initDatas() {
        adapter = new RecycleViewAdapter(this);
        adapter.setHeaderView(headerView);
        mPullLoadMoreRecyclerView.setAdapter(adapter);
    }

    private void initView() {
        container = (CoordinatorLayout) rootView.findViewById(R.id.id_container);
        mPullLoadMoreRecyclerView = (PullLoadMoreRecyclerView) rootView.findViewById(R.id.id_show_single_weibo_recycleview);
        //设置网格布局（也可线性、瀑布）
        mPullLoadMoreRecyclerView.setGridLayout(2);
        mPullLoadMoreRecyclerView.setOnPullLoadMoreListener(this);
        mPullLoadMoreRecyclerView.setRefreshing(true);

        headerView = LayoutInflater.from(getActivity()).
                inflate(R.layout.banner, mPullLoadMoreRecyclerView, false);
    }

    @Override
    public void showVideosList(List<VideosEntity> videosList) {
        initDatas(videosList);
        mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();//关闭加载显示圈
    }

    private void initDatas(List<VideosEntity> videosList) {
        if (mPage == 1) {
            adapter.clearAndSetDatas(videosList);
        }else if (mPage==2) {
            adapter.addDatas(videosList);
        } else {
            Snackbar.make(container, "已无更多数据", Snackbar.LENGTH_LONG).show();
        }

        adapter.setOnItemClickListener(new MyItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                VideosEntity videos = adapter.getmDatas().get(postion);
                if (videos != null) {
                    Intent intent = new Intent(getActivity(), VideoPlay.class);
                    intent.putExtra("id", videos.getId());
                    intent.putExtra("link", videos.getLink());
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onRefresh() {
        //下拉刷新
        mPage = 1;
        iVideoPresenter.loadDatas(url);//重新加载一次模拟刷新
    }

    @Override
    public void onLoadMore() {
        //上拉加载更多 XMTUwMDk0MTI0OA,XMTUxMDkwOTg1Mg,XMTUxMDk0ODA1Ng,XMTUwMzE5Njk3Ng
        final String url = "https://openapi.youku.com/v2/videos/show_basic_batch.json?client_id=1b590edfa7c4a505&video_ids=XMTUwMDk0MTI0OA,XMTUxMDkwOTg1Mg,XMTUxMDk0ODA1Ng,XMTUwMzE5Njk3Ng";
        mPage += 1;
        iVideoPresenter.loadDatas(url);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (null != rootView)
            ((ViewGroup) rootView.getParent()).removeView(rootView);
    }

}
