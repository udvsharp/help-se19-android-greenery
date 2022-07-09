package com.nure.greeneryapp.ui.register;

import android.util.Log;
import android.util.Patterns;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nure.greeneryapp.R;
import com.nure.greeneryapp.rest.RestApi;
import com.nure.greeneryapp.rest.api.AuthService;
import com.nure.greeneryapp.rest.model.LoggedInUser;
import com.nure.greeneryapp.rest.model.RegisterInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterViewModel extends ViewModel {
    private static final String ERR_MSG_NOT_FOUND = "User Not found.";
    AuthService service;
    private MutableLiveData<RegisterFormState> registerFormState = new MutableLiveData<>();
    private MutableLiveData<RegisterResult> loginResult = new MutableLiveData<>();

    RegisterViewModel(AuthService service) {
        this.service = service;
    }

    LiveData<RegisterFormState> getRegisterFormState() {
        return registerFormState;
    }

    LiveData<RegisterResult> getLoginResult() {
        return loginResult;
    }

    public void register(String username, String surname, String password, String email, String role, String organization) {
        Call<LoggedInUser> call = service
                .Register(new RegisterInfo(username, surname, password, email, role, organization));
        call.enqueue(new Callback<LoggedInUser>() {
            @Override
            public void onResponse(@NonNull Call<LoggedInUser> call, @NonNull Response<LoggedInUser> response) {
                LoggedInUser body = response.body();
                if (body == null) {
                    // TODO: detailed error handling
                    handleError(response);
                    return;
                }

                Log.d(RestApi.TAG_EXT, body.getToken());
                loginResult.setValue(new RegisterResult(
                        new RegisteredUserView(
                                body.getName(),
                                body.getToken()
                        )
                ));
            }

            @Override
            public void onFailure(@NonNull Call<LoggedInUser> call, @NonNull Throwable t) {
                Log.d(RestApi.TAG_EXT, String.valueOf(t.getMessage()));
                loginResult.setValue(new RegisterResult(R.string.register_failed));
            }
        });
    }

    private void handleError(Response<LoggedInUser> response) {
        Log.w(RestApi.TAG_EXT, "Login failed: " + response.code());
        loginResult.setValue(new RegisterResult(R.string.login_failed));
    }

    public void registerDataChanged(String username, String surname, String password, String email, String role, String organization) {
        Integer usernameError = null;
        Integer surnameError = null;
        Integer passwordError = null;
        Integer emailError = null;
        Integer roleError = null;
        Integer organizationError = null;
        boolean flag = true;

        if (!isUserNameValid(username)) {
            usernameError = R.string.invalid_username;
            flag = false;
        } else if (!isPasswordValid(password)) {
            passwordError = R.string.invalid_password;
            flag = false;
        } else if (!isUserNameValid(surname)) {
            surnameError = R.string.invalid_surname;
            flag = false;
        } else if (email.isEmpty()) {
            emailError = R.string.invalid_email;
            flag = false;
        } else if (role.isEmpty()) {
            roleError = R.string.invalid_role;
            flag = false;
        } else if (organization.isEmpty()) {
            organizationError = R.string.invalid_org;
            flag = false;
        }

        if (flag) {
            registerFormState.setValue(new RegisterFormState(true));
        } else {
            registerFormState.setValue(new RegisterFormState(usernameError, surnameError, passwordError, emailError, roleError, organizationError));
        }
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }
}
