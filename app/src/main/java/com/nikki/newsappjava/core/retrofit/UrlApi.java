package com.nikki.newsappjava.core.retrofit;

import com.nikki.newsappjava.core.service.DeviceService;
import com.nikki.newsappjava.core.service.NewsService;

public class UrlApi {

    public static String BASE_URL = "http://192.168.1.13:8080/api/";

    public static NewsService getNews(){
        return RetrotifClient.getClient(BASE_URL).create(NewsService.class);
    }

    public static DeviceService getDevice(){
        return RetrotifClient.getClient(BASE_URL).create(DeviceService.class);
    }
}
