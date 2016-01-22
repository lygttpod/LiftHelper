package com.allen.lifthelper.history.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.allen.lifthelper.R;
import com.allen.lifthelper.history.bean.HistoryBean;
import com.allen.lifthelper.robot.bean.MsgBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Allen on 2016/1/15.
 */
public class HistoryListAdapter extends RecyclerView.Adapter<HistoryListAdapter.HistoryListViewHolder> {

    private List<HistoryBean.ResultEntity> resultEntities;
    private Context mContext;

    public HistoryListAdapter(List<HistoryBean.ResultEntity> resultEntities, Context context) {
        this.resultEntities = resultEntities;
        this.mContext = context;
    }

    @Override
    public HistoryListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ac_history_list_item, null);
        HistoryListViewHolder HistoryListViewHolder = new HistoryListViewHolder(view);
        return HistoryListViewHolder;
    }

    @Override
    public void onBindViewHolder(HistoryListViewHolder holder, int position) {
        String title = resultEntities.get(position).getTitle();
        String des = resultEntities.get(position).getDes();
        String time = resultEntities.get(position).getYear() + "年" + resultEntities.get(position).getMonth() + "月" + resultEntities.get(position).getDay();
        String pic_url = resultEntities.get(position).getPic();

        holder.title.setText(title);
        holder.des.setText(des);
        holder.time.setText(time);
        if (!pic_url.equals("")) {
            Picasso.with(mContext).load(pic_url)
                    .error(R.mipmap.define_pic)
                    .placeholder(R.mipmap.define_pic)
                    .into(holder.pic);
        }


    }


    @Override
    public int getItemCount() {
        return resultEntities.size();
    }

    public class HistoryListViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView des;
        public TextView time;

        public ImageView pic;


        public HistoryListViewHolder(View itemView) {
            super(itemView);
            pic = (ImageView) itemView.findViewById(R.id.history_pic);

            title = (TextView) itemView.findViewById(R.id.history_title);
            des = (TextView) itemView.findViewById(R.id.history_des);
            time = (TextView) itemView.findViewById(R.id.history_time);


        }
    }
}
