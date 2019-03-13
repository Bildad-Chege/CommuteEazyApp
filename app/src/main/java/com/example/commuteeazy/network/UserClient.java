package com.example.commuteeazy.network;

import com.example.commuteeazy.DO.Operator;
import com.example.commuteeazy.DO.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserClient {

    @GET("login/{username}/{password}")
    Call<User> login(@Path("username")String userName,@Path("password") String password);

    @POST("addUser")
    Call<User> signup(@Body User user);

    @GET("operators")
    Call<List<Operator>> getAllOperators();
}
