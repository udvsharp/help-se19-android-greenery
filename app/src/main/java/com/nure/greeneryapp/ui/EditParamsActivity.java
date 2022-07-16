package com.nure.greeneryapp.ui;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nure.greeneryapp.databinding.ActivityEditParamsBinding;
import com.nure.greeneryapp.rest.RestApi;
import com.nure.greeneryapp.rest.api.ParametersService;
import com.nure.greeneryapp.rest.model.DeviceParameters;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditParamsActivity extends AppCompatActivity {

    private DeviceParameters params;
    ActivityEditParamsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditParamsBinding.inflate(this.getLayoutInflater());
        setContentView(binding.getRoot());

        String id = getIntent().getExtras().getString("target");
        ParametersService service = RestApi.getInstance().Parameters();

        service.GetParameters(id).enqueue(new Callback<DeviceParameters>() {
            @Override
            public void onResponse(Call<DeviceParameters> call, Response<DeviceParameters> response) {
                if (response.isSuccessful()) {
                    params = response.body();
                    fillFields();
                } else {
                    Toast.makeText(EditParamsActivity.this, "Failure!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<DeviceParameters> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(EditParamsActivity.this, "Failure!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        binding.update.setOnClickListener(v -> {
            params.setAirHumidity(Integer.parseInt(binding.airHumidity.getText().toString()));
            params.setLightLevel(Integer.parseInt(binding.lightLevel.getText().toString()));
            params.setAirTemperature(Integer.parseInt(binding.airTemp.getText().toString()));
            params.setCo2Level(Integer.parseInt(binding.co2level.getText().toString()));
            params.setGroundHumidity(Integer.parseInt(binding.groundHumidity.getText().toString()));

            service.UpdateParameters(id, params).enqueue(new Callback<DeviceParameters>() {
                @Override
                public void onResponse(Call<DeviceParameters> call, Response<DeviceParameters> response) {
                    if (response.isSuccessful()) {
                        finish();
                    } else {
                        Toast.makeText(EditParamsActivity.this, "Failure!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }

                @Override
                public void onFailure(Call<DeviceParameters> call, Throwable t) {
                    t.printStackTrace();
                    Toast.makeText(EditParamsActivity.this, "Failure!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        });
    }

    private void fillFields() {
        binding.airHumidity.setText(params.getAirHumidity().toString());
        binding.lightLevel.setText(params.getLightLevel().toString());
        binding.airTemp.setText(params.getAirTemperature().toString());
        binding.co2level.setText(params.getCo2Level().toString());
        binding.groundHumidity.setText(params.getGroundHumidity().toString());
    }
}