package com.example.admin.newsinformation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.admin.newsinformation.model.NewsResponse;
import com.example.admin.newsinformation.util.RecyclerAdapter;
import com.example.admin.newsinformation.view.main.MainContract;
import com.example.admin.newsinformation.view.main.MainPresenter;

public class MainActivity extends AppCompatActivity implements MainContract.View{
    MainPresenter presenter;
    public static final String TAG = MainPresenter.class.getSimpleName() + "MainActivity";
    NewsResponse news;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainPresenter();
        presenter.attachView(this);
        presenter.getCurrentNews();
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void setCurrentNews(NewsResponse newsResponse) {
        news = newsResponse;

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);

        recyclerView = findViewById(R.id.rvMain);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        recyclerView.setLayoutManager(layoutManager); //need layoutManager
        recyclerView.setItemAnimator(itemAnimator); //don't need this but it allows animation for each item
        RecyclerAdapter ra = new RecyclerAdapter(news.getArticles());
        recyclerView.setAdapter(ra); //need adapter
    }

    @Override
    public void showProgress(String progress) {

    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        presenter.detachView();
    }
}
