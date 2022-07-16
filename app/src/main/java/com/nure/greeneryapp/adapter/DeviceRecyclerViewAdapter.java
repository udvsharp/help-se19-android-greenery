package com.nure.greeneryapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nure.greeneryapp.R;
import com.nure.greeneryapp.databinding.DeviceListItemBinding;
import com.nure.greeneryapp.rest.RestApi;
import com.nure.greeneryapp.rest.api.DevicesService;
import com.nure.greeneryapp.rest.model.Device;
import com.nure.greeneryapp.rest.model.DeviceParameters;
import com.nure.greeneryapp.rest.model.Plant;
import com.nure.greeneryapp.ui.EditParamsActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeviceRecyclerViewAdapter extends RecyclerView.Adapter<DeviceRecyclerViewAdapter.ViewHolder> {

    private List<Device> devices = new ArrayList<>();

    public DeviceRecyclerViewAdapter() {
    }

    public void setItems(List<Device> devices) {
        this.devices = devices;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(DeviceListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Device item = devices.get(position);
        holder.bindTo(item);
    }

    @Override
    public int getItemCount() {
        return devices.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public Device item;
        // Main
        private final View root;
        public final TextView idView;
        public final TextView nameView;
        // Parameters
        public final View mainDivider;
        public final TextView co2LevelView;
        public final TextView groundHumidityView;
        public final TextView airHumidityView;
        public final TextView airTempView;
        public final TextView lightLevelView;
        // Target Parameters
        public final View targetDivider;
        public final TextView targetCo2LevelView;
        public final TextView targetGroundHumidityView;
        public final TextView targetAirHumidityView;
        public final TextView targetAirTempView;
        public final TextView targetLightLevelView;

        public ViewHolder(DeviceListItemBinding binding) {
            super(binding.getRoot());

            root = binding.getRoot();
            idView = binding.deviceId;
            nameView = binding.speciesName;

            mainDivider = binding.mainDivider;
            targetCo2LevelView = binding.targetCo2Level;
            targetGroundHumidityView = binding.targetGroundHumidity;
            targetAirHumidityView = binding.targetAirHumidity;
            targetAirTempView = binding.targetAirTemperature;
            targetLightLevelView = binding.targetLightLevel;

            targetDivider = binding.targetDivider;
            co2LevelView = binding.co2Level;
            groundHumidityView = binding.groundHumidity;
            airHumidityView = binding.airHumidity;
            airTempView = binding.airTemperature;
            lightLevelView = binding.lightLevel;
        }

        public void bindTo(Device item) {
            DevicesService service = RestApi.getInstance().Devices();

            this.item = item;
            int position = getAbsoluteAdapterPosition();
            Plant plant = item.getPlant();
            DeviceParameters parameter = item.getParameter();
            DeviceParameters targetParameters = null;
            Device value = devices.get(position);

            boolean hasTarget = !item.isAvailable();

            Context context = root.getContext();
            Context appContext = context.getApplicationContext();
            Resources resources = context.getResources();

            nameView.setVisibility(View.VISIBLE);
            mainDivider.setVisibility(View.VISIBLE);
            targetCo2LevelView.setVisibility(View.VISIBLE);
            targetGroundHumidityView.setVisibility(View.VISIBLE);
            targetAirHumidityView.setVisibility(View.VISIBLE);
            targetAirTempView.setVisibility(View.VISIBLE);
            targetLightLevelView.setVisibility(View.VISIBLE);
            targetDivider.setVisibility(View.VISIBLE);

            // Card color
            if (hasTarget) {
                targetParameters = item.getPlant().getTargetParams();

                Integer colorId = null;
                if (item.isWorking()) {
                    colorId = R.color.color_working;
                } else {
                    colorId = R.color.color_offline;
                }
                root.setBackgroundTintList(resources.getColorStateList(colorId, appContext.getTheme()));
            } else {
                nameView.setVisibility(View.GONE);
                mainDivider.setVisibility(View.GONE);
                targetCo2LevelView.setVisibility(View.GONE);
                targetGroundHumidityView.setVisibility(View.GONE);
                targetAirHumidityView.setVisibility(View.GONE);
                targetAirTempView.setVisibility(View.GONE);
                targetLightLevelView.setVisibility(View.GONE);
                targetDivider.setVisibility(View.GONE);
            }

            // Main
            idView.setText(item.getId());
            if (hasTarget) {
                nameView.setText(plant.getSpecies().getName());
            }
            // Parameters
            co2LevelView.setText("CO2 Level: " + parameter.getCo2Level());
            groundHumidityView.setText("Ground Humidity: " + parameter.getGroundHumidity());
            airHumidityView.setText("Air Humidity: " + parameter.getAirHumidity());
            airTempView.setText("Air Temperature: " + parameter.getAirTemperature());
            lightLevelView.setText("Light Level: " + parameter.getLightLevel());

            if (!hasTarget) {
                idView.setTextColor(context.getColor(android.R.color.black));
                co2LevelView.setTextColor(context.getColor(android.R.color.black));
                groundHumidityView.setTextColor(context.getColor(android.R.color.black));
                airHumidityView.setTextColor(context.getColor(android.R.color.black));
                airTempView.setTextColor(context.getColor(android.R.color.black));
                lightLevelView.setTextColor(context.getColor(android.R.color.black));
            }

            // Target Parameters
            if (hasTarget) {
                // Color if matches
                highlightTextIf(co2LevelView, parameter.getCo2Level(), targetParameters.getCo2Level());
                highlightTextIf(groundHumidityView, parameter.getGroundHumidity(), targetParameters.getGroundHumidity());
                highlightTextIf(airHumidityView, parameter.getAirHumidity(), targetParameters.getAirHumidity());
                highlightTextIf(airTempView, parameter.getAirTemperature(), targetParameters.getAirTemperature());
                highlightTextIf(lightLevelView, parameter.getLightLevel(), targetParameters.getLightLevel());

                targetCo2LevelView.setText("CO2 Level: " + targetParameters.getCo2Level());
                targetGroundHumidityView.setText("Ground Humidity: " + targetParameters.getGroundHumidity());
                targetAirHumidityView.setText("Air Humidity: " + targetParameters.getAirHumidity());
                targetAirTempView.setText("Air Temperature: " + targetParameters.getAirTemperature());
                targetLightLevelView.setText("Light Level: " + targetParameters.getLightLevel());
            }

            if (hasTarget) {
                // Edit on click
                DeviceParameters finalTargetParameters = targetParameters;
                root.setOnClickListener(v -> {
                    Intent intent = new Intent(context, EditParamsActivity.class);
                    intent.putExtra("target", finalTargetParameters.getId());
                    context.startActivity(intent);
                });
            }

            // Delete on long click
            root.setOnLongClickListener(view -> {

                service.DeleteDevice(value.getId()).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            devices.remove(position);

                            notifyItemRemoved(position);
                            notifyItemRangeChanged(position, devices.size());
                            Toast.makeText(context, "Item removed successfully!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Failure!", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(context, "Failure!", Toast.LENGTH_LONG).show();
                        t.printStackTrace();
                    }
                });
                return true;
            });
        }

        private void highlightTextIf(TextView view, int parameter, int targetParameter) {
            if (parameter != targetParameter) {
                view.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
            }
        }

        @Override
        public String toString() {
            return super.toString() + " '" + nameView.getText() + "'";
        }
    }
}