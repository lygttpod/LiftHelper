package com.allen.lifthelper.wechatnews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.allen.lifthelper.R;
import com.allen.lifthelper.history.bean.HistoryBean;
import com.allen.lifthelper.wechatnews.bean.WechatNewsBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Allen on 2016/1/15.
 */
public class WechatNewsListAdapter extends RecyclerView.Adapter<WechatNewsListAdapter.WechatNewsViewHolder> {

    private List<WechatNewsBean.ResultEntity.ListEntity> listEntities;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;


    public interface OnItemClickListener {
        void onItemClick(View view, int position);

    }

    public void setmOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public WechatNewsListAdapter(List<WechatNewsBean.ResultEntity.ListEntity> listEntities, Context context) {
        this.listEntities = listEntities;
        this.mContext = context;
    }

    @Override
    public WechatNewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ac_wechatnews_list_item, null);
        WechatNewsViewHolder HistoryListViewHolder = new WechatNewsViewHolder(view);
        return HistoryListViewHolder;
    }

    @Override
    public void onBindViewHolder(final WechatNewsViewHolder holder, final int position) {
        String title = listEntities.get(position).getTitle();
        String pic_url = listEntities.get(position).getFirstImg();
        String source = listEntities.get(position).getSource();
        holder.title.setText(title);
        holder.source.setText(source);
        if (!pic_url.equals("")) {
            Picasso.with(mContext).load(pic_url)
                    .error(R.mipmap.define_pic)
                    .placeholder(R.mipmap.define_pic)
                    .into(holder.pic);
        }
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(holder.itemView, position);
                }
            });

        }


    }


    @Override
    public int getItemCount() {
        return listEntities.size();
    }

    public class WechatNewsViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView source;

        public ImageView pic;


        public WechatNewsViewHolder(View itemView) {
            super(itemView);
            pic = (ImageView) itemView.findViewById(R.id.wechatnews_pic);

            title = (TextView) itemView.findViewById(R.id.wechatnews_title);
            source = (TextView) itemView.findViewById(R.id.wechatnews_source);


        }
    }
}
