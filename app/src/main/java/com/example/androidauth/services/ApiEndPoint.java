package com.example.androidauth.services;

import com.example.androidauth.models.Product;
import com.example.androidauth.models.User;
import com.example.androidauth.models.UserToken;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiEndPoint {

    @POST("auth/login")
    Call<UserToken> auth(@Body User user);

    @GET("products")
    Call<Product> getProducts();
}
