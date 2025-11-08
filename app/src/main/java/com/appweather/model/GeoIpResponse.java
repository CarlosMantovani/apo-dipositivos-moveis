package com.appweather.model;

import com.google.gson.annotations.SerializedName;

public class GeoIpResponse {

    @SerializedName("results")
    public Results results;

    public static class Results {

        @SerializedName("latitude")
        public double latitude;

        @SerializedName("longitude")
        public double longitude;

        @SerializedName("woeid")
        public int woeid;
    }
}
