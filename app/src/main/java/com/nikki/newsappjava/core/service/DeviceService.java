package com.nikki.newsappjava.core.service;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface DeviceService {
    @Headers("Accept: application/json")
    @POST("devices")
    Call<ResponseBody> setDevice(@Body String nameDevice);
}
