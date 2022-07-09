package com.nure.greeneryapp.ui.login;

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
import com.nure.greeneryapp.rest.model.LoginInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {
    private static final String ERR_MSG_NOT_FOUND = "User Not found.";
    AuthService service;
    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();

    LoginViewModel(AuthService service) {
        this.service = service;
    }

    LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    public void login(String username, String surname, String password) {
        Call<LoggedInUser> call = service
                .Login(new LoginInfo(username, surname, password));
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
                loginResult.setValue(new LoginResult(
                        new LoggedInUserView(
                                body.getName(),
                                body.getToken()
                        )
                ));
            }

            @Override
            public void onFailure(@NonNull Call<LoggedInUser> call, @NonNull Throwable t) {
                Log.d(RestApi.TAG_EXT, String.valueOf(t.getMessage()));
                loginResult.setValue(new LoginResult(R.string.login_failed));
            }
        });
    }

    private void handleError(Response<LoggedInUser> response) {
        Log.w(RestApi.TAG_EXT, "Login failed: " + response.code());
        loginResult.setValue(new LoginResult(R.string.login_failed));
    }

    public void loginDataChanged(String username, String surname, String password) {
        Integer usernameError = null;
        Integer surnameError = null;
        Integer passwordError = null;
        boolean flag = true;

        if (!isUserNameValid(username)) {
            usernameError = R.string.invalid_username;
        } else if (!isPasswordValid(password)) {
            passwordError = R.string.invalid_password;
            flag = false;
        } else if (!isUserNameValid(surname)) {
            surnameError = R.string.invalid_surname;
            flag = false;
        }

        if (flag) {
            loginFormState.setValue(new LoginFormState(true));
        } else {
            loginFormState.setValue(new LoginFormState(usernameError, surnameError, passwordError));
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
