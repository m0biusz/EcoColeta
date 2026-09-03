package com.example.ecocoleta.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ecocoleta.R;
import com.example.ecocoleta.models.CollectionItem;
import java.util.List;

public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.ViewHolder> {

    private List<CollectionItem> items;

    public CollectionAdapter(List<CollectionItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_collection, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CollectionItem item = items.get(position);
        holder.tvDay.setText(item.getDay());
        holder.tvTime.setText(item.getTime());
        holder.tvType.setText(item.getType());
        holder.tvRegion.setText(item.getRegion());
        holder.tvTagNext.setVisibility(item.isNext() ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDay, tvTime, tvType, tvRegion, tvTagNext;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDay = itemView.findViewById(R.id.tv_item_day);
            tvTime = itemView.findViewById(R.id.tv_item_time);
            tvType = itemView.findViewById(R.id.tv_item_type);
            tvRegion = itemView.findViewById(R.id.tv_item_region);
            tvTagNext = itemView.findViewById(R.id.tv_tag_next);
        }
    }
}
