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
 * Created by Aaron on 9/6/15.
 */
public class PlaygroundItemAdapter extends RecyclerView.Adapter<PlaygroundItemAdapter.ItemViewHolder>{
    private List<PlaygroundItemModel> dataSet;
    private RecyclerViewItemClickListener itemClickListener;
    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
    private DisplayImageOptions options;

    public PlaygroundItemAdapter(List<PlaygroundItemModel> dataSet) {
        this.dataSet = dataSet;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.playground_item_layout, parent, false);
        return new ItemViewHolder(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        PlaygroundItemModel data = dataSet.get(position);
//        holder.playground_thumbnail.setImageResource(data.playground_thumbnail);
        ImageLoader.getInstance().displayImage("drawable://" + data.playground_thumbnail, holder.playground_thumbnail, options, animateFirstListener);
        holder.playground_id.setText(data.playground_id);
        holder.playground_location.setText(data.playground_location);
        for (int i = 0; i < data.tag_Booleans.length; ++i) {
            boolean tag = data.tag_Booleans[i];
            if (tag) {
                switch (i) {
                    case 0:
                        holder.tag_walk.setBackgroundResource(R.drawable.tag_walk_style);
                        holder.tag_walk.setText("walk");
                        setMargins(holder.tag_walk);
                        break;
                    case 1:
                        holder.tag_run.setBackgroundResource(R.drawable.tag_run_style);
                        holder.tag_run.setText("run");
                        setMargins(holder.tag_run);
                        break;
                    case 2:
                        holder.tag_swim.setBackgroundResource(R.drawable.tag_swim_style);
                        holder.tag_swim.setText("swim");
                        setMargins(holder.tag_swim);
                        break;
                    case 3:
                        holder.tag_exercise.setBackgroundResource(R.drawable.tag_exercise_style);
                        holder.tag_exercise.setText("exercise");
                        setMargins(holder.tag_exercise);
                        break;
                    case 4:
                        holder.tag_yoga.setBackgroundResource(R.drawable.tag_yoga_style);
                        holder.tag_yoga.setText("yoga");
                        setMargins(holder.tag_yoga);
                        break;
                    case 5:
                        holder.tag_bike.setBackgroundResource(R.drawable.tag_bike_style);
                        holder.tag_bike.setText("bike");
                        setMargins(holder.tag_bike);
                        break;
                    case 6:
                        holder.tag_badminton.setBackgroundResource(R.drawable.tag_badminton_style);
                        holder.tag_badminton.setText("badminton");
                        setMargins(holder.tag_badminton);
                }
            }
        }
    }

    private void setMargins(TextView textView) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(7, 0, 7, 0);
        textView.setLayoutParams(layoutParams);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public void setOnItemCLickListener(RecyclerViewItemClickListener listener) {
        this.itemClickListener = listener;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView playground_thumbnail;
        private TextView playground_id;
        private TextView playground_location;
        private TextView tag_walk, tag_run, tag_swim, tag_exercise, tag_yoga, tag_bike, tag_badminton;
        private RecyclerViewItemClickListener itemClickListener;

        public ItemViewHolder(View itemView, final RecyclerViewItemClickListener itemClickListener) {
            super(itemView);
            playground_thumbnail = (ImageView) itemView.findViewById(R.id.playground_thumnail);
            playground_id = (TextView) itemView.findViewById(R.id.playground_id);
            playground_location = (TextView) itemView.findViewById(R.id.playground_location);
            tag_walk = (TextView) itemView.findViewById(R.id.playground_tag_walk);
            tag_run = (TextView) itemView.findViewById(R.id.playground_tag_run);
            tag_swim = (TextView) itemView.findViewById(R.id.playground_tag_swim);
            tag_exercise = (TextView) itemView.findViewById(R.id.playground_tag_exercise);
            tag_yoga = (TextView) itemView.findViewById(R.id.playground_tag_yoga);
            tag_bike = (TextView) itemView.findViewById(R.id.playground_tag_bike);
            tag_badminton = (TextView) itemView.findViewById(R.id.playground_tag_badminton);
            this.itemClickListener = itemClickListener;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null) {
                        itemClickListener.onItemClick(v, getAdapterPosition());
                    }
                }
            });
        }

        @Override
        public void onClick(View v) {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(v, getAdapterPosition());
            }
        }
    }

}
