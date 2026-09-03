package com.example.ecocoleta.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ecocoleta.R;
import com.example.ecocoleta.adapters.CollectionAdapter;
import com.example.ecocoleta.models.CollectionItem;
import java.util.ArrayList;
import java.util.List;

public class CollectionsFragment extends Fragment {

    private RecyclerView rvCollections;
    private ProgressBar progressBar;
    private View viewEmpty;
    private final Handler handler = new Handler(Looper.getMainLooper());

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_collections, container, false);

        rvCollections = view.findViewById(R.id.rv_collections);
        progressBar = view.findViewById(R.id.progress_bar);
        viewEmpty = view.findViewById(R.id.view_empty);
        
        rvCollections.setLayoutManager(new LinearLayoutManager(getContext()));

        loadData();

        return view;
    }

    private void loadData() {
        progressBar.setVisibility(View.VISIBLE);
        rvCollections.setVisibility(View.GONE);
        viewEmpty.setVisibility(View.GONE);

        // Simulating API delay safely
        handler.postDelayed(() -> {
            if (isAdded()) {
                progressBar.setVisibility(View.GONE);
                setupMockData();
            }
        }, 1000);
    }

    private void setupMockData() {
        List<CollectionItem> items = new ArrayList<>();
        
        items.add(new CollectionItem("Amanhã, 25 Ago", "08:00 - 10:00", getString(R.string.type_recyclable), "Setor Central", true));
        items.add(new CollectionItem("Quarta, 26 Ago", "07:30 - 09:30", getString(R.string.type_organic), "Setor Central", false));
        items.add(new CollectionItem("Sexta, 28 Ago", "08:00 - 10:00", getString(R.string.type_recyclable), "Setor Central", false));
        items.add(new CollectionItem("Sábado, 29 Ago", "09:00 - 12:00", getString(R.string.type_special), "Setor Central", false));
        items.add(new CollectionItem("Segunda, 31 Ago", "07:30 - 09:30", getString(R.string.type_organic), "Setor Central", false));
        items.add(new CollectionItem("Terça, 01 Set", "08:00 - 10:00", getString(R.string.type_recyclable), "Setor Central", false));

        if (items.isEmpty()) {
            viewEmpty.setVisibility(View.VISIBLE);
            rvCollections.setVisibility(View.GONE);
        } else {
            viewEmpty.setVisibility(View.GONE);
            rvCollections.setVisibility(View.VISIBLE);
            rvCollections.setAdapter(new CollectionAdapter(items));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        handler.removeCallbacksAndMessages(null);
    }
}
