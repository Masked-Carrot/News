package com.carrot.news;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepository {

    private static NewsRepository instance;

    public static NewsRepository getInstance(){
        if(instance == null)
            instance = new NewsRepository();
        return instance;
    }

    private NewsApi newsApi;

    public NewsRepository(){
        newsApi = RetrofitService.cteateService(NewsApi.class);
    }
    public MutableLiveData<NewsResponse> getDailyNews(String source , String APIkey){
        MutableLiveData<NewsResponse> listMutableLiveData = new MutableLiveData<>();
        newsApi.getNewslist(source , APIkey).enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if(response.isSuccessful())
                    listMutableLiveData.setValue(response.body());
                else
                    Log.e("RETROFIT" , "error detected in get daily news of news repository "+response.code());
            }
            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                listMutableLiveData.setValue(null);
                Log.e("Retrofit" , "error detected in get daily news "+t.toString() , t);
            }
        });
        return listMutableLiveData;
    }
}
