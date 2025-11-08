package com.appweather.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.appweather.R;
import com.appweather.model.WeatherResponse;
import java.util.List;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ViewHolder> {

    private final List<WeatherResponse.Forecast> forecastList;

    public ForecastAdapter(List<WeatherResponse.Forecast> forecastList) {
        this.forecastList = forecastList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_forecast, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WeatherResponse.Forecast forecast = forecastList.get(position);

        holder.txtDate.setText(forecast.weekday + " - " + forecast.date);
        holder.txtDesc.setText(forecast.description);
        holder.txtMinMax.setText("Min: " + forecast.min + "° | Max: " + forecast.max + "°");

        String desc = forecast.description == null ? "" : forecast.description.toLowerCase();
        if (desc.contains("sol") || desc.contains("limpo")) {
            holder.imgIcon.setImageResource(R.drawable.sun);
        } else if (desc.contains("chuva") || desc.contains("rain")) {
            holder.imgIcon.setImageResource(R.drawable.rain);
        }
    }

    @Override
    public int getItemCount() {
        return forecastList == null ? 0 : forecastList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgIcon;
        TextView txtDate, txtDesc, txtMinMax;

        ViewHolder(View itemView) {
            super(itemView);
            imgIcon = itemView.findViewById(R.id.imgIcon);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtDesc = itemView.findViewById(R.id.txtDesc);
            txtMinMax = itemView.findViewById(R.id.txtMinMax);
        }
    }
}
