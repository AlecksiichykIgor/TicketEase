package com.example.graduate_work_bachelor_app.retrofit;

import com.example.graduate_work_bachelor_app.model.Client;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AdminApi {
    @GET("/admin/clients")
    Call<List<Client>> getAllClients();

    @GET("/admin/clients/{clientId}")
    Call<Client> getClientById(@Path("clientId") int clientId);

    @PUT("/admin/clients/{clientId}")
    Call<String> updateClient(@Path("clientId") int clientId, @Body Client client);

    @DELETE("/admin/clients/{clientId}")
    Call<String> deleteClient(@Path("clientId") int clientId);

    @POST("/admin/clients/adminLogin")
    Call<ResponseBody> adminLogin(@Body Client admin);

    @PUT("/admin/adminUpdate/{clientId}")
    Call<String> updateAdmin(@Path("clientId") int clientId, @Body Client client);
}
