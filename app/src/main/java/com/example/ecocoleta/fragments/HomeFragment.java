package com.example.ecocoleta.fragments;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.example.ecocoleta.R;
import com.example.ecocoleta.utils.NotificationHelper;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.materialswitch.MaterialSwitch;

public class HomeFragment extends Fragment {

    private TextView tvGreeting;
    private TextView tvCollectionDate;
    private TextView tvCollectionTime;
    private TextView tvCollectionType;
    private TextView tvCollectionRegion;
    private TextView tvNoticeText;
    private MaterialButton btnViewSchedule;
    private MaterialButton btnReport;
    private MaterialButton btnTestNotification;
    private MaterialSwitch switchReminders;
    private SharedPreferences sharedPreferences;

    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    Toast.makeText(getContext(), "Notificações ativadas", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Permissão de notificação negada", Toast.LENGTH_SHORT).show();
                    switchReminders.setChecked(false);
                }
            });

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        sharedPreferences = requireActivity().getSharedPreferences("EcoColetaPrefs", Context.MODE_PRIVATE);
        NotificationHelper.createNotificationChannel(requireContext());

        // Initialize views
        tvGreeting = view.findViewById(R.id.tv_greeting);
        tvCollectionDate = view.findViewById(R.id.tv_collection_date);
        tvCollectionTime = view.findViewById(R.id.tv_collection_time);
        tvCollectionType = view.findViewById(R.id.tv_collection_type);
        tvCollectionRegion = view.findViewById(R.id.tv_collection_region);
        tvNoticeText = view.findViewById(R.id.tv_notice_text);
        btnViewSchedule = view.findViewById(R.id.btn_view_schedule);
        btnReport = view.findViewById(R.id.btn_report);
        btnTestNotification = view.findViewById(R.id.btn_test_notification);
        switchReminders = view.findViewById(R.id.switch_reminders);

        setupMockData();
        setupListeners();

        return view;
    }

    private void setupMockData() {
        String userName = getString(R.string.mock_user_name);
        tvGreeting.setText(getString(R.string.greeting_user, userName));
        
        tvCollectionDate.setText(getString(R.string.mock_date));
        tvCollectionTime.setText(getString(R.string.mock_time));
        tvCollectionType.setText(getString(R.string.mock_type));
        tvCollectionRegion.setText(getString(R.string.mock_region));
        tvNoticeText.setText(getString(R.string.mock_notice_1));

        boolean remindersEnabled = sharedPreferences.getBoolean("reminders_enabled", true);
        switchReminders.setChecked(remindersEnabled);
    }

    private void setupListeners() {
        btnViewSchedule.setOnClickListener(v -> 
            Toast.makeText(getContext(), "Navegando para programação completa...", Toast.LENGTH_SHORT).show()
        );

        btnReport.setOnClickListener(v -> 
            Toast.makeText(getContext(), "Abrindo formulário de reporte...", Toast.LENGTH_SHORT).show()
        );

        switchReminders.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                checkNotificationPermission();
            }
            sharedPreferences.edit().putBoolean("reminders_enabled", isChecked).apply();
            String msg = isChecked ? "Lembretes ativados" : "Lembretes desativados";
            Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
        });

        btnTestNotification.setOnClickListener(v -> {
            if (sharedPreferences.getBoolean("reminders_enabled", true)) {
                NotificationHelper.showCollectionNotification(
                    requireContext(),
                    getString(R.string.mock_region),
                    getString(R.string.mock_date),
                    getString(R.string.mock_time),
                    getString(R.string.mock_type)
                );
            } else {
                Toast.makeText(getContext(), "Ative os lembretes para testar", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void checkNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
            }
        }
    }
}
