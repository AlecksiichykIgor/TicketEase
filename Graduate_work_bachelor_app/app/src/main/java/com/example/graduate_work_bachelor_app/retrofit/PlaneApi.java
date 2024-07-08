package com.example.graduate_work_bachelor_app.retrofit;

import com.example.graduate_work_bachelor_app.model.planes.AirplaneRoute;
import com.example.graduate_work_bachelor_app.model.planes.FlightSchedule;
import com.example.graduate_work_bachelor_app.model.trains.TrainSchedule;

import java.time.LocalDate;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PlaneApi {
    @GET("/api/planes/schedules")
    Call<List<FlightSchedule>> getFlightSchedules(
            @Query("departureDate") LocalDate departureDate,
            @Query("departureCity") String departureCity,
            @Query("arrivalCity") String arrivalCity
    );
    @GET("/api/planes/schedules/{scheduleId}")
    Call<FlightSchedule> getFlightScheduleById(@Path("scheduleId") int scheduleId);

    @GET("/api/planes/routes/{id}")
    Call<AirplaneRoute> getAirplaneRouteById(@Path("id") int id);

    @GET("/api/planes/routes/number/{routeNumber}")
    Call<AirplaneRoute> getAirplaneRouteByNumber(@Path("routeNumber") String routeNumber);

    @GET("/api/planes/routes")
    Call<List<AirplaneRoute>> getAllAirplaneRoutes();

    @POST("/api/planes/routes/save")
    Call<AirplaneRoute> createAirplaneRoute(@Body AirplaneRoute airplaneRoute);

    @PUT("/api/planes/routes/update/{id}")
    Call<AirplaneRoute> updateAirplaneRoute(@Path("id") int routeId, @Body AirplaneRoute updatedRoute);

    @POST("/api/planes/schedules/save")
    Call<FlightSchedule> createFlightSchedule(@Body FlightSchedule flightSchedule);

    @PUT("/api/planes/schedules/update/{id}")
    Call<FlightSchedule> updateFlightSchedule(@Path("id") int id, @Body FlightSchedule flightSchedule);
}
