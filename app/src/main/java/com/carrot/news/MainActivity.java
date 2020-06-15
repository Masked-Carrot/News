package com.carrot.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.OnNewsClickListener {

    private RecyclerViewAdapter adapter;
    private RecyclerView recyclerView;
    private MainActivityViewmodel mainActivityViewmodel;
    List<Article> articles = new ArrayList<>();
    private Toolbar toolbar;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefreshLayout = findViewById(R.id.swipecontainer);
        recyclerView = findViewById(R.id.recycler_view);
        toolbar = findViewById(R.id.toolbar_of_main);
        setSupportActionBar(toolbar);


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mainActivityViewmodel.refreshNews();
                swipeRefreshLayout.setRefreshing(false);
            }
        });


        mainActivityViewmodel = ViewModelProviders.of(this).get(MainActivityViewmodel.class);
        mainActivityViewmodel.init();
        mainActivityViewmodel.getNews().observe(this, newsResponse ->
        {
            Log.d("observer" , "is now observing");
            List<Article> newsArticles = newsResponse.getArticles();
            articles.addAll(newsArticles);
            adapter.notifyDataSetChanged();
        });

        init();
    }

    private void init(){
        if(adapter == null) {
            adapter = new RecyclerViewAdapter(articles, this , this::onNewsClick);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setNestedScrollingEnabled(true);
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void onNewsClick(int pos) {
        Intent intent = new Intent(MainActivity.this , webview.class);
        intent.putExtra("url" , articles.get(pos).getUrl());
        startActivity(intent);
    }
}