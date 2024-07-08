package com.example.graduate_work_bachelor_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.graduate_work_bachelor_app.model.buses.BusRoute;
import com.example.graduate_work_bachelor_app.model.planes.AirplaneRoute;
import com.example.graduate_work_bachelor_app.model.trains.TrainRoute;
import com.example.graduate_work_bachelor_app.retrofit.BusApi;
import com.example.graduate_work_bachelor_app.retrofit.PlaneApi;
import com.example.graduate_work_bachelor_app.retrofit.RetrofitService;
import com.example.graduate_work_bachelor_app.retrofit.TrainApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ActivityRoute extends AppCompatActivity {
    private RetrofitService retrofitService;
    private ListView listView;
    private RouteBusAdapter busAdapter;
    private RouteTrainAdapter trainAdapter;
    private RoutePlaneAdapter planeAdapter;
    private List<BusRoute> busRouteList;
    private List<TrainRoute> trainRouteList;
    private List<AirplaneRoute> airplaneRouteList;
    private String transportType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);

        listView = findViewById(R.id.busRouteList);
        busRouteList = new ArrayList<>();
        trainRouteList = new ArrayList<>();
        airplaneRouteList = new ArrayList<>();

        busAdapter = new RouteBusAdapter(this, busRouteList);
        trainAdapter = new RouteTrainAdapter(this, trainRouteList);
        planeAdapter = new RoutePlaneAdapter(this, airplaneRouteList);

        retrofitService = new RetrofitService();

        TextView headerTitle = findViewById(R.id.header_title);
        transportType = getIntent().getStringExtra("TRANSPORT_TYPE");
        if (transportType.equals("bus")){
            headerTitle.setText("Список маршрутів Автобусів");
            listView.setAdapter(busAdapter);
            getListBusRoutes();
        } else if (transportType.equals("train")) {
            headerTitle.setText("Список маршрутів Поїздів");
            getListTrainRoutes();
            listView.setAdapter(trainAdapter);
        } else if (transportType.equals("plane")) {
            headerTitle.setText("Список маршрутів Літаків");
            getListPlaneRoutes();
            listView.setAdapter(planeAdapter);
        }

        TextView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        TextView add = findViewById(R.id.addButton);
        add.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityRoute.this, ActivityRouteInfo.class);
                intent.putExtra("STATUS", "add");
                intent.putExtra("TRANSPORT_TYPE",getIntent().getStringExtra("TRANSPORT_TYPE"));
                startActivity(intent);
            }
        });
    }
    private void getListBusRoutes() {
        Retrofit retrofit = retrofitService.getRetrofit();
        BusApi busApi = retrofit.create(BusApi.class);
        Call<List<BusRoute>> call = busApi.getAllBusRoutes();

        call.enqueue(new Callback<List<BusRoute>>() {
            @Override
            public void onResponse(Call<List<BusRoute>> call, Response<List<BusRoute>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    busRouteList.clear();
                    busRouteList.addAll(response.body());
                    busAdapter.notifyDataSetChanged();
                } else {
                    Log.e("API_ERROR", "Response was unsuccessful or body was null");
                }
            }

            @Override
            public void onFailure(Call<List<BusRoute>> call, Throwable t) {
                Log.e("API_ERROR", "Failed to fetch data: " + t.getMessage());
            }
        });
    }
    private void getListTrainRoutes() {
        Retrofit retrofit = retrofitService.getRetrofit();
        TrainApi trainApi = retrofit.create(TrainApi.class);
        Call<List<TrainRoute>> call = trainApi.getAllTrainRoutes();

        call.enqueue(new Callback<List<TrainRoute>>() {
            @Override
            public void onResponse(Call<List<TrainRoute>> call, Response<List<TrainRoute>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    trainRouteList.clear();
                    trainRouteList.addAll(response.body());
                    trainAdapter.notifyDataSetChanged();
                } else {
                    Log.e("API_ERROR", "Response was unsuccessful or body was null");
                }
            }

            @Override
            public void onFailure(Call<List<TrainRoute>> call, Throwable t) {
                Log.e("API_ERROR", "Failed to fetch data: " + t.getMessage());
            }
        });
    }
    private void getListPlaneRoutes() {
        Retrofit retrofit = retrofitService.getRetrofit();
        PlaneApi planeApi = retrofit.create(PlaneApi.class);
        Call<List<AirplaneRoute>> call = planeApi.getAllAirplaneRoutes();

        call.enqueue(new Callback<List<AirplaneRoute>>() {
            @Override
            public void onResponse(Call<List<AirplaneRoute>> call, Response<List<AirplaneRoute>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    airplaneRouteList.clear();
                    airplaneRouteList.addAll(response.body());
                    planeAdapter.notifyDataSetChanged();
                } else {
                    Log.e("API_ERROR", "Response was unsuccessful or body was null");
                }
            }

            @Override
            public void onFailure(Call<List<AirplaneRoute>> call, Throwable t) {
                Log.e("API_ERROR", "Failed to fetch data: " + t.getMessage());
            }
        });
    }
}