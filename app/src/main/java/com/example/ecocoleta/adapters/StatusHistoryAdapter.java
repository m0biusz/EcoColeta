package com.example.ecocoleta.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ecocoleta.R;
import com.example.ecocoleta.models.StatusHistory;
import java.util.List;

public class StatusHistoryAdapter extends RecyclerView.Adapter<StatusHistoryAdapter.ViewHolder> {

    private List<StatusHistory> history;

    public StatusHistoryAdapter(List<StatusHistory> history) {
        this.history = history;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_status_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StatusHistory item = history.get(position);
        holder.tvStatus.setText(item.getStatus());
        holder.tvDate.setText(item.getDate());
    }

    @Override
    public int getItemCount() {
        return history.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvStatus, tvDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStatus = itemView.findViewById(R.id.tv_history_status);
            tvDate = itemView.findViewById(R.id.tv_history_date);
        }
    }
}
