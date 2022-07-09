package com.nure.greeneryapp.ui.register;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.nure.greeneryapp.MainActivity;
import com.nure.greeneryapp.R;
import com.nure.greeneryapp.databinding.ActivityRegisterBinding;
import com.nure.greeneryapp.util.PrefsUtils;

// TODO: fix bad request error
public class RegisterActivity extends AppCompatActivity {

    public static final String TAG = "LoginActivity";
    private PrefsUtils prefsUtils;

    private RegisterViewModel registerViewModel;
    private @NonNull
    ActivityRegisterBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Init
        PrefsUtils.init(this);

        // Check token
        prefsUtils = PrefsUtils.getInstance();
        if (prefsUtils.isTokenSet()) {
            setResult(Activity.RESULT_OK);

            String token = prefsUtils.getToken();
            Log.d(TAG, "Token is present: " + token);
            finish();
            proceedToMainActivity();

            return;
        }

        // Init for draw
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        registerViewModel = new ViewModelProvider(this, new RegisterViewModelFactory())
                .get(RegisterViewModel.class);

        // Vars
        final EditText usernameEditText = binding.username;
        final EditText surnameEditText = binding.usersurname;
        final EditText emailEditText = binding.email;
        final EditText passwordEditText = binding.password;
        final EditText roleEditText = binding.role;
        final EditText organizationEditText = binding.organization;
        final Button registerButton = binding.register;
        final ProgressBar loadingProgressBar = binding.loading;

        registerViewModel.getRegisterFormState().observe(this, loginFormState -> {
            if (loginFormState == null) {
                return;
            }
            registerButton.setEnabled(loginFormState.isDataValid());
            if (loginFormState.getUsernameError() != null) {
                usernameEditText.setError(getString(loginFormState.getUsernameError()));
            }
            if (loginFormState.getSurnameError() != null) {
                surnameEditText.setError(getString(loginFormState.getSurnameError()));
            }
            if (loginFormState.getEmailError() != null) {
                emailEditText.setError(getString(loginFormState.getEmailError()));
            }
            if (loginFormState.getPasswordError() != null) {
                passwordEditText.setError(getString(loginFormState.getPasswordError()));
            }
            if (loginFormState.getRoleError() != null) {
                roleEditText.setError(getString(loginFormState.getRoleError()));
            }
            if (loginFormState.getOrganizationError() != null) {
                organizationEditText.setError(getString(loginFormState.getOrganizationError()));
            }
        });

        registerViewModel.getLoginResult().observe(this, loginResult -> {
            if (loginResult == null) {
                return;
            }
            loadingProgressBar.setVisibility(View.GONE);
            if (loginResult.getError() != null) {
                showLoginFailed(loginResult.getError());
            }
            if (loginResult.getSuccess() != null) {
                updateUiWithUser(loginResult.getSuccess());
                setResult(Activity.RESULT_OK);
                finish();
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                registerViewModel.registerDataChanged(
                        usernameEditText.getText().toString(),
                        surnameEditText.getText().toString(),
                        passwordEditText.getText().toString(),
                        emailEditText.getText().toString(),
                        roleEditText.getText().toString(),
                        organizationEditText.getText().toString()
                );
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        roleEditText.addTextChangedListener(afterTextChangedListener);
        organizationEditText.addTextChangedListener(afterTextChangedListener);

        organizationEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                registerViewModel.register(
                        usernameEditText.getText().toString(),
                        surnameEditText.getText().toString(),
                        passwordEditText.getText().toString(),
                        emailEditText.getText().toString(),
                        roleEditText.getText().toString(),
                        organizationEditText.getText().toString()
                );
            }
            return false;
        });

        registerButton.setOnClickListener(v -> {
            loadingProgressBar.setVisibility(View.VISIBLE);
            registerViewModel.register(
                    usernameEditText.getText().toString(),
                    surnameEditText.getText().toString(),
                    passwordEditText.getText().toString(),
                    emailEditText.getText().toString(),
                    roleEditText.getText().toString(),
                    organizationEditText.getText().toString()
            );
        });
    }

    private void proceedToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void updateUiWithUser(RegisteredUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        prefsUtils.saveToken(model.getToken());
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
        proceedToMainActivity();
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}