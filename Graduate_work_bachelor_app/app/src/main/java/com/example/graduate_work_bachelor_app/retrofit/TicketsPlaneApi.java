package com.example.graduate_work_bachelor_app.retrofit;

import com.example.graduate_work_bachelor_app.model.planes.TicketsPlane;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TicketsPlaneApi {
    @GET("/api/planes/tickets/client/{clientId}/active")
    Call<List<TicketsPlane>> getActiveTicketsByClientId(@Path("clientId") int clientId);
    @GET("/api/planes/tickets/schedule/{scheduleId}")
    Call<List<TicketsPlane>> getActiveTicketsByScheduleId(@Path("scheduleId") int scheduleId);

    @POST("/api/planes/tickets/save")
    Call<Void> saveTicket(@Body TicketsPlane ticket);

    @PUT("/api/planes/tickets/{ticketId}/return")
    Call<Void> returnTicket(@Path("ticketId") int ticketId);

    @GET("/api/plane/tickets/active")
    Call<List<TicketsPlane>> getActiveTickets();
}
