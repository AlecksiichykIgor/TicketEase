package com.example.graduate_work_bachelor_app.retrofit;

import com.example.graduate_work_bachelor_app.model.trains.TicketTrain;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TicketsTrainApi {
    @GET("/api/trains/tickets/client/{clientId}/active")
    Call<List<TicketTrain>> getActiveTicketsByClientId(@Path("clientId") int clientId);
    @GET("/api/trains/tickets/schedule/{scheduleId}")
    Call<List<TicketTrain>> getActiveTicketsByScheduleId(@Path("scheduleId") int scheduleId);

    @POST("/api/trains/tickets/save")
    Call<Void> saveTicket(@Body TicketTrain ticket);

    @PUT("/api/trains/tickets/{ticketId}/return")
    Call<Void> returnTicket(@Path("ticketId") int ticketId);

    @GET("api/trains/tickets/active")
    Call<List<TicketTrain>> getActiveTickets();
}
