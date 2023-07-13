package com.nikki.newsappjava.core.service;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface NewsService {
    @Headers("Accept: application/json")
    @GET("news")
    Call<ResponseBody> getNews();

    @Headers("Accept: application/json")
    @POST("visitor")
    Call<ResponseBody> setVisitor();
}
