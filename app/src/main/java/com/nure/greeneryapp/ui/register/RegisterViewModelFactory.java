package com.nure.greeneryapp.ui.register;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.nure.greeneryapp.rest.RestApi;

public class RegisterViewModelFactory implements ViewModelProvider.Factory {

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(RegisterViewModel.class)) {
            return (T) new RegisterViewModel(RestApi.getInstance().Auth());
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}