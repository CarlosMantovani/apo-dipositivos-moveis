package com.appweather.api;

import com.appweather.model.GeoIpResponse;
import com.appweather.model.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {

    @GET("weather")
    Call<WeatherResponse> getWeather(
            @Query("woeid") String woeid,
            @Query("key") String key
    );

    @GET("geoip")
    Call<GeoIpResponse> getLocationByIP(
            @Query("key") String key,
            @Query("address") String address,
            @Query("precision") boolean precision
    );
}
