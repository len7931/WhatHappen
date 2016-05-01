package com.example.wxl.myapplication.adapter;

import android.support.annotation.BinderThread;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wxl.myapplication.R;
import com.example.wxl.myapplication.bean.ChannelItem;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wxl on 4/30/16.
 */
public class NewsBasicFragmentAdapter extends RecyclerView.Adapter{
    final int FOOTERVIEW = 0;
    final int ITEMVIEW = 1;
    private List<ChannelItem> mData;
    private View mFooterView;
    private View.OnClickListener mItemOnClickListner;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEMVIEW) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_news_basic, parent, false);
            if (mItemOnClickListner != null)
                view.setOnClickListener(mItemOnClickListner);

            return new Holder(view);
        } else {

            return new FooterHoler(mFooterView);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mData.size())
            return FOOTERVIEW;
        return ITEMVIEW;
    }

    public void setOnItemClickListener(View.OnClickListener listener) {
        mItemOnClickListner = listener;
    }

    public void setFooterView(View view) {
        mFooterView = view;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (!(holder instanceof Holder))
            return;
        Holder viewHolder = (Holder) holder;
        ChannelItem item = mData.get(position);
        viewHolder.itemView.setTag(item.url);
        viewHolder.title.setText(item.title);
        viewHolder.src.setText(item.description);
        viewHolder.date.setText(item.ctime);
        if (item.picUrl != "")
            Glide.with(viewHolder.cover.getContext()).load(item.picUrl).fitCenter()
            .crossFade().into(viewHolder.cover);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size() + getFooterViewCount();
    }

    public void setData(List<ChannelItem> data) {
        mData = data;
        notifyDataSetChanged();

    }

    public List<ChannelItem> getData() {
        return mData;
    }

    public int getFooterViewCount() {
        return mFooterView == null ? 0 : 1;
    }

    public class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.holder_cover)
        ImageView cover;
        @BindView(R.id.holder_title)
        TextView title;
        @BindView(R.id.holder_date)
        TextView date;
        @BindView(R.id.holder_src)
        TextView src;


        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class FooterHoler extends  RecyclerView.ViewHolder {
        public FooterHoler(View itemView) {
            super(itemView);
        }
    }


}
