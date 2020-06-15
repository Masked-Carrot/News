package com.carrot.news;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class MainActivityViewmodel extends ViewModel {

    private NewsRepository newsRepository;
    private MutableLiveData<NewsResponse> liveNews;

    public void init(){
        if(liveNews != null)
            return;
        newsRepository = NewsRepository.getInstance();
        liveNews = newsRepository.getDailyNews("google-news-in" , "Api key here");

    }

    public LiveData<NewsResponse> getNews(){
        return liveNews;
    }

    public void refreshNews(){
        liveNews = newsRepository.getDailyNews("google-news-in" , "Api key here");
    }
}
