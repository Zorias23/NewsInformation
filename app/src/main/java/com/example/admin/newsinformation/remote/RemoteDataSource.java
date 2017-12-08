package com.example.admin.newsinformation.remote;



import com.example.admin.newsinformation.model.NewsResponse;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Admin on 11/29/2017.
 */

public class RemoteDataSource {
    public static final String BASE_URL = "https://newsapi.org/v2/";

    public static final String API_KEY = "85741bbd3854469d8ba14b1642d9224c";



    public static Retrofit create()
    {
         return  new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
    }

    public static io.reactivex.Observable<NewsResponse> getCurrentNews()
    {
        Retrofit retrofit = create();
        RemoteService remoteService = retrofit.create(RemoteService.class);
        return remoteService.getCurrentNews();
    }




}
