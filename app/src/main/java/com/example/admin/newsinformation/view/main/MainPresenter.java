package com.example.admin.newsinformation.view.main;

/**
 * Created by Admin on 12/4/2017.
 */

import android.util.Log;


import com.example.admin.newsinformation.remote.RemoteDataSource;
import com.example.admin.newsinformation.model.Article;
import com.example.admin.newsinformation.model.NewsResponse;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Admin on 11/29/2017.
 */

public class MainPresenter implements MainContract.Presenter
{
    MainContract.View view;
    public static final String TAG = "MainPresenter";
    private NewsResponse newsResponse; //in this example, JsonResponse is the main parent class in the JSON response, a list is returned from this, a list of Item objects

    private List<Article> items;


    @Override
    public void attachView(MainContract.View view)
    {
        this.view = view;
    }

    @Override
    public void detachView()
    {

    }

    @Override
    public void getCurrentNews()
    {
        RemoteDataSource.getCurrentNews()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<NewsResponse>()
                {
                    @Override
                    public void onSubscribe(Disposable d)
                    {
                        view.showProgress("Downloading weather data...");
                    }

                    @Override
                    public void onNext(NewsResponse weather)
                    {
                        newsResponse = weather;
                        Log.d(TAG, "onNext: " + weather.toString());
                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        view.showError(e.toString());
                        Log.d(TAG, "onError: "+e.toString());
                    }

                    @Override
                    public void onComplete()
                    {
                        view.setCurrentNews(newsResponse);
                        Log.d(TAG, "onComplete: we have this number of articles: " + newsResponse.getArticles().size());
                    }
                });
    }


}