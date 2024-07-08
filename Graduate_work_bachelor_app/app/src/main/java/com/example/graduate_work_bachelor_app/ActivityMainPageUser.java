package com.example.graduate_work_bachelor_app;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

public class ActivityMainPageUser extends AppCompatActivity {

    public static final String TRANSPORT_TYPE_PLANE = "plane";
    public static final String TRANSPORT_TYPE_BUS = "bus";
    public static final String TRANSPORT_TYPE_TRAIN = "train";

    private EditText departureCityEditText;
    private EditText arrivalCityEditText;
    private TextView datePickerText;
    private LocalDate selectedDate;
    private TextView swapIcon;
    private RadioButton lastCheckedRadioButton;
    private RadioGroup radioGroup;
    private Button searchButton;
    private String selectedTransportType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page_user);

        departureCityEditText = findViewById(R.id.departure_city);
        arrivalCityEditText = findViewById(R.id.arrival_city);
        datePickerText = findViewById(R.id.date_picker_text);
        radioGroup = findViewById(R.id.radioGroup);
        searchButton = findViewById(R.id.button_search);
        swapIcon = findViewById(R.id.swap_icon);
        LinearLayout datePickerActions = findViewById(R.id.date_picker_actions);

        setDefaultDate();

        datePickerActions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkedRadioButton = findViewById(checkedId);
                if (lastCheckedRadioButton != null && lastCheckedRadioButton == checkedRadioButton) {
                    checkedRadioButton.setChecked(false);
                    lastCheckedRadioButton = null;
                    selectedTransportType = null; // Reset transport type when deselected
                } else {
                    lastCheckedRadioButton = checkedRadioButton;
                    switch (checkedId) {
                        case R.id.plane_Radio:
                            selectedTransportType = TRANSPORT_TYPE_PLANE;
                            break;
                        case R.id.bus_Radio:
                            selectedTransportType = TRANSPORT_TYPE_BUS;
                            break;
                        case R.id.train_Radio:
                            selectedTransportType = TRANSPORT_TYPE_TRAIN;
                            break;
                    }
                }
            }
        });

        swapIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swapCities();
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields()) {
                    Intent intent = new Intent(ActivityMainPageUser.this, ActivityTransportSchedules.class);
                    intent.putExtra("departure_city", departureCityEditText.getText().toString());
                    intent.putExtra("arrival_city", arrivalCityEditText.getText().toString());
                    intent.putExtra("date", selectedDate.toString());
                    intent.putExtra("transport_type", selectedTransportType); // Add transport type
                    startActivity(intent);
                }
            }
        });
    }

    private void setDefaultDate() {
        selectedDate = LocalDate.now();
        String formattedDateForDisplay = selectedDate.format(DateTimeFormatter.ofPattern("d MMM", new Locale("uk")));
        datePickerText.setText(formattedDateForDisplay);
        datePickerText.setTextSize(16);
    }

    private void showDatePicker() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year1, month1, dayOfMonth1) -> {
                    selectedDate = LocalDate.of(year1, month1 + 1, dayOfMonth1);
                    String formattedDate = selectedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd", new Locale("uk")));
                    datePickerText.setText(formattedDate);
                    datePickerText.setTextSize(16);
                }, year, month, dayOfMonth);

        datePickerDialog.show();
    }

    private void swapCities() {
        String departureCity = departureCityEditText.getText().toString().trim();
        String arrivalCity = arrivalCityEditText.getText().toString().trim();

        departureCityEditText.setText(arrivalCity);
        arrivalCityEditText.setText(departureCity);
    }

    private boolean validateFields() {
        String departureCity = departureCityEditText.getText().toString().trim();
        String arrivalCity = arrivalCityEditText.getText().toString().trim();

        if (departureCity.isEmpty()) {
            showToast("Please enter a departure city.");
            return false;
        }

        if (arrivalCity.isEmpty()) {
            showToast("Please enter an arrival city.");
            return false;
        }

        if (selectedDate == null) {
            showToast("Please select a date.");
            return false;
        }

        if (selectedDate.isBefore(LocalDate.now())) {
            showToast("Date cannot be in the past.");
            return false;
        }

        if (selectedTransportType == null) {
            showToast("Please select a transport type.");
            return false;
        }

        return true;
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void openMyTickets(View view) {
        Intent intent = new Intent(this, ActivityMyTickets.class);
        startActivity(intent);
    }

    public LocalDate getSelectedDate() {
        return selectedDate;
    }
}
