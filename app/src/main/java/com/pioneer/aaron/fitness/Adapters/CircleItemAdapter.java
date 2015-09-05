package com.pioneer.aaron.fitness.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pioneer.aaron.fitness.Interface.RecyclerViewItemClickListener;
import com.pioneer.aaron.fitness.R;

import java.util.List;

/**
 * Created by Aaron on 9/5/15.
 */
public class CircleItemAdapter extends RecyclerView.Adapter<CircleItemAdapter.ItemViewHolder> {

    private List<CircleItemModel> modelList;
    private RecyclerViewItemClickListener itemClickListener;

    public CircleItemAdapter(List<CircleItemModel> modelList) {
        this.modelList = modelList;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.circle_item_layout, parent, false);
        return new ItemViewHolder(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        CircleItemModel model = modelList.get(position);
        holder.user_thumbnail.setImageResource(model.user_thumbnail);
        holder.user_id.setText(model.user_id);
        if (model.tag_run) {
            holder.tag_run.setText("Run");
        } else {
            holder.tag_run.setBackgroundResource(R.drawable.tag_null_style);
        }
        if (model.tag_walk) {
            holder.tag_walk.setText("Walk");
        } else {
            holder.tag_walk.setBackgroundResource(R.drawable.tag_null_style);
        }
        if (model.tag_swim) {
            holder.tag_swim.setText("Swim");
        } else {
            holder.tag_swim.setBackgroundResource(R.drawable.tag_null_style);
        }
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
        private TextView tag_walk, tag_run, tag_swim;
        private TextView user_id;
        private RecyclerViewItemClickListener mItemClickListener;

        public ItemViewHolder(View itemView, RecyclerViewItemClickListener itemClickListener) {
            super(itemView);
            user_thumbnail = (ImageView) itemView.findViewById(R.id.user_thumbnail);
            user_id = (TextView) itemView.findViewById(R.id.user_id);
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
