package com.carrot.news;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {

    @GET("top-headlines")
    Call<NewsResponse> getNewslist(@Query("sources") String newsSource, @Query("apiKey") String apiKey );

}
