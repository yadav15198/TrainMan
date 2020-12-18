package com.example.giffassignment.Repositery;

import com.example.giffassignment.model.GifArray;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GifService {
  @GET("trending")
  Call<GifArray> get_url(@Query("api_key") String key,@Query("limit") int limit,@Query("offset") int offset);
}
