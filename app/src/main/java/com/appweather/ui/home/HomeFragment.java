package com.appweather.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.appweather.BuildConfig;

import com.appweather.api.RetrofitClient;
import com.appweather.api.WeatherService;
import com.appweather.databinding.FragmentHomeBinding;
import com.appweather.model.GeoIpResponse;
import com.appweather.model.WeatherResponse;
import com.appweather.ui.map.MapFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    public static double globalLat = 0.0;
    public static double globalLon = 0.0;
    public static String globalWoeid = "421585";

    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        loadWeather();
        loadCoordinates();

        return binding.getRoot();
    }

    public void forceReload() {
        loadWeather();
        loadCoordinates();
    }

    private void loadWeather() {

        binding.progressBar.setVisibility(View.VISIBLE);
        binding.recyclerView.setVisibility(View.GONE);
        binding.txtCity.setText("Carregando...");

        WeatherService service = RetrofitClient.getRetrofitInstance().create(WeatherService.class);

        service.getWeather(globalWoeid,BuildConfig.HG_API_KEY).enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {

                if (!response.isSuccessful() || response.body() == null) {
                    binding.txtCity.setText("Erro ao buscar dados");
                    return;
                }

                WeatherResponse.Results result = response.body().results;
                binding.txtCity.setText(result.city_name);
                binding.recyclerView.setAdapter(new ForecastAdapter(result.forecast));

                binding.progressBar.setVisibility(View.GONE);
                binding.recyclerView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
                binding.txtCity.setText("Erro ao carregar dados");
            }
        });
    }

    private void loadCoordinates() {

        WeatherService service = RetrofitClient.getRetrofitInstance().create(WeatherService.class);

        service.getLocationByIP(BuildConfig.HG_API_KEY_IP, "remote", false)
                .enqueue(new Callback<GeoIpResponse>() {
                    @Override
                    public void onResponse(Call<GeoIpResponse> call, Response<GeoIpResponse> response) {

                        if (!response.isSuccessful() || response.body() == null || response.body().results == null)
                            return;

                        GeoIpResponse.Results r = response.body().results;

                        globalLat = r.latitude;
                        globalLon = r.longitude;
                        globalWoeid = String.valueOf(r.woeid);

                        MapFragment.refreshMap();
                    }

                    @Override
                    public void onFailure(Call<GeoIpResponse> call, Throwable t) { }
                });
    }
}
