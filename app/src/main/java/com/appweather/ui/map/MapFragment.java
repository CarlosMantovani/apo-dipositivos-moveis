package com.appweather.ui.map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.appweather.databinding.FragmentMapBinding;
import com.appweather.ui.home.HomeFragment;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;

import com.journeyapps.barcodescanner.*;
import com.appweather.R;

public class MapFragment extends Fragment {

    private FragmentMapBinding binding;
    private static GoogleMap googleMapInstance;
    private static SupportMapFragment mapFragmentInstance;

    private final androidx.activity.result.ActivityResultLauncher<ScanOptions> barcodeLauncher =
            registerForActivityResult(new ScanContract(), result -> {
                if (result.getContents() != null) {

                    HomeFragment.globalWoeid = result.getContents();

                    HomeFragment homeFragment =
                            (HomeFragment) requireActivity().getSupportFragmentManager()
                                    .findFragmentByTag("f0");

                    if (homeFragment != null)
                        homeFragment.forceReload();
                }
            });

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMapBinding.inflate(inflater, container, false);

        binding.fabScan.setOnClickListener(v -> {
            ScanOptions opt = new ScanOptions();
            opt.setPrompt("Escaneie o QR Code");
            opt.setBeepEnabled(true);
            barcodeLauncher.launch(opt);
        });

        initMap();

        return binding.getRoot();
    }

    private void initMap() {
        mapFragmentInstance = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

        if (mapFragmentInstance != null) {
            mapFragmentInstance.getMapAsync(googleMap -> {
                googleMapInstance = googleMap;
                refreshMap();
            });
        }
    }

    public static void refreshMap() {
        if (googleMapInstance == null)
            return;

        LatLng coordenadas = new LatLng(HomeFragment.globalLat, HomeFragment.globalLon);

        googleMapInstance.clear();
        googleMapInstance.addMarker(new MarkerOptions().position(coordenadas).title("Cidade"));
        googleMapInstance.animateCamera(CameraUpdateFactory.newLatLngZoom(coordenadas, 12f));
    }
}
