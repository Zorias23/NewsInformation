package com.example.admin.newsinformation.remote;




import com.example.admin.newsinformation.model.NewsResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Admin on 11/29/2017.
 */

public interface RemoteService {

    @GET("everything?q=bitcoin&apiKey=" + RemoteDataSource.API_KEY)
    Observable<NewsResponse> getCurrentNews();




}
