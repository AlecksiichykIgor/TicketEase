package com.example.graduate_work_bachelor_app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.graduate_work_bachelor_app.model.buses.BusSchedule;
import com.example.graduate_work_bachelor_app.model.planes.FlightSchedule;
import com.example.graduate_work_bachelor_app.model.trains.TrainSchedule;
import com.example.graduate_work_bachelor_app.retrofit.BusApi;
import com.example.graduate_work_bachelor_app.retrofit.PlaneApi;
import com.example.graduate_work_bachelor_app.retrofit.RetrofitService;
import com.example.graduate_work_bachelor_app.retrofit.TrainApi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ActivityTransportSchedules extends AppCompatActivity {
    private RetrofitService retrofitService;
    private BusApi busApi;
    private TrainApi trainApi;
    private PlaneApi planeApi;
    private ListView listView;
    private TextView headerTitleCities;
    private TextView headerTitleDate;
    private LinearLayout dateContainer;
    private View lastSelectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport_schedules);

        headerTitleCities = findViewById(R.id.header_title_Cities);
        headerTitleDate = findViewById(R.id.header_title_Date);
        dateContainer = findViewById(R.id.dateContainer);

        Intent intent = getIntent();
        String departureCity = intent.getStringExtra("departure_city");
        String arrivalCity = intent.getStringExtra("arrival_city");
        String dateStr = intent.getStringExtra("date");
        String selectedTransportType = intent.getStringExtra("transport_type");

        String citiesStr = departureCity + " - " + arrivalCity;
        headerTitleCities.setText(citiesStr);

        LocalDate dateDateFormat = LocalDate.parse(dateStr);
        String formattedDate = dateDateFormat.format(DateTimeFormatter.ofPattern("d MMM, EEE", new Locale("uk")));
        headerTitleDate.setText(formattedDate);

        listView = findViewById(R.id.list_View);

        retrofitService = new RetrofitService();
        Retrofit retrofit = retrofitService.getRetrofit();
        if (selectedTransportType.equals("bus")){
            busApi = retrofit.create(BusApi.class);
            getBusSchedules(LocalDate.parse(dateStr), departureCity, arrivalCity);
        } else if (selectedTransportType.equals("plane")) {
            planeApi = retrofit.create(PlaneApi.class);
            getPlaneSchedules(LocalDate.parse(dateStr), departureCity, arrivalCity);
        } else if (selectedTransportType.equals("train")) {
            trainApi = retrofit.create(TrainApi.class);
            getTrainSchedules(LocalDate.parse(dateStr), departureCity, arrivalCity);
        } else {
            Toast.makeText(ActivityTransportSchedules.this, "Вибачте сталася помилка.", Toast.LENGTH_SHORT).show();
        }

        createAndAddDateItems(dateStr);

        TextView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void getBusSchedules(LocalDate date, String departureCity, String arrivalCity) {
        Call<List<BusSchedule>> call = busApi.getBusSchedules(
                date,
                departureCity,
                arrivalCity
        );

        call.enqueue(new Callback<List<BusSchedule>>() {
            @Override
            public void onResponse(Call<List<BusSchedule>> call, Response<List<BusSchedule>> response) {
                if (response.isSuccessful()) {
                    List<BusSchedule> busSchedules = response.body();
                    if (busSchedules != null && !busSchedules.isEmpty()) {
                        BusScheduleAdapter adapter = new BusScheduleAdapter(ActivityTransportSchedules.this, busSchedules);
                        listView.setAdapter(adapter);
                    } else {
                        Toast.makeText(ActivityTransportSchedules.this, "No schedules found", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ActivityTransportSchedules.this, "Failed to get schedules", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<BusSchedule>> call, Throwable t) {
                Toast.makeText(ActivityTransportSchedules.this, "An error occurred", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getTrainSchedules(LocalDate date, String departureCity, String arrivalCity) {
        Call<List<TrainSchedule>> call = trainApi.getTrainSchedules(
                date,
                departureCity,
                arrivalCity
        );

        call.enqueue(new Callback<List<TrainSchedule>>() {
            @Override
            public void onResponse(Call<List<TrainSchedule>> call, Response<List<TrainSchedule>> response) {
                if (response.isSuccessful()) {
                    List<TrainSchedule> trainSchedules = response.body();
                    if (trainSchedules != null && !trainSchedules.isEmpty()) {
                        TrainScheduleAdapter adapter = new TrainScheduleAdapter(ActivityTransportSchedules.this, trainSchedules);
                        listView.setAdapter(adapter);
                    } else {
                        Toast.makeText(ActivityTransportSchedules.this, "No schedules found", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ActivityTransportSchedules.this, "Failed to get schedules", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<TrainSchedule>> call, Throwable t) {
                Toast.makeText(ActivityTransportSchedules.this, "An error occurred", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getPlaneSchedules(LocalDate date, String departureCity, String arrivalCity) {
        Call<List<FlightSchedule>> call = planeApi.getFlightSchedules(
                date,
                departureCity,
                arrivalCity
        );

        call.enqueue(new Callback<List<FlightSchedule>>() {
            @Override
            public void onResponse(Call<List<FlightSchedule>> call, Response<List<FlightSchedule>> response) {
                if (response.isSuccessful()) {
                    List<FlightSchedule> flightSchedules = response.body();
                    if (flightSchedules != null && !flightSchedules.isEmpty()) {
                        FlightScheduleAdapter adapter = new FlightScheduleAdapter(ActivityTransportSchedules.this, flightSchedules);
                        listView.setAdapter(adapter);
                    } else {
                        Toast.makeText(ActivityTransportSchedules.this, "No schedules found", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ActivityTransportSchedules.this, "Failed to get schedules", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<FlightSchedule>> call, Throwable t) {
                Toast.makeText(ActivityTransportSchedules.this, "An error occurred", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void createAndAddDateItems(String dateStr) {
        LocalDate currentDate = LocalDate.parse(dateStr);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(10, 0, 10, 0);

        for (int i = -7; i <= 7; i++) {
            LocalDate date = currentDate.plusDays(i);
            if (!date.isBefore(LocalDate.now())) {
                View dateItemView = createDateItemView(date);

                if (date.equals(currentDate)) {
                    selectDateItem(dateItemView);
                    lastSelectedItem = dateItemView;
                }

                dateContainer.addView(dateItemView, layoutParams);
            }
        }
    }

    @SuppressLint("ResourceType")
    private void selectDateItem(View view) {
        view.setBackgroundResource(R.drawable.rounded_date_item_selected);
        TextView dateTextView = view.findViewById(R.id.dateText);
        TextView dayTextView = view.findViewById(R.id.dayText);
        dateTextView.setTextColor(ContextCompat.getColor(this, android.R.color.white));
        dayTextView.setTextColor(ContextCompat.getColor(this, android.R.color.white));
    }

    private View createDateItemView(final LocalDate date) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View itemView = inflater.inflate(R.layout.date_list_item, null);

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d MMMM", new Locale("uk"));
        String dateStr = date.format(dateFormatter);
        dateStr = capitalizeFirstLetter(dateStr);
        TextView dateTextView = itemView.findViewById(R.id.dateText);
        dateTextView.setText(dateStr);

        TextView dayTextView = itemView.findViewById(R.id.dayText);
        String dayOfWeekStr = date.getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("uk"));
        dayOfWeekStr = capitalizeFirstLetter(dayOfWeekStr);
        dayTextView.setText(dayOfWeekStr);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lastSelectedItem != null) {
                    lastSelectedItem.setBackgroundResource(R.drawable.rounded_date_item);
                    TextView lastDateTextView = lastSelectedItem.findViewById(R.id.dateText);
                    TextView lastDayTextView = lastSelectedItem.findViewById(R.id.dayText);
                    lastDateTextView.setTextColor(ContextCompat.getColor(ActivityTransportSchedules.this, android.R.color.black));
                    lastDayTextView.setTextColor(ContextCompat.getColor(ActivityTransportSchedules.this, android.R.color.black));
                }

                selectDateItem(v);

                lastSelectedItem = v;
            }
        });

        return itemView;
    }

    private String capitalizeFirstLetter(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        return text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
    }

}
