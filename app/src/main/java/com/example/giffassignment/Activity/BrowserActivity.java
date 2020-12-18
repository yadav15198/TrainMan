package com.example.giffassignment.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ProgressBar;

import com.example.giffassignment.Adapter.GiffAdapter;
import com.example.giffassignment.R;
import com.example.giffassignment.Repositery.GifRepositery;
import com.example.giffassignment.Repositery.GifService;
import com.example.giffassignment.Repositery.String_Constants;
import com.example.giffassignment.model.DataClass;
import com.example.giffassignment.model.GifArray;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BrowserActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    GiffAdapter adapter;
    GifArray gifDetails;
    ProgressBar progressBar,progressBar2;
    Boolean isScrolled = false;
    LinearLayoutManager layoutManager;
    GifService gifService;
    GifArray array;
    int ofset = 0,counter = 1;
    int currentItoms,totalItems,scrolledItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        progressBar = findViewById(R.id.progress);
        progressBar2 = findViewById(R.id.progress2);
        gifDetails = new GifArray();
        layoutManager = new GridLayoutManager(getApplicationContext(),1);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        gifService = GifRepositery.getInstance().create(GifService.class);
        loadFirstPage();

        //On Scrolling
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                      isScrolled = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItoms = layoutManager.getChildCount();
                totalItems = layoutManager.getItemCount();
                scrolledItems = layoutManager.findFirstVisibleItemPosition();
                System.out.println(currentItoms+" "+totalItems+" "+scrolledItems);
                if(isScrolled && currentItoms + scrolledItems == totalItems){
                    if(counter < 10) {
                        isScrolled = false;
                        counter++;
                        loadNextPage();
                    }
                }

            }
        });




    }
  ///Method for loading first page
    private void loadFirstPage() {
        progressBar.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Call<GifArray> call = gifService.get_url(String_Constants.api_key,25,0);
                call.enqueue(new Callback<GifArray>() {
                    @Override
                    public void onResponse(Call<GifArray> call, Response<GifArray> response) {
                         array = response.body();
                        adapter = new GiffAdapter(array,getApplicationContext());
                        recyclerView.setAdapter(adapter);
                        //adapter.setGifList(array);
                        progressBar.setVisibility(View.INVISIBLE);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<GifArray> call, Throwable t) {

                    }
                });
            }
        },1000);
    }


       /// Method For loading next page
        public void loadNextPage(){
        progressBar2.setVisibility(View.VISIBLE);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    ofset = ofset + 25;
                    Call<GifArray> call = gifService.get_url(String_Constants.api_key,25,ofset);
                    call.enqueue(new Callback<GifArray>() {
                        @Override
                        public void onResponse(Call<GifArray> call, Response<GifArray> response) {
                            GifArray array1 = response.body();
                            for(int i = 0; i<array1.getData().size();i++){
                                array.getData().add(array1.getData().get(i));
                            }
                            //recyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                            progressBar2.setVisibility(View.GONE);
                        }

                        @Override
                        public void onFailure(Call<GifArray> call, Throwable t) {

                        }
                    });
                }
            },5000);
        }


}