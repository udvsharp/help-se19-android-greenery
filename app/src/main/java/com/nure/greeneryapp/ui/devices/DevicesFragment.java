package com.nure.greeneryapp.ui.devices;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nure.greeneryapp.adapter.DeviceRecyclerViewAdapter;
import com.nure.greeneryapp.databinding.FragmentDevicesBinding;
import com.nure.greeneryapp.rest.RestApi;
import com.nure.greeneryapp.rest.api.DevicesService;
import com.nure.greeneryapp.rest.model.Device;
import com.nure.greeneryapp.rest.model.DeviceResponse;
import com.nure.greeneryapp.util.PrefsUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A fragment representing a list of Items.
 */
public class DevicesFragment extends Fragment {
    private FragmentDevicesBinding binding;
    DevicesService service;
    private RecyclerView recyclerView;
    private DeviceRecyclerViewAdapter adapter;

    public DevicesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        service = RestApi.getInstance().Devices();
        fillItems();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDevicesBinding.inflate(getLayoutInflater());
        View root = binding.getRoot();

        // Set the adapter
        Context context = root.getContext();
        recyclerView = binding.list;
        adapter = new DeviceRecyclerViewAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        fillItems();
    }

    private void fillItems() {
        PrefsUtils utils = PrefsUtils.getInstance();

        String organizationId = utils.getOrganizationId();
        if (!organizationId.equals("NULL")) {
            service.GetOrganizationDevices(organizationId).enqueue(new Callback<DeviceResponse>() {
                @Override
                public void onResponse(Call<DeviceResponse> call, Response<DeviceResponse> response) {
                    DeviceResponse body = response.body();
                    if (body != null) {
                        List<Device> devices = new ArrayList<>();
                        devices.addAll(body.getTaken());
                        devices.addAll(body.getAvailable());

                        adapter.setItems(devices);
                    } else {
                        Log.e(RestApi.TAG_EXT, "Items response is null!");
                    }
                }

                @Override
                public void onFailure(Call<DeviceResponse> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }
    }
}