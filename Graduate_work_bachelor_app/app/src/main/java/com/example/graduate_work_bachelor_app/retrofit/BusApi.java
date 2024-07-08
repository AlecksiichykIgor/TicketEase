package com.example.graduate_work_bachelor_app.retrofit;

import com.example.graduate_work_bachelor_app.model.buses.BusRoute;
import com.example.graduate_work_bachelor_app.model.buses.BusSchedule;

import java.time.LocalDate;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BusApi {
    @GET("/api/buses/schedules")
    Call<List<BusSchedule>> getBusSchedules(
            @Query("departureDate") LocalDate departureDate,
            @Query("departureCity") String departureCity,
            @Query("arrivalCity") String arrivalCity
    );

    @GET("/api/buses/schedules/all")
    Call<List<BusSchedule>> getAllBusSchedules();

    @GET("/api/buses/schedules/{scheduleId}")
    Call<BusSchedule> getBusScheduleById(@Path("scheduleId") int scheduleId);

    @GET("/api/buses/routes")
    Call<List<BusRoute>> getAllBusRoutes();

    @GET("/api/buses/routes/{routeId}")
    Call<BusRoute> getBusRouteById(@Path("routeId") int routeId);

    @GET("/api/buses/routes/number/{routeNumber}")
    Call<BusRoute> getBusRouteByNumber(@Path("routeNumber") String routeNumber);

    @GET("/api/buses/routes/search")
    Call<List<BusRoute>> searchBusRoutes(
            @Query("departureCity") String departureCity,
            @Query("arrivalCity") String arrivalCity
    );

    @POST("/api/buses/routes/save")
    Call<BusRoute> createBusRoute(@Body BusRoute busRoute);

    @PUT("/api/buses/routes/update/{routeId}")
    Call<BusRoute> updateBusRoute(@Path("routeId") int routeId, @Body BusRoute updatedRoute);

    @DELETE("/api/buses/routes/delete/{routeId}")
    Call<String> deleteBusRoute(@Path("routeId") int routeId);


}
