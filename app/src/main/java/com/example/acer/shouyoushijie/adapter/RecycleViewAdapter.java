package com.example.acer.shouyoushijie.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.acer.shouyoushijie.R;
import com.example.acer.shouyoushijie.models.VideosEntity;
import com.example.acer.shouyoushijie.presenter.MyItemClickListener;
import com.example.acer.shouyoushijie.view.IVideoView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页为一整个recycleview，header加载显示网络广告页轮播，item显示优酷视屏列表。
 * Created by acer on 2016/3/30.
 */
public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.RecycleViewHolder> {

    private static final int TYPE_HEADER = 0, TYPE_ITEM = 1;
    private final int HeaderViewSize = 1;
    private View mHeaderView;
    //广告页url地址
    private String[] images = {"http://img2.imgtn.bdimg.com/it/u=3093785514,1341050958&fm=21&gp=0.jpg",
            "http://img2.3lian.com/2014/f2/37/d/40.jpg",
            "http://d.3987.com/sqmy_131219/001.jpg",
            "http://img2.3lian.com/2014/f2/37/d/39.jpg"};

    private MyItemClickListener mItemClickListener;

    private IVideoView iVideoView;
    private List<VideosEntity> videosList;

    public RecycleViewAdapter(IVideoView iVideoView) {
        this.iVideoView = iVideoView;
        this.videosList = new ArrayList<>();
    }

    public void clearAndSetDatas(List<VideosEntity> datas) {
        if (datas != null && datas.size() > 0) {
            this.videosList.clear();
            addDatas(datas);
        }
    }

    public void addDatas(List<VideosEntity> datas) {
        if (datas != null && datas.size() > 0) {
            this.videosList.addAll(datas);
            notifyDataSetChanged();
        }
    }

    public void setHeaderView(View headerView) {
        this.mHeaderView = headerView;
        notifyItemInserted(0);
    }

    public List<VideosEntity> getmDatas() {
        return videosList;
    }

    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null) return TYPE_ITEM;
        if (position == 0) return TYPE_HEADER;
        return TYPE_ITEM;
    }

    //由于有header视图，对positon进行调整
    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return mHeaderView == null ? position : position - 1;
    }

    @Override
    public RecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType != TYPE_HEADER) { //position不是头布局（广告）就显示item布局（视频列表）
            return new RecycleViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.videos_item, parent, false));
        }
        return new RecycleViewHolder(mHeaderView);
    }

    public void setOnItemClickListener(MyItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    @Override
    public void onBindViewHolder(final RecycleViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_HEADER) {//header
            for (String image : images) {
                holder.flipper.addView(getImageView(image));
            }
            holder.flipper.setInAnimation(iVideoView.getActivity(), R.anim.right_in);
            holder.flipper.setOutAnimation(iVideoView.getActivity(), R.anim.right_out);
            holder.flipper.setFlipInterval(3000);
            holder.flipper.startFlipping();

        } else {//item
            int pos = getRealPosition(holder);//有headerview，故position实际上要减1

            ImageLoader.getInstance().displayImage(videosList.get(pos).getThumbnail(),
                    holder.pic);

            holder.title.setText(videosList.get(pos).getTitle());
        }

        // 如果设置了回调，则设置点击事件
        if (mItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getAdapterPosition() - 1;
                    mItemClickListener.onItemClick(holder.itemView, pos);
                }
            });
        }
    }

    private ImageView getImageView(String url) {
        ImageView image = new ImageView(iVideoView.getActivity());
        image.setScaleType(ImageView.ScaleType.FIT_XY);
        ImageLoader.getInstance().displayImage(url, image);
        return image;
    }

    //让头视图占两格
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return getItemViewType(position) == TYPE_HEADER
                            ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return HeaderViewSize + videosList.size();
    }

    public class RecycleViewHolder extends RecyclerView.ViewHolder {

        ImageView pic;
        TextView title;
        ViewFlipper flipper;

        public RecycleViewHolder(View itemView) {
            super(itemView);
            if (itemView == mHeaderView) {
                flipper = (ViewFlipper) itemView.findViewById(R.id.flipper);
            } else {
                pic = (ImageView) itemView.findViewById(R.id.id_thumbnail);
                title = (TextView) itemView.findViewById(R.id.id_title);
            }
        }
    }
}
