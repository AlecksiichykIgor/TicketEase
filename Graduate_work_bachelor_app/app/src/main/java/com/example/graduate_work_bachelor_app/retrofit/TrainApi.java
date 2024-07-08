package com.example.graduate_work_bachelor_app.retrofit;

import com.example.graduate_work_bachelor_app.model.buses.BusSchedule;
import com.example.graduate_work_bachelor_app.model.trains.TrainRoute;
import com.example.graduate_work_bachelor_app.model.trains.TrainSchedule;

import java.time.LocalDate;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Body;
import retrofit2.http.Query;

public interface TrainApi {

    @GET("/api/trains/routes/{id}")
    Call<TrainRoute> getTrainRouteById(@Path("id") int id);

    @GET("/api/trains/routes")
    Call<List<TrainRoute>> getAllTrainRoutes();

    @POST("/api/trains/routes/save")
    Call<TrainRoute> createTrainRoute(@Body TrainRoute trainRoute);

    // Get train route by route number
    @GET("/api/trains/routes/number/{routeNumber}")
    Call<TrainRoute> getTrainRouteByNumber(@Path("routeNumber") String routeNumber);

    @PUT("/api/trains/routes/update/{id}")
    Call<TrainRoute> updateRoute(@Path("id") int id, @Body TrainRoute updatedRoute);

    @GET("/api/trains/schedules")
    Call<List<TrainSchedule>> getTrainSchedules(@Query("departureDate") LocalDate departureDate,
                                                @Query("departureCity") String departureCity,
                                                @Query("arrivalCity") String arrivalCity);
    @GET("/api/trains/schedules/{scheduleId}")
    Call<TrainSchedule> getTrainScheduleById(@Path("scheduleId") int scheduleId);

    @POST("/api/trains/schedules/save")
    Call<TrainSchedule> createTrainSchedule(@Body TrainSchedule trainSchedule);

    @PUT("/api/trains/schedules/update/{id}")
    Call<TrainSchedule> updateTrainSchedule(@Path("id") int id, @Body TrainSchedule trainSchedule);
}
