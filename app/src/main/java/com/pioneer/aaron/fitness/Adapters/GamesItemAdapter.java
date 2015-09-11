package com.pioneer.aaron.fitness.Adapters;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.pioneer.aaron.fitness.Interface.RecyclerViewItemClickListener;
import com.pioneer.aaron.fitness.R;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Aaron on 9/6/15.
 */
public class GamesItemAdapter extends RecyclerView.Adapter<GamesItemAdapter.ItemViewHolder>{
    private List<GamesItemModel> modelList;
    private RecyclerViewItemClickListener mItemClickListener;
    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

    private DisplayImageOptions options;

    public GamesItemAdapter(List<GamesItemModel> modelList) {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.game_item_layout, parent, false);
        return new ItemViewHolder(view, mItemClickListener);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        GamesItemModel model = modelList.get(position);
//        holder.game_thumbnail.setImageResource(model.game_thumbnail);

        ImageLoader.getInstance().displayImage("drawable://" + model.game_thumbnail, holder.game_thumbnail, options, animateFirstListener);

        holder.game_name.setText(model.game_name);
        holder.game_participant.setText("Participants: " + model.game_participant);
        holder.game_countdown.setText("Due: " + model.game_countdown);

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public void setOnItemClickListener(RecyclerViewItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;

    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView game_thumbnail;
        private TextView game_name;
        private TextView game_participant;
        private TextView game_countdown;
        private RecyclerViewItemClickListener mItemClickListener;

        public ItemViewHolder(View itemView, RecyclerViewItemClickListener itemClickListener) {
            super(itemView);
            game_thumbnail = (ImageView) itemView.findViewById(R.id.game_thumbnail);
            game_name = (TextView) itemView.findViewById(R.id.game_name);
            game_participant = (TextView) itemView.findViewById(R.id.game_participant);
            game_countdown = (TextView) itemView.findViewById(R.id.game_countdown);
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
