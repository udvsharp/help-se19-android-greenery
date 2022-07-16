package com.nure.greeneryapp.ui.profile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.nure.greeneryapp.R;
import com.nure.greeneryapp.databinding.FragmentProfileBinding;
import com.nure.greeneryapp.rest.RestApi;
import com.nure.greeneryapp.rest.api.UserService;
import com.nure.greeneryapp.rest.model.UserProfile;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {

    private UserProfile profile;
    FragmentProfileBinding binding;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        UserService service = RestApi.getInstance().Users();
        service.GetUserProfile().enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                // TODO: logout if 401
                UserProfile body = response.body();
                if (body != null) {
                    profile = body;
                }
                fillFields();
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {
                Log.e(RestApi.TAG_EXT, t.getMessage());
            }
        });
    }

    private void fillFields() {
        binding.name.setText(profile.getName());
        binding.surname.setText(profile.getSurname());
        binding.email.setText(profile.getEmail());
        binding.organization.setText(profile.getOrganization());
        binding.role.setText(profile.getRole());
    }

}