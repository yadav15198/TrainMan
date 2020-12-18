package com.example.giffassignment.Repositery;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GifRepositery {
private static Retrofit retrofit;
private static GifService gifService;

public static Retrofit getInstance(){
    if(retrofit == null){
        retrofit = new Retrofit.Builder()
                .baseUrl(String_Constants.str_url)
                .addConverterFactory(GsonConverterFactory.create()).build();
    }
    return retrofit;
}

    public static GifService getGifService() {
     if(gifService == null){
         gifService = getInstance().create(GifService.class);
     }
        return gifService;
    }
}
