package com.example.ecocoleta;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ecocoleta.adapters.StatusHistoryAdapter;
import com.example.ecocoleta.models.Ocorrencia;

public class TicketDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        Ocorrencia ticket;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ticket = getIntent().getSerializableExtra("ticket", Ocorrencia.class);
        } else {
            ticket = (Ocorrencia) getIntent().getSerializableExtra("ticket");
        }
        
        if (ticket != null) {
            bindData(ticket);
        }
    }

    private void bindData(Ocorrencia ticket) {
        TextView tvProtocol = findViewById(R.id.tv_detail_protocol);
        TextView tvStatus = findViewById(R.id.tv_detail_status);
        TextView tvType = findViewById(R.id.tv_detail_type);
        TextView tvDate = findViewById(R.id.tv_detail_date);
        TextView tvDescription = findViewById(R.id.tv_detail_description);
        TextView tvLocation = findViewById(R.id.tv_detail_location);
        RecyclerView rvHistory = findViewById(R.id.rv_status_history);

        tvProtocol.setText(getString(R.string.label_protocol) + ticket.getProtocolo());
        tvStatus.setText(ticket.getStatus());
        tvType.setText(ticket.getTipo());
        tvDate.setText(ticket.getData());
        tvDescription.setText(ticket.getDescricao());
        tvLocation.setText(ticket.getLocalizacao());

        rvHistory.setLayoutManager(new LinearLayoutManager(this));
        rvHistory.setAdapter(new StatusHistoryAdapter(ticket.getHistorico()));
    }
}
