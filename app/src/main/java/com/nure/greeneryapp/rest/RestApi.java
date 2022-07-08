package com.nure.greeneryapp.rest;

import com.nure.greeneryapp.rest.api.AuthService;
import com.nure.greeneryapp.util.PrefsUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApi {
    private static RestApi sInstance;
    private Retrofit retrofit = null;

    public static final String TAG_EXT = "Retrofit-Networking-Ext";
    public static final String TAG_IN = "Retrofit-Networking-In";

    private RestApi() {
        OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(new AuthInterceptor())
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

    public void addAuthInterceptor() {

    }

    public Retrofit client() {
        return retrofit;
    }

    public AuthService Auth() {
        return retrofit.create(AuthService.class);
    }

    private static class AuthInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            PrefsUtils utils = PrefsUtils.getInstance();
            if (utils.isTokenSet()) {
                String token = utils.getToken();
                request = request.newBuilder()
                        .addHeader("Authorization", "Bearer" + token)
                        .build();
            }
            return chain.proceed(request);
        }
    }
}
