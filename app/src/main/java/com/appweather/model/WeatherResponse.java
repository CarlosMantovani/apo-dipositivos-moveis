package com.appweather.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class WeatherResponse {

    @SerializedName("results")
    public Results results;

    public static class Results {
        @SerializedName("city_name") public String city_name;
        @SerializedName("woeid") public int woeid;
        @SerializedName("forecast") public List<Forecast> forecast;
        @SerializedName("lat") public double lat;
        @SerializedName("lon") public double lon;
    }

    public static class Forecast {
        @SerializedName("date") public String date;
        @SerializedName("weekday") public String weekday;
        @SerializedName("max") public int max;
        @SerializedName("min") public int min;
        @SerializedName("description") public String description;
    }
}
