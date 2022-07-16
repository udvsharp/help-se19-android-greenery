package com.nure.greeneryapp.rest;

import android.util.Log;

import com.nure.greeneryapp.rest.api.AuthService;
import com.nure.greeneryapp.rest.api.DevicesService;
import com.nure.greeneryapp.rest.api.ParametersService;
import com.nure.greeneryapp.rest.api.UserService;
import com.nure.greeneryapp.util.PrefsUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApi {
    public static final String TAG_EXT = "Retrofit-Networking-Ext";
    public static final String TAG_IN = "Retrofit-Networking-In";

    private static RestApi sInstance;

    private Retrofit retrofit;

    private RestApi() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new AuthInterceptor())
                .addInterceptor(new LoggingInterceptor())
                .addInterceptor(new CommonErrorInterceptor())
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.147.61:3000")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    public static RestApi getInstance() {
        if (sInstance == null) {
            sInstance = new RestApi();
        }
        return sInstance;
    }

    public Retrofit client() {
        return retrofit;
    }

    public AuthService Auth() {
        return retrofit.create(AuthService.class);
    }

    public UserService Users() {
        return retrofit.create(UserService.class);
    }

    public DevicesService Devices() {
        return retrofit.create(DevicesService.class);
    }

    public ParametersService Parameters() {
        return retrofit.create(ParametersService.class);
    }

    private static class AuthInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            PrefsUtils utils = PrefsUtils.getInstance();
            if (utils.isTokenSet()) {
                String token = utils.getToken();

                Log.d(TAG_IN, String.format("Using auth token: %s", token));

                request = request.newBuilder()
                        .addHeader("Authorization", "Bearer " + token)
                        .build();
            }
            return chain.proceed(request);
        }
    }

    private static class LoggingInterceptor implements Interceptor {
        @Override public Response intercept(Interceptor.Chain chain) throws IOException {
            Request request = chain.request();

            long t1 = System.nanoTime();
            Log.i(TAG_IN, String.format("Sending request %s on %s%n%s",
                    request.url(), chain.connection(), request.headers()));

            Response response = chain.proceed(request);

            long t2 = System.nanoTime();
            Log.i(TAG_IN, String.format("Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));

            return response;
        }
    }

    private static class CommonErrorInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response response = chain.proceed(chain.request());

            if (response.code() == 401) {
                Log.e(TAG_IN, "Token is invalid! Deleting from SharedPrefs!");
                PrefsUtils utils = PrefsUtils.getInstance();
                utils.deleteToken();
            }

            return response;
        }
    }
}
