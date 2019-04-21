package com.cristiangoncas.repository.network;

import android.content.Context;

import com.cristiangoncas.repository.utils.ConfigConstants;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConnectionProvider {

    private final Retrofit retrofit;

    public ConnectionProvider(Context context) {
        if (context == null) {
            throw new NullPointerException("Network Controller requires not null context.");
        }
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cache(new Cache(context.getCacheDir(), ConfigConstants.CACHE_SIZE))
                .addNetworkInterceptor(new StethoInterceptor())
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(ConfigConstants.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
