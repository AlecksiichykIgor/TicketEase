package com.example.graduate_work_bachelor_app.retrofit;

import com.example.graduate_work_bachelor_app.model.buses.TicketsBus;
import com.example.graduate_work_bachelor_app.model.planes.TicketsPlane;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TicketsBusApi {
    @GET("/api/bus/tickets/client/{clientId}/active")
    Call<List<TicketsBus>> getActiveTicketsByClientId(@Path("clientId") int clientId);
    @GET("/api/bus/tickets/schedule/{scheduleId}")
    Call<List<TicketsBus>> getActiveTicketsByScheduleId(@Path("scheduleId") int scheduleId);

    @POST("/api/bus/tickets/save")
    Call<Void> saveTicket(@Body TicketsBus ticket);

    @PUT("/api/bus/tickets/{ticketId}/return")
    Call<Void> returnTicket(@Path("ticketId") int ticketId);

    @GET("/api/bus/tickets/active")
    Call<List<TicketsBus>> getActiveTickets();
}
