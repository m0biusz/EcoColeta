package com.example.ecocoleta.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ecocoleta.R;
import com.example.ecocoleta.models.Ocorrencia;
import java.util.List;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.ViewHolder> {

    public interface OnTicketClickListener {
        void onTicketClick(Ocorrencia ticket);
    }

    private List<Ocorrencia> tickets;
    private OnTicketClickListener listener;

    public TicketAdapter(List<Ocorrencia> tickets, OnTicketClickListener listener) {
        this.tickets = tickets;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ticket, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ocorrencia ticket = tickets.get(position);
        holder.tvProtocol.setText("Protocolo: " + ticket.getProtocolo());
        holder.tvType.setText(ticket.getTipo());
        holder.tvDate.setText(ticket.getData());
        holder.tvLocation.setText(ticket.getLocalizacao());
        holder.tvStatus.setText(ticket.getStatus());
        
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) listener.onTicketClick(ticket);
        });
    }

    @Override
    public int getItemCount() {
        return tickets.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvProtocol, tvStatus, tvType, tvDate, tvLocation;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvProtocol = itemView.findViewById(R.id.tv_ticket_protocol);
            tvStatus = itemView.findViewById(R.id.tv_ticket_status);
            tvType = itemView.findViewById(R.id.tv_ticket_type);
            tvDate = itemView.findViewById(R.id.tv_ticket_date);
            tvLocation = itemView.findViewById(R.id.tv_ticket_location);
        }
    }
}
