package com.example.admin.newsinformation.util;

/**
 * Created by Admin on 12/4/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.newsinformation.R;
import com.example.admin.newsinformation.ViewArticleActivity;
import com.example.admin.newsinformation.model.Article;

import java.util.ArrayList;
import java.util.List;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>
{
    public static List<Article> Articles = new ArrayList<>();
    Context context;

    public List<Article> getArticleList() {
        return Articles;
    }


    public static final String TAG = "RecyclerAdapter";

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: ");
        context = parent.getContext(); //set the context here so I can use it later in this class if I want
        //this will be the Articles.xml that I'm inflating
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_recycler_item, null);

        return new ViewHolder(view); //should be initialized with the inflated calls above
    }

    public RecyclerAdapter(List<Article> ArticleList) {
        this.Articles = ArticleList;
    }


    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: ");
        Article gr = Articles.get(position);
        holder.tvTitle.setText("Title: " + gr.getTitle());
        holder.tvDescription.setText("Description: " + gr.getDescription());
        holder.tvPublishedAt.setText("Published at: " + gr.getPublishedAt());

    }

    @Override
    public int getItemCount() {
        return Articles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //private final TextView tvName;
       // private final RecyclerView recyclerView;
        private final TextView tvTitle, tvDescription, tvPublishedAt;

        public ViewHolder(View ArticleView) {
                super(ArticleView);
                //this is where we do our initializations by findViewById method calls
                tvTitle = ArticleView.findViewById(R.id.tvTitle);
                tvDescription = ArticleView.findViewById(R.id.tvDescription);
                tvPublishedAt = ArticleView.findViewById(R.id.tvPublishedAt);
                ArticleView.setOnClickListener(this);
            }



        @Override
        public void onClick(View view) {
            //this is where I have my logic for when they click on an Article in the recyclerview, like if i want to go to the next activity
            /*
            ArrayList<Animal> animals = AnimalsRecyclerAdapter.animalsList;
            Log.d("AnimalsRecyclerAdapter", "onClick: you clicked position " + getPosition());
            Log.d(TAG, "onClick: this results in value from list: " + animals.get(getPosition()).getName());
            Intent intent = new Intent(view.getContext(), ViewAnimalActivity.class);
            intent.putExtra("animal", animals.get(getPosition()).getName());
            view.getContext().startActivity(intent); */
            Log.d(TAG, "onClick: you clicked on position" + getPosition());
            Article gr = Articles.get(getPosition());
            Intent intent = new Intent(view.getContext(), ViewArticleActivity.class);
            intent.putExtra("Article", gr);
            view.getContext().startActivity(intent);

        }
    }
}
