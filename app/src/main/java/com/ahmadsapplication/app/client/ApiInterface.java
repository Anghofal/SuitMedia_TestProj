package com.ahmadsapplication.app.client;

import com.ahmadsapplication.app.ServiceApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("api/users?page=1&per_page=10")
    Call<ServiceApiResponse> getServiceList();

}
