package com.example.graduate_work_bachelor_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.graduate_work_bachelor_app.model.Client;
import com.example.graduate_work_bachelor_app.model.buses.BusSchedule;
import com.example.graduate_work_bachelor_app.model.buses.TicketsBus;
import com.example.graduate_work_bachelor_app.model.planes.FlightSchedule;
import com.example.graduate_work_bachelor_app.model.planes.TicketsPlane;
import com.example.graduate_work_bachelor_app.model.trains.TicketTrain;
import com.example.graduate_work_bachelor_app.model.trains.TrainSchedule;
import com.example.graduate_work_bachelor_app.retrofit.BusApi;
import com.example.graduate_work_bachelor_app.retrofit.PlaneApi;
import com.example.graduate_work_bachelor_app.retrofit.RetrofitService;
import com.example.graduate_work_bachelor_app.retrofit.TicketsBusApi;
import com.example.graduate_work_bachelor_app.retrofit.TicketsPlaneApi;
import com.example.graduate_work_bachelor_app.retrofit.TicketsTrainApi;
import com.example.graduate_work_bachelor_app.retrofit.TrainApi;
import com.example.graduate_work_bachelor_app.retrofit.UserApi;
import com.jakewharton.threetenabp.AndroidThreeTen;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityBuyTickets extends AppCompatActivity {

    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private int scheduleId;
    private String transportType;
    private ArrayList<Integer> selectedSeats;
    private ArrayList<Double> seatPrices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_tickets);
        AndroidThreeTen.init(this);
        firstNameEditText = findViewById(R.id.firstName);
        lastNameEditText = findViewById(R.id.lastName);

        Intent intent = getIntent();
        scheduleId = intent.getIntExtra("scheduleId", -1); // -1 - значение по умолчанию, если идентификатор не найден
        selectedSeats = intent.getIntegerArrayListExtra("selected_seats");
        seatPrices = (ArrayList<Double>) intent.getSerializableExtra("seat_prices");
        Button buttonConfirm = findViewById(R.id.buttonConfirm);
        transportType = intent.getStringExtra("transport_type");
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (scheduleId != -1 && selectedSeats != null && seatPrices != null) {
                    getScheduleDetailsAndSaveTickets(scheduleId, transportType);
                } else {
                    Toast.makeText(ActivityBuyTickets.this, "Ошибка получения данных из Intent", Toast.LENGTH_SHORT).show();
                }
            }
        });
        TextView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void getScheduleDetailsAndSaveTickets(int scheduleId, String transportType) {

        UserApi userApi = new RetrofitService().getRetrofit().create(UserApi.class);

        // Получаем информацию о клиенте
        Call<Client> clientCall = userApi.getUserById(1);
        clientCall.enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                if (response.isSuccessful()) {
                    Client client = response.body();
                    if (transportType.equals("bus")){
                        BusApi busApi = new RetrofitService().getRetrofit().create(BusApi.class);
                        Call<BusSchedule> scheduleCall = busApi.getBusScheduleById(scheduleId);
                        scheduleCall.enqueue(new Callback<BusSchedule>() {
                            @Override
                            public void onResponse(Call<BusSchedule> call, Response<BusSchedule> response) {
                                if (response.isSuccessful()) {
                                    BusSchedule busSchedule = response.body();
                                    if (busSchedule != null && client != null) {

                                        Intent intent = getIntent();
                                        ArrayList<Integer> selectedSeats = intent.getIntegerArrayListExtra("selected_seats");
                                        ArrayList<Double> seatPrices = (ArrayList<Double>) intent.getSerializableExtra("seat_prices");
                                        for (int i = 0; i < selectedSeats.size(); i++) {
                                            TicketsBus ticket = new TicketsBus();
                                            ticket.setBusSchedule(busSchedule);
                                            ticket.setPassengerFirstName(firstNameEditText.getText().toString().trim());
                                            ticket.setPassengerLastName(lastNameEditText.getText().toString().trim());
                                            ticket.setDatePurchase(LocalDate.now());
                                            ticket.setTimePurchase(LocalDateTime.now());
                                            ticket.setTicketPrice(seatPrices.get(i));
                                            ticket.setSeatNumber(selectedSeats.get(i));
                                            ticket.setStatus("Active");
                                            ticket.setClient(client);

                                            saveBusTicket(ticket);
                                        }
                                    } else {
                                        Toast.makeText(ActivityBuyTickets.this, "Расписание не найдено или клиент не найден", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(ActivityBuyTickets.this, "Ошибка получения данных расписания", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<BusSchedule> call, Throwable t) {
                                Toast.makeText(ActivityBuyTickets.this, "Ошибка сети: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    else if (transportType.equals("train")) {
                        TrainApi trainApi = new RetrofitService().getRetrofit().create(TrainApi.class);
                        Call<TrainSchedule> scheduleCall = trainApi.getTrainScheduleById(scheduleId);
                        scheduleCall.enqueue(new Callback<TrainSchedule>() {
                            @Override
                            public void onResponse(Call<TrainSchedule> call, Response<TrainSchedule> response) {
                                if (response.isSuccessful()) {
                                    TrainSchedule trainSchedule = response.body();
                                    if (trainSchedule != null && client != null) {

                                        Intent intent = getIntent();
                                        ArrayList<Integer> selectedSeats = intent.getIntegerArrayListExtra("selected_seats");
                                        ArrayList<Double> seatPrices = (ArrayList<Double>) intent.getSerializableExtra("seat_prices");
                                        for (int i = 0; i < selectedSeats.size(); i++) {
                                            TicketTrain ticket = new TicketTrain();
                                            ticket.setTrainSchedule(trainSchedule);
                                            ticket.setPassengerFirstName(firstNameEditText.getText().toString().trim());
                                            ticket.setPassengerLastName(lastNameEditText.getText().toString().trim());
                                            ticket.setDatePurchase(LocalDate.now());
                                            ticket.setTimePurchase(LocalDateTime.now());
                                            ticket.setPrice(seatPrices.get(i));
                                            ticket.setSeatNumber(selectedSeats.get(i));
                                            ticket.setWagonNumber(1);
                                            ticket.setStatus("Active");
                                            ticket.setClient(client);

                                            saveTrainTicket(ticket);
                                        }
                                    } else {
                                        Toast.makeText(ActivityBuyTickets.this, "Расписание не найдено или клиент не найден", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(ActivityBuyTickets.this, "Ошибка получения данных расписания", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<TrainSchedule> call, Throwable t) {
                                Toast.makeText(ActivityBuyTickets.this, "Ошибка сети: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    else if (transportType.equals("plane")){
                        PlaneApi planeApi = new RetrofitService().getRetrofit().create(PlaneApi.class);
                        Call<FlightSchedule> scheduleCall = planeApi.getFlightScheduleById(scheduleId);
                        scheduleCall.enqueue(new Callback<FlightSchedule>() {
                            @Override
                            public void onResponse(Call<FlightSchedule> call, Response<FlightSchedule> response) {
                                if (response.isSuccessful()) {
                                    FlightSchedule flightSchedule = response.body();
                                    if (flightSchedule != null && client != null) {

                                        Intent intent = getIntent();
                                        ArrayList<Integer> selectedSeats = intent.getIntegerArrayListExtra("selected_seats");
                                        ArrayList<Double> seatPrices = (ArrayList<Double>) intent.getSerializableExtra("seat_prices");
                                        for (int i = 0; i < selectedSeats.size(); i++) {
                                            TicketsPlane ticketsPlane = new TicketsPlane();
                                            ticketsPlane.setFlightSchedule(flightSchedule);
                                            ticketsPlane.setPassengerFirstName(firstNameEditText.getText().toString().trim());
                                            ticketsPlane.setPassengerLastName(lastNameEditText.getText().toString().trim());
                                            ticketsPlane.setDatePurchase(LocalDate.now());
                                            ticketsPlane.setTimePurchase(LocalDateTime.now());
                                            ticketsPlane.setTicketPrice(seatPrices.get(i));
                                            ticketsPlane.setSeatNumber(String.valueOf(selectedSeats.get(i)));
                                            ticketsPlane.setStatus("Active");
                                            ticketsPlane.setClient(client);

                                            savePLaneTicket(ticketsPlane);
                                        }
                                    } else {
                                        Toast.makeText(ActivityBuyTickets.this, "Расписание не найдено или клиент не найден", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(ActivityBuyTickets.this, "Ошибка получения данных расписания", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<FlightSchedule> call, Throwable t) {
                                Toast.makeText(ActivityBuyTickets.this, "Ошибка сети: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                } else {
                    Toast.makeText(ActivityBuyTickets.this, "Ошибка получения данных клиента", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                Toast.makeText(ActivityBuyTickets.this, "Ошибка сети: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveBusTicket(TicketsBus ticket) {
        TicketsBusApi api = new RetrofitService().getRetrofit().create(TicketsBusApi.class);

        Call<Void> call = api.saveTicket(ticket);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(ActivityBuyTickets.this, "Билет успешно сохранен", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ActivityBuyTickets.this, "Квиток успішно куплено", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(ActivityBuyTickets.this, "Ошибка сети: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void saveTrainTicket(TicketTrain ticketTrain) {
        TicketsTrainApi api = new RetrofitService().getRetrofit().create(TicketsTrainApi.class);

        Call<Void> call = api.saveTicket(ticketTrain);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(ActivityBuyTickets.this, "Билет успешно сохранен", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ActivityBuyTickets.this, "Квиток успішно куплено", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(ActivityBuyTickets.this, "Ошибка сети: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void savePLaneTicket(TicketsPlane ticket) {
        TicketsPlaneApi api = new RetrofitService().getRetrofit().create(TicketsPlaneApi.class);

        Call<Void> call = api.saveTicket(ticket);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(ActivityBuyTickets.this, "Билет успешно сохранен", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ActivityBuyTickets.this, "Квиток успішно куплено", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(ActivityBuyTickets.this, "Ошибка сети: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
