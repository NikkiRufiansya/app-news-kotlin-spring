package com.nikki.newsappjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.nikki.newsappjava.adapter.NewsAdapter;
import com.nikki.newsappjava.core.retrofit.UrlApi;
import com.nikki.newsappjava.core.service.DeviceService;
import com.nikki.newsappjava.core.service.NewsService;
import com.nikki.newsappjava.databinding.ActivityMainBinding;
import com.nikki.newsappjava.model.NewsModels;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    private List<NewsModels> newsModels = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private NewsAdapter newsAdapter;
    private NewsService newsService = UrlApi.getNews();
    private DeviceService deviceService = UrlApi.getDevice();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        getNews();
        setVisitor();
        setDevice(getDeviceName());
        binding.swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getNews();

            }
        });


    }

    private void getNews() {
        newsModels.clear();
        newsService.getNews().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    // Handle successful response
                    ResponseBody responseBody = response.body();
                    if (responseBody != null) {
                        try {
                            String respon = responseBody.string();
                            JSONObject object = new JSONObject(respon);
                            JSONArray data = object.getJSONArray("data");
                            for (int i = 0; i < data.length(); i++) {
                                JSONObject news = data.getJSONObject(i);
                                newsModels.add(new NewsModels(
                                        news.getString("content"),
                                        news.getString("image"),
                                        news.getString("title"),
                                        news.getString("fileName"),
                                        news.getString("description")
                                ));
                            }
                            newsAdapter = new NewsAdapter(MainActivity.this, newsModels);
                            linearLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
                            binding.rvNews.setLayoutManager(linearLayoutManager);
                            binding.rvNews.setAdapter(newsAdapter);
                            newsAdapter.notifyDataSetChanged();
                            binding.swipe.setRefreshing(false);
                        } catch (IOException | JSONException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        // Handle the case when the response body is null
                        throw new RuntimeException("Response body is null");
                    }
                } else if (response.code() == 404) {
                    // Handle 404 response (Not Found)
                    // For example, you can show an error message or perform some other action
                    Toast.makeText(MainActivity.this, "News not found", Toast.LENGTH_SHORT).show();
                    binding.swipe.setRefreshing(false);
                } else {
                    // Handle other non-successful responses
                    throw new RuntimeException("Response is not successful");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                // Handle the failure case
            }
        });
    }

    private void setVisitor(){
        newsService.setVisitor().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    System.out.println("berhasil");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private void setDevice(String deviceName){
        deviceService.setDevice(deviceName).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    System.out.println("Berhasil");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }


    public String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        } else {
            return capitalize(manufacturer) + " " + model;
        }
    }


    private String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) {
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }
    }

}