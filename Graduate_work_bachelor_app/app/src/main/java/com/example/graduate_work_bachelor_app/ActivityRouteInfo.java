package com.example.graduate_work_bachelor_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.graduate_work_bachelor_app.model.buses.BusRoute;
import com.example.graduate_work_bachelor_app.model.planes.AirplaneRoute;
import com.example.graduate_work_bachelor_app.model.trains.TrainRoute;
import com.example.graduate_work_bachelor_app.retrofit.BusApi;
import com.example.graduate_work_bachelor_app.retrofit.PlaneApi;
import com.example.graduate_work_bachelor_app.retrofit.RetrofitService;
import com.example.graduate_work_bachelor_app.retrofit.TrainApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityRouteInfo extends AppCompatActivity {
    private EditText departureCity, departureStation, arrivalCity,arrivalStation,
            textNumber, seatsAvailable, ticketPrice;
    private TextView titleActivity;
    private Button buttonAdd_Update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_info);
        buttonAdd_Update = findViewById(R.id.buttonAdd_UpdateRoute);
        String status= getIntent().getStringExtra("STATUS");
        departureCity = findViewById(R.id.editTextDepartureCity);
        departureStation = findViewById(R.id.editTextDepartureStation);
        arrivalCity = findViewById(R.id.editTextArrivalCity);
        arrivalStation = findViewById(R.id.editTextArrivalStation);
        textNumber = findViewById(R.id.editTextNumber);
        seatsAvailable = findViewById(R.id.editTextSeatsAvailable);
        ticketPrice = findViewById(R.id.editTextTicketPrice);
        titleActivity = findViewById(R.id.title);
        String transportType = getIntent().getStringExtra("TRANSPORT_TYPE");
        int id = getIntent().getIntExtra("ROUTE_ID", -1);
        if (status.equals("update")){
            buttonAdd_Update.setText("Оновити");
            if (transportType.equals("bus")){
                BusApi busApi = new RetrofitService().getRetrofit().create(BusApi.class);
                Call<BusRoute> busRouteCall = busApi.getBusRouteById(id);
                busRouteCall.enqueue(new Callback<BusRoute>() {
                    @Override
                    public void onResponse(Call<BusRoute> call, Response<BusRoute> response) {
                        if (response.isSuccessful()) {
                            BusRoute busRoute = response.body();
                            departureCity.setText(busRoute.getDepartureCity());
                            departureStation.setText(busRoute.getDepartureStation());
                            arrivalCity.setText(busRoute.getArrivalCity());
                            arrivalStation.setText(busRoute.getArrivalStation());
                            textNumber.setText(busRoute.getRouteNumber());
                            seatsAvailable.setText(String.valueOf(busRoute.getCountSeats()));
                            ticketPrice.setText(String.valueOf(busRoute.getPrice()));
                            titleActivity.setText(busRoute.getDepartureCity() + " - " + busRoute.getArrivalCity());
                        }else {
                            Toast.makeText(ActivityRouteInfo.this, "Поимлка отримання даних маршрута", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<BusRoute> call, Throwable t) {

                    }
                });
                buttonAdd_Update.setOnClickListener(new android.view.View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        BusRoute busRoute = new BusRoute();
                        busRoute.setDepartureCity(departureCity.getText().toString());
                        busRoute.setDepartureStation(departureStation.getText().toString());
                        busRoute.setArrivalCity(arrivalCity.getText().toString());
                        busRoute.setArrivalStation(arrivalStation.getText().toString());
                        busRoute.setRouteNumber(textNumber.getText().toString());
                        busRoute.setCountSeats(Integer.parseInt(String.valueOf(seatsAvailable.getText())));
                        busRoute.setPrice(Double.parseDouble(ticketPrice.getText().toString()));
                        Call<BusRoute> busRouteCallCreate = busApi.updateBusRoute(id, busRoute);
                        busRouteCallCreate.enqueue(new Callback<BusRoute>() {
                            @Override
                            public void onResponse(Call<BusRoute> call, Response<BusRoute> response) {
                                departureCity.setText("");
                                departureStation.setText("");
                                arrivalCity.setText("");
                                arrivalStation.setText("");
                                textNumber.setText("");
                                seatsAvailable.setText("");
                                ticketPrice.setText("");
                                Toast.makeText(ActivityRouteInfo.this, "Маршрут успішно оновлено!", Toast.LENGTH_SHORT).show();
                                finish();
                            }

                            @Override
                            public void onFailure(Call<BusRoute> call, Throwable t) {

                            }
                        });
                    }
                });
            }
            else if (transportType.equals("plane")) {
                PlaneApi planeApi = new RetrofitService().getRetrofit().create(PlaneApi.class);
                Call<AirplaneRoute> airplaneRouteCall = planeApi.getAirplaneRouteById(id);
                airplaneRouteCall.enqueue(new Callback<AirplaneRoute>() {
                    @Override
                    public void onResponse(Call<AirplaneRoute> call, Response<AirplaneRoute> response) {
                        if (response.isSuccessful()) {
                            AirplaneRoute busRoute = response.body();
                            departureCity.setText(busRoute.getDepartureCity());
                            departureStation.setText(busRoute.getDepartureAirport());
                            arrivalCity.setText(busRoute.getArrivalCity());
                            arrivalStation.setText(busRoute.getArrivalAirport());
                            textNumber.setText(busRoute.getRouteNumber());
                            seatsAvailable.setText(String.valueOf(busRoute.getCountSeats()));
                            ticketPrice.setText(String.valueOf(busRoute.getPrice()));
                            titleActivity.setText(busRoute.getDepartureCity() + " - " + busRoute.getArrivalCity());
                        }else {
                            Toast.makeText(ActivityRouteInfo.this, "Поимлка отримання даних маршрута", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<AirplaneRoute> call, Throwable t) {

                    }
                });
                buttonAdd_Update.setOnClickListener(new android.view.View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AirplaneRoute airplaneRoute = new AirplaneRoute();
                        airplaneRoute.setDepartureCity(departureCity.getText().toString());
                        airplaneRoute.setDepartureAirport(departureStation.getText().toString());
                        airplaneRoute.setArrivalCity(arrivalCity.getText().toString());
                        airplaneRoute.setArrivalAirport(arrivalStation.getText().toString());
                        airplaneRoute.setRouteNumber(textNumber.getText().toString());
                        airplaneRoute.setCountSeats(Integer.parseInt(String.valueOf(seatsAvailable.getText())));
                        airplaneRoute.setPrice(Double.parseDouble(ticketPrice.getText().toString()));
                        Call<AirplaneRoute> airplaneRouteCallCreate = planeApi.updateAirplaneRoute(id, airplaneRoute);
                        airplaneRouteCallCreate.enqueue(new Callback<AirplaneRoute>() {
                            @Override
                            public void onResponse(Call<AirplaneRoute> call, Response<AirplaneRoute> response) {
                                departureCity.setText("");
                                departureStation.setText("");
                                arrivalCity.setText("");
                                arrivalStation.setText("");
                                textNumber.setText("");
                                seatsAvailable.setText("");
                                ticketPrice.setText("");
                                Toast.makeText(ActivityRouteInfo.this, "Маршрут успішно оновлено!", Toast.LENGTH_SHORT).show();
                                finish();
                            }

                            @Override
                            public void onFailure(Call<AirplaneRoute> call, Throwable t) {

                            }
                        });
                    }
                });
            }
            else if (transportType.equals("train")) {
                TrainApi trainApi = new RetrofitService().getRetrofit().create(TrainApi.class);
                Call<TrainRoute> trainRouteCall = trainApi.getTrainRouteById(id);
                trainRouteCall.enqueue(new Callback<TrainRoute>() {
                    @Override
                    public void onResponse(Call<TrainRoute> call, Response<TrainRoute> response) {
                        if (response.isSuccessful()) {
                            TrainRoute trainRoute = response.body();
                            departureCity.setText(trainRoute.getDepartureCity());
                            departureStation.setText(trainRoute.getDepartureStation());
                            arrivalCity.setText(trainRoute.getArrivalCity());
                            arrivalStation.setText(trainRoute.getArrivalStation());
                            textNumber.setText(trainRoute.getRouteNumber());
                            seatsAvailable.setText(String.valueOf(trainRoute.getCountWagon()));
                            ticketPrice.setText(String.valueOf(trainRoute.getPrice()));
                            titleActivity.setText(trainRoute.getDepartureCity() + " - " + trainRoute.getArrivalCity());
                        }else {
                            Toast.makeText(ActivityRouteInfo.this, "Поимлка отримання даних маршрута", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<TrainRoute> call, Throwable t) {

                    }
                });
                buttonAdd_Update.setOnClickListener(new android.view.View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TrainRoute trainRoute = new TrainRoute();
                        trainRoute.setDepartureCity(departureCity.getText().toString());
                        trainRoute.setDepartureStation(departureStation.getText().toString());
                        trainRoute.setArrivalCity(arrivalCity.getText().toString());
                        trainRoute.setArrivalStation(arrivalStation.getText().toString());
                        trainRoute.setRouteNumber(textNumber.getText().toString());
                        trainRoute.setCountSeats(Integer.parseInt(String.valueOf(seatsAvailable.getText())));
                        trainRoute.setPrice(Double.parseDouble(ticketPrice.getText().toString()));
                        Call<TrainRoute> busRouteCallCreate = trainApi.updateRoute(id, trainRoute);
                        busRouteCallCreate.enqueue(new Callback<TrainRoute>() {
                            @Override
                            public void onResponse(Call<TrainRoute> call, Response<TrainRoute> response) {
                                departureCity.setText("");
                                departureStation.setText("");
                                arrivalCity.setText("");
                                arrivalStation.setText("");
                                textNumber.setText("");
                                seatsAvailable.setText("");
                                ticketPrice.setText("");
                                Toast.makeText(ActivityRouteInfo.this, "Маршрут успішно оновлено!", Toast.LENGTH_SHORT).show();
                                finish();
                            }

                            @Override
                            public void onFailure(Call<TrainRoute> call, Throwable t) {

                            }
                        });
                    }
                });
            }
        } else {
            if (transportType.equals("bus")){
                buttonAdd_Update.setOnClickListener(new android.view.View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        BusApi busApi = new RetrofitService().getRetrofit().create(BusApi.class);
                        BusRoute busRoute = new BusRoute();
                        busRoute.setDepartureCity(departureCity.getText().toString());
                        busRoute.setDepartureStation(departureStation.getText().toString());
                        busRoute.setArrivalCity(arrivalCity.getText().toString());
                        busRoute.setArrivalStation(arrivalStation.getText().toString());
                        busRoute.setRouteNumber(textNumber.getText().toString());
                        busRoute.setCountSeats(Integer.parseInt(String.valueOf(seatsAvailable.getText())));
                        busRoute.setPrice(Double.parseDouble(ticketPrice.getText().toString()));
                        buttonAdd_Update.setText("Додати");
                        Call<BusRoute> busRouteCallCreate = busApi.createBusRoute(busRoute);
                        busRouteCallCreate.enqueue(new Callback<BusRoute>() {
                            @Override
                            public void onResponse(Call<BusRoute> call, Response<BusRoute> response) {
                                departureCity.setText("");
                                departureStation.setText("");
                                arrivalCity.setText("");
                                arrivalStation.setText("");
                                textNumber.setText("");
                                seatsAvailable.setText("");
                                ticketPrice.setText("");
                                Toast.makeText(ActivityRouteInfo.this, "Маршрут успішно додано!", Toast.LENGTH_SHORT).show();
                                finish();
                            }

                            @Override
                            public void onFailure(Call<BusRoute> call, Throwable t) {
                                titleActivity.setText(t.getMessage());
                            }
                        });
                    }
                });
            }
            else if (transportType.equals("plane")) {
                buttonAdd_Update.setOnClickListener(new android.view.View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PlaneApi planeApi = new RetrofitService().getRetrofit().create(PlaneApi.class);
                        AirplaneRoute airplaneRoute = new AirplaneRoute();
                        airplaneRoute.setDepartureCity(departureCity.getText().toString());
                        airplaneRoute.setDepartureAirport(departureStation.getText().toString());
                        airplaneRoute.setArrivalCity(arrivalCity.getText().toString());
                        airplaneRoute.setArrivalAirport(arrivalStation.getText().toString());
                        airplaneRoute.setRouteNumber(textNumber.getText().toString());
                        airplaneRoute.setCountSeats(Integer.parseInt(String.valueOf(seatsAvailable.getText())));
                        airplaneRoute.setPrice(Double.parseDouble(ticketPrice.getText().toString()));
                        buttonAdd_Update.setText("Додати");
                        Call<AirplaneRoute> airplaneRouteCall = planeApi.createAirplaneRoute(airplaneRoute);
                        airplaneRouteCall.enqueue(new Callback<AirplaneRoute>() {
                            @Override
                            public void onResponse(Call<AirplaneRoute> call, Response<AirplaneRoute> response) {
                                departureCity.setText("");
                                departureStation.setText("");
                                arrivalCity.setText("");
                                arrivalStation.setText("");
                                textNumber.setText("");
                                seatsAvailable.setText("");
                                ticketPrice.setText("");
                                Toast.makeText(ActivityRouteInfo.this, "Маршрут успішно додано!", Toast.LENGTH_SHORT).show();
                                finish();
                            }

                            @Override
                            public void onFailure(Call<AirplaneRoute> call, Throwable t) {

                            }
                        });
                    }
                });
            }
            else if (transportType.equals("train")) {
                buttonAdd_Update.setOnClickListener(new android.view.View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TrainApi trainApi = new RetrofitService().getRetrofit().create(TrainApi.class);
                        TrainRoute trainRoute = new TrainRoute();
                        trainRoute.setDepartureCity(departureCity.getText().toString());
                        trainRoute.setDepartureStation(departureStation.getText().toString());
                        trainRoute.setArrivalCity(arrivalCity.getText().toString());
                        trainRoute.setArrivalStation(arrivalStation.getText().toString());
                        trainRoute.setRouteNumber(textNumber.getText().toString());
                        trainRoute.setCountSeats(Integer.parseInt(String.valueOf(seatsAvailable.getText())));
                        trainRoute.setPrice(Double.parseDouble(ticketPrice.getText().toString()));
                        buttonAdd_Update.setText("Додати");
                        Call<TrainRoute> trainRouteCall = trainApi.createTrainRoute(trainRoute);
                        trainRouteCall.enqueue(new Callback<TrainRoute>() {
                            @Override
                            public void onResponse(Call<TrainRoute> call, Response<TrainRoute> response) {
                                departureCity.setText("");
                                departureStation.setText("");
                                arrivalCity.setText("");
                                arrivalStation.setText("");
                                textNumber.setText("");
                                seatsAvailable.setText("");
                                ticketPrice.setText("");
                                Toast.makeText(ActivityRouteInfo.this, "Маршрут успішно додано!", Toast.LENGTH_SHORT).show();
                                finish();
                            }

                            @Override
                            public void onFailure(Call<TrainRoute> call, Throwable t) {
                            }
                        });
                    }
                });
            }
        }
        Button buttonCancel = findViewById(R.id.buttonCancel);
        buttonCancel.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}