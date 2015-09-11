package com.pioneer.aaron.fitness.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.pioneer.aaron.fitness.Interface.RecyclerViewItemClickListener;
import com.pioneer.aaron.fitness.R;

import java.util.List;

/**
 * Created by Aaron on 9/5/15.
 */
public class CircleItemAdapter extends RecyclerView.Adapter<CircleItemAdapter.ItemViewHolder> {

    private List<CircleItemModel> modelList;
    private RecyclerViewItemClickListener itemClickListener;
    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
    private DisplayImageOptions options;

    public CircleItemAdapter(List<CircleItemModel> modelList) {
        this.modelList = modelList;
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(null)
                .showImageForEmptyUri(null)
                .showImageOnFail(null)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(false)
                .build();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.circle_item_layout, parent, false);
        return new ItemViewHolder(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        CircleItemModel model = modelList.get(position);
//        holder.user_thumbnail.setImageResource(model.user_thumbnail);
        ImageLoader.getInstance().displayImage("drawable://" + model.user_thumbnail, holder.user_thumbnail, options, animateFirstListener);
        holder.user_id.setText(model.user_id);
        holder.user_slogan.setText(model.user_slogan);

        if (model.tag_run) {
            setMargins(holder.tag_run);
            holder.tag_run.setText("run");
        } else {
            holder.tag_run.setBackgroundResource(R.drawable.tag_null_style);
        }
        if (model.tag_walk) {
            setMargins(holder.tag_walk);
            holder.tag_walk.setText("walk");
        } else {
            holder.tag_walk.setBackgroundResource(R.drawable.tag_null_style);
        }
        if (model.tag_swim) {
            setMargins(holder.tag_swim);
            holder.tag_swim.setText("swim");
        } else {
            holder.tag_swim.setBackgroundResource(R.drawable.tag_null_style);
        }
    }

    private void setMargins(TextView textView) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(7, 0, 7, 0);
        textView.setLayoutParams(layoutParams);
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public void setOnItemClickListener(RecyclerViewItemClickListener listener){
        this.itemClickListener = listener;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView user_thumbnail;
        private TextView user_slogan;
        private TextView tag_walk, tag_run, tag_swim;
        private TextView user_id;
        private RecyclerViewItemClickListener mItemClickListener;

        public ItemViewHolder(View itemView, RecyclerViewItemClickListener itemClickListener) {
            super(itemView);
            user_thumbnail = (ImageView) itemView.findViewById(R.id.user_thumbnail);
            user_id = (TextView) itemView.findViewById(R.id.user_id);
            user_slogan = (TextView) itemView.findViewById(R.id.user_slogan);
            tag_walk = (TextView) itemView.findViewById(R.id.user_tag_walk);
            tag_run = (TextView) itemView.findViewById(R.id.user_tag_run);
            tag_swim = (TextView) itemView.findViewById(R.id.user_tag_swim);
            this.mItemClickListener = itemClickListener;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemClick(v, getAdapterPosition());
                    }
                }
            });
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(v, getAdapterPosition());
            }
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
