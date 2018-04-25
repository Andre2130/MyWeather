package com.example.mcs.myweatherapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by MCS on 4/25/2018.
 */

public class RetrofitClient {
    public static Retrofit getClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APiService.url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    public static APiService getAPIService(){
        return getClient().create(APiService.class);
    }
}
