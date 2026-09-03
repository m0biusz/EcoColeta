package com.example.ecocoleta.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ecocoleta.R;
import com.example.ecocoleta.TicketDetailActivity;
import com.example.ecocoleta.adapters.TicketAdapter;
import com.example.ecocoleta.models.Ocorrencia;
import com.example.ecocoleta.models.StatusHistory;
import java.util.ArrayList;
import java.util.List;

public class TicketsFragment extends Fragment implements TicketAdapter.OnTicketClickListener {

    private RecyclerView rvTickets;
    private ProgressBar progressBar;
    private View viewEmpty;
    private final Handler handler = new Handler(Looper.getMainLooper());

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tickets, container, false);

        rvTickets = view.findViewById(R.id.rv_tickets);
        progressBar = view.findViewById(R.id.progress_bar);
        viewEmpty = view.findViewById(R.id.view_empty);

        rvTickets.setLayoutManager(new LinearLayoutManager(getContext()));

        view.findViewById(R.id.fab_add_ticket).setOnClickListener(v -> 
            Toast.makeText(getContext(), "Abrindo formulário de nova ocorrência...", Toast.LENGTH_SHORT).show()
        );

        loadData();

        return view;
    }

    private void loadData() {
        progressBar.setVisibility(View.VISIBLE);
        rvTickets.setVisibility(View.GONE);
        viewEmpty.setVisibility(View.GONE);

        // Simulating API delay safely
        handler.postDelayed(() -> {
            if (isAdded()) {
                progressBar.setVisibility(View.GONE);
                loadMockTickets();
            }
        }, 1000);
    }

    private void loadMockTickets() {
        List<Ocorrencia> tickets = new ArrayList<>();

        Ocorrencia t1 = new Ocorrencia("2026-0001", getString(R.string.ticket_type_trash), "23 Ago 2026", "Rua das Palmeiras, Centro", getString(R.string.status_analyzing));
        t1.setDescricao("O lixo não foi coletado na calçada da minha residência desde segunda-feira.");
        t1.addHistorico(new StatusHistory(getString(R.string.status_analyzing), "24 Ago 2026, 09:15"));
        t1.addHistorico(new StatusHistory(getString(R.string.status_received), "23 Ago 2026, 15:30"));
        t1.addHistorico(new StatusHistory(getString(R.string.status_sent), "23 Ago 2026, 14:30"));
        tickets.add(t1);

        Ocorrencia t2 = new Ocorrencia("2026-0002", getString(R.string.ticket_type_container), "20 Ago 2026", "Av. Brasil, Vila Nova", getString(R.string.status_resolved));
        t2.setDescricao("A lixeira comunitária da esquina está com a tampa quebrada.");
        t2.addHistorico(new StatusHistory(getString(R.string.status_resolved), "22 Ago 2026, 11:00"));
        t2.addHistorico(new StatusHistory(getString(R.string.status_in_progress), "21 Ago 2026, 08:45"));
        t2.addHistorico(new StatusHistory(getString(R.string.status_sent), "20 Ago 2026, 17:10"));
        tickets.add(t2);

        if (tickets.isEmpty()) {
            viewEmpty.setVisibility(View.VISIBLE);
            rvTickets.setVisibility(View.GONE);
        } else {
            viewEmpty.setVisibility(View.GONE);
            rvTickets.setVisibility(View.VISIBLE);
            rvTickets.setAdapter(new TicketAdapter(tickets, this));
        }
    }

    @Override
    public void onTicketClick(Ocorrencia ticket) {
        Intent intent = new Intent(getActivity(), TicketDetailActivity.class);
        intent.putExtra("ticket", ticket);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        handler.removeCallbacksAndMessages(null);
    }
}
