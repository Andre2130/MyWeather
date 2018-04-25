package com.example.mcs.myweatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView mTextViewResult;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewResult = findViewById(R.id.textView);
        Button button = findViewById(R.id.button);

/*
        APiService api = RetrofitClient.getAPIService();

        Call<List<WeatherModel>> call = api.getWeather();

        call.enqueue(new Callback<List<WeatherModel>>() {
            @Override
            public void onResponse(Call<List<WeatherModel>> call, retrofit2.Response<List<WeatherModel>> response) {

                List<WeatherModel> weather = new ArrayList<>();

                for(WeatherModel w: weather){

                    Log.d("main", String.valueOf(w.getMain()));
                }
            }

            @Override
            public void onFailure(Call<List<WeatherModel>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });*/


       mQueue = Volley.newRequestQueue(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jsonParse();
            }
        });
    }

  private void jsonParse() {

      String url = "http://api.openweathermap.org/data/2.5/weather?zip=30315,us&appid=e7b0145fa9e9dacbd5656ae786710032";

      JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
              new Response.Listener<JSONObject>() {
                  @Override
                  public void onResponse(JSONObject response) {
                      try {
                          JSONArray jsonArray = response.getJSONArray("main");

                          for (int i = 0; i < jsonArray.length(); i++) {
                              JSONObject main = jsonArray.getJSONObject(i);

                              double temp = main.getDouble("temp");
                              int humidity = main.getInt("humidity");
                              double pressure = main.getDouble("pressure");

                              mTextViewResult.append("Temp " + temp + ",Humidity " + humidity + ", Pressure " + pressure + "\n\n");

                          }
                      } catch (JSONException e) {
                          e.printStackTrace();
                      }
                  }
              }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
              error.printStackTrace();
          }
      });

      mQueue.add(request);
  }

    }

