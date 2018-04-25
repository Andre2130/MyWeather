package com.example.mcs.myweatherapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
//import rx.Observable;

/**
 * Created by MCS on 4/25/2018.
 */

public interface APiService {

    String url = "http://api.openweathermap.org/data/2.5/weather?zip=30315,us&appid=e7b0145fa9e9dacbd5656ae786710032";

    @GET("zip")
    Call<List<WeatherModel>> getWeather();
}
