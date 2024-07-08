package com.example.graduate_work_bachelor_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.graduate_work_bachelor_app.model.buses.BusRoute;
import com.example.graduate_work_bachelor_app.model.buses.TicketsBus;
import com.example.graduate_work_bachelor_app.model.planes.AirplaneRoute;
import com.example.graduate_work_bachelor_app.model.planes.TicketsPlane;
import com.example.graduate_work_bachelor_app.model.trains.TicketTrain;
import com.example.graduate_work_bachelor_app.model.trains.TrainRoute;
import com.example.graduate_work_bachelor_app.retrofit.BusApi;
import com.example.graduate_work_bachelor_app.retrofit.RetrofitService;
import com.example.graduate_work_bachelor_app.retrofit.TicketsBusApi;
import com.example.graduate_work_bachelor_app.retrofit.TicketsPlaneApi;
import com.example.graduate_work_bachelor_app.retrofit.TicketsTrainApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TicketsAdminList extends AppCompatActivity {
    private Button btnBuses, btnPlanes, btnTrains;
    private ListView listViewTickets;
    private TicketAdminAdapter adapter;
    private List<Ticket> tickets;
//    private List<TicketsBus> ticketsBuses;
    private List<TicketTrain> ticketTrains;
    private List<TicketsPlane> ticketsPlanes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tickets_admin_list);

        btnBuses = findViewById(R.id.btnBuses);
        btnPlanes = findViewById(R.id.btnPlanes);
        btnTrains = findViewById(R.id.btnTrains);
        listViewTickets = findViewById(R.id.listViewTickets);
        ImageButton btnCreatePDF = findViewById(R.id.btnCreatePDF);

//        ticketsBuses = new ArrayList<>();
        ticketTrains = new ArrayList<>();
        ticketsPlanes = new ArrayList<>();
        tickets = new ArrayList<>();
        adapter = new TicketAdminAdapter(this, tickets);
        listViewTickets.setAdapter(adapter);

        btnBuses.setOnClickListener(v -> loadTickets("bus"));
        btnPlanes.setOnClickListener(v -> loadTickets("plane"));
        btnTrains.setOnClickListener(v -> loadTickets("train"));

        btnCreatePDF.setOnClickListener(v -> showDatePickerDialog());
    }

    private void loadTickets(String transportType) {
        tickets.clear();
        RetrofitService retrofitService = new RetrofitService();
        if (transportType.equals("bus")) {
            List<TicketsBus> ticketsBusesList = new ArrayList<>();
            Retrofit retrofit = retrofitService.getRetrofit();
            TicketsBusApi ticketsBusApi = retrofit.create(TicketsBusApi.class);
            Call<List<TicketsBus>> call = ticketsBusApi.getActiveTickets();

            call.enqueue(new Callback<List<TicketsBus>>() {
                @Override
                public void onResponse(Call<List<TicketsBus>> call, Response<List<TicketsBus>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        ticketsBusesList.clear();
                        ticketsBusesList.addAll(response.body());
                        for (TicketsBus ticketsBus : ticketsBusesList) {
                            Ticket newTicket = new Ticket();
                            newTicket.setTicketId(ticketsBus.getTicketId());
                            newTicket.setDatePurchase(String.valueOf(ticketsBus.getDatePurchase()));
                            newTicket.setTicketPrice(ticketsBus.getTicketPrice());
                            tickets.add(newTicket);
                        }
                        adapter.notifyDataSetChanged(); // Обновляем адаптер после добавления всех данных
                    } else {
                        Log.e("API_ERROR", "Response was unsuccessful or body was null");
                    }
                }

                @Override
                public void onFailure(Call<List<TicketsBus>> call, Throwable t) {
                    Log.e("API_ERROR", "Failed to fetch data: " + t.getMessage());
                }
            });

        } else if (transportType.equals("train")) {
            Retrofit retrofit = retrofitService.getRetrofit();
            TicketsTrainApi ticketsTrainApi = retrofit.create(TicketsTrainApi.class);
            Call<List<TicketTrain>> call = ticketsTrainApi.getActiveTickets();

            call.enqueue(new Callback<List<TicketTrain>>() {
                @Override
                public void onResponse(Call<List<TicketTrain>> call, Response<List<TicketTrain>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        ticketTrains.clear();
                        ticketTrains.addAll(response.body());
                        for (TicketTrain ticketTrain : ticketTrains) {
                            Ticket ticket = new Ticket();
                            ticket.setTicketId(ticketTrain.getTicketId());
                            ticket.setDatePurchase(String.valueOf(ticketTrain.getDatePurchase()));
                            ticket.setTicketPrice(ticketTrain.getPrice());
                            tickets.add(ticket);
                        }
                        adapter.notifyDataSetChanged(); // Обновляем адаптер после добавления всех данных
                    } else {
                        Log.e("API_ERROR", "Response was unsuccessful or body was null");
                    }
                }

                @Override
                public void onFailure(Call<List<TicketTrain>> call, Throwable t) {
                    Log.e("API_ERROR", "Failed to fetch data: " + t.getMessage());
                }
            });

        } else if (transportType.equals("plane")) {
            Retrofit retrofit = retrofitService.getRetrofit();
            TicketsPlaneApi ticketsPlaneApi = retrofit.create(TicketsPlaneApi.class);
            Call<List<TicketsPlane>> call = ticketsPlaneApi.getActiveTickets();

            call.enqueue(new Callback<List<TicketsPlane>>() {
                @Override
                public void onResponse(Call<List<TicketsPlane>> call, Response<List<TicketsPlane>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        ticketsPlanes.clear();
                        ticketsPlanes.addAll(response.body());
                        for (TicketsPlane ticketsPlane : ticketsPlanes) {
                            Ticket ticket = new Ticket();
                            ticket.setTicketId(ticketsPlane.getTicketId());
                            ticket.setDatePurchase(String.valueOf(ticketsPlane.getDatePurchase()));
                            ticket.setTicketPrice(ticketsPlane.getTicketPrice());
                            tickets.add(ticket);
                        }
                        adapter.notifyDataSetChanged(); // Обновляем адаптер после добавления всех данных
                    } else {
                        Log.e("API_ERROR", "Response was unsuccessful or body was null");
                    }
                }

                @Override
                public void onFailure(Call<List<TicketsPlane>> call, Throwable t) {
                    Log.e("API_ERROR", "Failed to fetch data: " + t.getMessage());
                }
            });
        }

        Toast.makeText(this, "Завантажено квитки для " + transportType, Toast.LENGTH_SHORT).show();
    }


    private void showDatePickerDialog() {
        View view = getLayoutInflater().inflate(R.layout.date_picker_dialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);

        view.findViewById(R.id.btnCreatePDF).setOnClickListener(v -> {
            // Handle PDF creation
            Toast.makeText(this, "PDF створено", Toast.LENGTH_SHORT).show();
        });

        builder.create().show();
    }
}