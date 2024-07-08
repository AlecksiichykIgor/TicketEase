package com.example.graduate_work_bachelor_app.retrofit;

import com.example.graduate_work_bachelor_app.model.Client;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserApi {
    @GET("/users/get-all")
    Call<List<Client>> getAllUsers();
    @GET("/users/{userPhone}")
    Call<Client> getUserByPhone(@Path("userPhone") String userPhone);
    @GET("/users/{userId}")
    Call<Client> getUserById(@Path("userId") int userId);
    @POST("/users/registration")
    Call<Void> registerUser(@Body Client client);
    @POST("/users/login")
    Call<ResponseBody> login(@Body Client client);
}
