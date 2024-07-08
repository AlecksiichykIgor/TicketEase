package com.example.graduate_work_bachelor_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.graduate_work_bachelor_app.model.buses.TicketsBus;
import com.example.graduate_work_bachelor_app.retrofit.RetrofitService;
import com.example.graduate_work_bachelor_app.retrofit.TicketsBusApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ActivityMyTickets extends AppCompatActivity {
    private RetrofitService retrofitService;
    private TicketsBusApi ticketsBusApi;
    private ListView listView;
    private TicketAdapter adapter;
    private List<TicketsBus> ticketList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tickets);

        listView = findViewById(R.id.listView);
        ticketList = new ArrayList<>(); // Инициализация ticketList

        // Инициализация адаптера и установка для ListView
        adapter = new TicketAdapter(this, ticketList);
        listView.setAdapter(adapter);

        retrofitService = new RetrofitService();
        Retrofit retrofit = retrofitService.getRetrofit();
        ticketsBusApi = retrofit.create(TicketsBusApi.class);

        int clientId = 1;
        fetchTickets(clientId);
        TextView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Этот метод переходит на предыдущую активность
            }
        });
    }
//    private File createPdfReport(List<List<String>> data) {
//
//        try {
//            System.out.println("Error: ");
//        } catch (Exception e) {
//            System.out.println("Error: " + e);
//            return null;
//        }
//    }

    private void fetchTickets(int clientId) {
        Call<List<TicketsBus>> call = ticketsBusApi.getActiveTicketsByClientId(clientId);

        call.enqueue(new Callback<List<TicketsBus>>() {
            @Override
            public void onResponse(Call<List<TicketsBus>> call, Response<List<TicketsBus>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ticketList.clear();
                    ticketList.addAll(response.body());
                    adapter.notifyDataSetChanged();
                } else {
                    Log.e("API_ERROR", "Response was unsuccessful or body was null");
                }
            }

            @Override
            public void onFailure(Call<List<TicketsBus>> call, Throwable t) {
                Log.e("API_ERROR", "Failed to fetch data: " + t.getMessage());
            }
        });
    }
}
