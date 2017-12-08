package com.example.admin.newsinformation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.newsinformation.model.Article;
import com.example.admin.newsinformation.util.DatabaseHelper;

public class ViewArticleActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    TextView tvName, tvTitle, tvDescription, tvUrl, tvPublishedAt;
    DatabaseHelper myDB;
    Article a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_article);
        myDB = new DatabaseHelper(this);
         a = (Article) getIntent().getSerializableExtra("Article");
        Log.d(TAG, "onCreate: article isn't null and the name is: " + a.getSource().getName());
        tvName = findViewById(R.id.tvName);
        tvTitle = findViewById(R.id.tvTitle2);
        tvDescription = findViewById(R.id.tvDescription2);
        tvPublishedAt = findViewById(R.id.tvPublishedAt2);
        tvUrl = findViewById(R.id.tvUrl);
        tvName.setText("Name: " + a.getSource().getName());
        tvTitle.setText("Title: " + a.getTitle());
        tvDescription.setText("Description: " + a.getDescription());
        tvUrl.setText("URL: " + a.getUrl());
        tvPublishedAt.setText("Published at: " + a.getPublishedAt());
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
       // myToolbar.setNavigationIcon(R.drawable.like_icon);
        setSupportActionBar(myToolbar);
    }

    public void saveArticle(View view) {
        long returnVal = myDB.saveArticle(a);
        if (returnVal > 0) {
            Toast.makeText(ViewArticleActivity.this, "Successfully entered " +a.getSource().getName() + "!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(ViewArticleActivity.this, "Something went wrong!", Toast.LENGTH_LONG).show();
        }
    }
}
