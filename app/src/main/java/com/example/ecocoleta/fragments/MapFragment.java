package com.example.ecocoleta.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.ecocoleta.R;
import com.example.ecocoleta.models.PontoDescarte;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private FusedLocationProviderClient fusedLocationClient;
    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isAdded() && isGranted) {
                    updateLocationUI();
                    getDeviceLocation();
                }
            });

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity());

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        FloatingActionButton fabMyLocation = view.findViewById(R.id.fab_my_location);
        fabMyLocation.setOnClickListener(v -> getDeviceLocation());

        return view;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        updateLocationUI();
        loadMockPoints();

        // Initial camera position: focus on markers or user
        LatLng initialCenter = new LatLng(-27.0010, -51.1520);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(initialCenter, 14f));
        
        getDeviceLocation(); // Try to move to user location if available

        mMap.setOnMarkerClickListener(marker -> {
            PontoDescarte ponto = (PontoDescarte) marker.getTag();
            if (ponto != null) {
                String info = ponto.getNome() + "\n" +
                             ponto.getEndereco() + "\n" +
                             "Tipos: " + ponto.getTiposAceitos();
                Toast.makeText(getContext(), info, Toast.LENGTH_LONG).show();
            }
            return false;
        });
    }

    private void updateLocationUI() {
        if (mMap == null) return;
        try {
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
            } else {
                requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION);
            }
        } catch (SecurityException e) {
            Toast.makeText(getContext(), "Erro ao acessar localização", Toast.LENGTH_SHORT).show();
        }
    }

    private void getDeviceLocation() {
        try {
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                fusedLocationClient.getLastLocation().addOnSuccessListener(location -> {
                    if (location != null && mMap != null) {
                        LatLng currentLatLng = new LatLng(location.getLatitude(), location.getLongitude());
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15f));
                    }
                });
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    private void loadMockPoints() {
        List<PontoDescarte> pontos = new ArrayList<>();
        
        PontoDescarte p1 = new PontoDescarte();
        p1.setNome("Ecoponto Central");
        p1.setEndereco("Rua XV de Novembro, 100");
        p1.setLatitude(-27.0010);
        p1.setLongitude(-51.1520);
        p1.setTiposAceitos("Eletrônicos, Móveis, Pneus");
        pontos.add(p1);

        PontoDescarte p2 = new PontoDescarte();
        p2.setNome("Recicla Verde");
        p2.setEndereco("Rua Brasil, 50");
        p2.setLatitude(-26.9980);
        p2.setLongitude(-51.1480);
        p2.setTiposAceitos("Papel, Plástico, Vidro");
        pontos.add(p2);

        for (PontoDescarte p : pontos) {
            LatLng latLng = new LatLng(p.getLatitude(), p.getLongitude());
            float hue = p.getNome().contains("Ecoponto") ? BitmapDescriptorFactory.HUE_GREEN : BitmapDescriptorFactory.HUE_AZURE;
            
            Marker marker = mMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .title(p.getNome())
                    .icon(BitmapDescriptorFactory.defaultMarker(hue)));
            if (marker != null) {
                marker.setTag(p);
            }
        }
    }
}
