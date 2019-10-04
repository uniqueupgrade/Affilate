package com.example.mom.afflilate.services;

import com.example.mom.afflilate.interfaces.CommonAPIs;
import com.example.mom.afflilate.utils.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommonAPIServices {
    private Retrofit mRetrofit = null;

    public CommonAPIs getCommonAPIAuthorization() {
        try {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder().connectTimeout(1, TimeUnit.MINUTES)
                    .readTimeout(1, TimeUnit.MINUTES)
                    .writeTimeout(1, TimeUnit.MINUTES);
            httpClient.addInterceptor(new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();
                    Request request = AuthorizationHeaders.getRequestBuilder(original);
                    return chain.proceed(request);
                }
            });
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            if (mRetrofit == null) {
                mRetrofit = new Retrofit.Builder()
                        .client(httpClient.build())
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .baseUrl(Constants.URL_API)
                        .build();
            }

            return mRetrofit.create(CommonAPIs.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
