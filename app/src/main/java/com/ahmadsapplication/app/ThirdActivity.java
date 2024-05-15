package com.ahmadsapplication.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahmadsapplication.app.client.ApiClient;
import com.ahmadsapplication.app.client.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThirdActivity extends AppCompatActivity {

    private TextView txtNoUser;
    private ImageView backButton;
    private RecyclerView dataListRecycleView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private DataAdapter dataAdapter;
    ArrayList<DataList> dataLists;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_screen);

        txtNoUser = findViewById(R.id.txtNoUser);
        swipeRefreshLayout = findViewById(R.id.swipeLayout);
        backButton = findViewById(R.id.backButtonThirdScreen);
        dataListRecycleView = findViewById(R.id.dataRecyleView);
        dataListRecycleView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        dataLists = new ArrayList<>();
        dataAdapter = new DataAdapter(ThirdActivity.this,dataLists);

        dataListRecycleView.setAdapter(dataAdapter);
        PopulatedServices();
    }

    @Override
    protected void onStart() {
        super.onStart();
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(ThirdActivity.this, SecondActivity.class);
                ThirdActivity.this.startActivity(myIntent);
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                PopulatedServices();
            }
        });
    }

    public void PopulatedServices(){
        ApiClient.getClient().create(ApiInterface.class).getServiceList().enqueue(new Callback<ServiceApiResponse>() {
            @Override
            public void onResponse(Call<ServiceApiResponse> call, Response<ServiceApiResponse> response) {
                if(response.isSuccessful()){
                    dataLists.addAll(response.body().getData());
                    dataAdapter.notifyDataSetChanged();
                    if(response.body().getData().size() == 0){
                        dataListRecycleView.setVisibility(View.GONE);
                        txtNoUser.setVisibility(View.VISIBLE);
                    }
                    else {
                        txtNoUser.setVisibility(View.GONE);
                        dataListRecycleView.setVisibility(View.VISIBLE);
                    }
                    swipeRefreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<ServiceApiResponse> call, Throwable t) {
                Log.d("Error",""+t.getStackTrace());
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}