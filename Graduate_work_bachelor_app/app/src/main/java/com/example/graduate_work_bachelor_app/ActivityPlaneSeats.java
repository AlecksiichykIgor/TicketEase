package com.example.graduate_work_bachelor_app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.graduate_work_bachelor_app.retrofit.RetrofitService;
import com.example.graduate_work_bachelor_app.retrofit.TicketsBusApi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import retrofit2.Retrofit;

public class ActivityPlaneSeats extends Activity {
    private HashMap<Integer, Double> selectedSeatsWithPrices = new HashMap<>();
    private Set<Integer> occupiedSeats = new HashSet<>();
    private RetrofitService retrofitService;
    private TicketsBusApi ticketsBusApi;
    private TextView headerTitleCities;
    private TextView headerTitleDate;
    private EditText priceSeats;
    private double priceS;
    private LinearLayout bottomMenuTicket;

    private int selectedCount = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plane_seats);

        LinearLayout firstLeftRows = findViewById(R.id.first_left_rows);
        LinearLayout secondLeftRows = findViewById(R.id.second_left_rows);
        LinearLayout firstMiddleRows = findViewById(R.id.first_middle_rows);
        LinearLayout secondMiddleRows = findViewById(R.id.second_middle_rows);
        LinearLayout thirdMiddleRows = findViewById(R.id.third_middle_rows);
        LinearLayout firstRightRows = findViewById(R.id.first_right_rows);
        LinearLayout secondRightRows = findViewById(R.id.second_right_rows);
        bottomMenuTicket = findViewById(R.id.bottomMenuTicket);

        List<Integer> greyS = new ArrayList<>();
        greyS.add(1);
        greyS.add(4);
        greyS.add(5);
        greyS.add(16);

        createTextViews(firstLeftRows, 12, 1, greyS, 7, "A");
        createTextViews(secondLeftRows, 12, 2, greyS, 7, "B");
        createTextViews(firstMiddleRows, 12, 3, greyS, 7, "C");
        createTextViews(secondMiddleRows, 12, 4, greyS, 7, "D");
        createTextViews(thirdMiddleRows, 12, 5, greyS, 7, "E");
        createTextViews(firstRightRows, 12, 6, greyS, 7, "F");
        createTextViews(secondRightRows, 12, 7, greyS, 7, "G");

        headerTitleCities = findViewById(R.id.header_title_Cities);
        headerTitleDate = findViewById(R.id.header_title_Date);
        Intent intent = getIntent();
        String departureCity = intent.getStringExtra("departure_city");
        String arrivalCity = intent.getStringExtra("arrival_city");
        String dateStr = intent.getStringExtra("date");
        LocalDate dateDateFormat = LocalDate.parse(dateStr);
        String formattedDate = dateDateFormat.format(DateTimeFormatter.ofPattern("d MMM, EEE", new Locale("uk")));
        String citiesStr = departureCity + " - " + arrivalCity;
        headerTitleCities.setText(citiesStr);
        headerTitleDate.setText(formattedDate);

        priceSeats = findViewById(R.id.price);
        retrofitService = new RetrofitService();
        Retrofit retrofit = retrofitService.getRetrofit();
        ticketsBusApi = retrofit.create(TicketsBusApi.class);
        int scheduleId = intent.getIntExtra("scheduleId",0);

        TextView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Button button = findViewById(R.id.buttonSelected);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedCount > 0) {
                    // Отправить выбранные места на следующий экран
                    Intent intentTicket = new Intent(ActivityPlaneSeats.this, ActivityBuyTickets.class);
                    ArrayList<Integer> selectedSeats = new ArrayList<>(selectedSeatsWithPrices.keySet());
                    ArrayList<Double> seatPrices = new ArrayList<>(selectedSeatsWithPrices.values());
                    intentTicket.putIntegerArrayListExtra("selected_seats", selectedSeats);
                    intentTicket.putExtra("seat_prices", seatPrices);
                    intentTicket.putExtra("scheduleId",scheduleId);
                    startActivity(intentTicket);
                } else {
                    Toast.makeText(ActivityPlaneSeats.this, "Оберіть місце", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private int createTextViews(LinearLayout layout, int count, int start, List<Integer> graySeats, int step, String numberLine) {
        Context context = getApplicationContext();
        TextView countSelectSeats = findViewById(R.id.countSelectSeats);

        int lastGeneratedNumber = 0;

        for (int i = 0; i < count; i++) {
            TextView textView = new TextView(context);
            textView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            int num = start + i * step;
            lastGeneratedNumber = num;
            textView.setText(num + "" + numberLine);
            textView.setTextSize(15);
            textView.setGravity(Gravity.CENTER);
            textView.setPadding(30, 25, 30, 25);

            if (graySeats.contains(num)) {
                textView.setBackgroundResource(R.drawable.rounded_square_gray_seat);
                textView.setTextColor(Color.BLACK);
                textView.setOnClickListener(null);
            } else {
                textView.setBackgroundResource(R.drawable.rounded_square_train_seats);
                textView.setTextColor(Color.BLACK);
                Intent intent = getIntent();
                double price = intent.getDoubleExtra("price", 0.0);

                textView.setOnClickListener(new View.OnClickListener() {
                    private boolean isSelected = false;

                    @Override
                    public void onClick(View v) {
                        if (isSelected) {
                            // Если место уже выбрано, то отменяем выбор
                            textView.setBackgroundResource(R.drawable.rounded_square_train_seats);
                            textView.setTextColor(Color.BLACK);
                            isSelected = false;
                            selectedSeatsWithPrices.remove(num); // Удаление из списка
                            selectedCount--;
                            priceSeats.setText(String.format("%.2f грн", price  * selectedCount));
                            if (selectedCount == 1){
                                countSelectSeats.setText(selectedCount + " квиток");
                            } else if (selectedCount > 1) {
                                countSelectSeats.setText(selectedCount + " квитка");
                            }
                        } else {
                            if (selectedCount < 2) {
                                // Если место не выбрано и можно выбрать, то выбираем его
                                textView.setBackgroundResource(R.drawable.selected_train_seat_style);
                                textView.setTextColor(Color.WHITE);
                                isSelected = true;
                                priceSeats.setText(String.format("%.2f грн", price  * (selectedCount+1)));
                                selectedSeatsWithPrices.put(num, price);
                                selectedCount++;
                                if (selectedCount == 1){
                                    countSelectSeats.setText(selectedCount + " квиток");
                                } else if (selectedCount > 1) {
                                    countSelectSeats.setText(selectedCount + " квитка");
                                }
                            } else {
                                Toast.makeText(ActivityPlaneSeats.this, "Можно обрати не більше 3 місць", Toast.LENGTH_SHORT).show();
                            }
                        }
                        checkSelectedSeats();
                    }
                });
            }

            layout.addView(textView);
        }
        return lastGeneratedNumber;
    }

    private void checkSelectedSeats() {
        if (selectedCount > 0) {
            bottomMenuTicket.setVisibility(View.VISIBLE);
        } else {
            bottomMenuTicket.setVisibility(View.INVISIBLE);
        }
    }
}
