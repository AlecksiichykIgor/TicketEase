package com.example.graduate_work_test_app;

import android.os.Bundle;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private LinearLayout firstLeftRows;
    private LinearLayout secondLeftRows;
    private LinearLayout firstRightRows;
    private LinearLayout secondRightRows;
    private LinearLayout bottomRow;
    private int lastSeatNumberInSecondLeftRow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bus);

        firstLeftRows = findViewById(R.id.first_left_rows);
        secondLeftRows = findViewById(R.id.second_left_rows);
        firstRightRows = findViewById(R.id.first_right_rows);
        secondRightRows = findViewById(R.id.second_right_rows);
        bottomRow = findViewById(R.id.bottom_row);

        setupSeats();
    }

    private void setupSeats() {
        // Генерируем номера мест
        List<String> firstLeftSeats = generateSeatNumbers(1, 9, 4);
        List<String> secondLeftSeats = generateSeatNumbers(2, 9, 4);
        List<String> firstRightSeats = generateSeatNumbers(3, 8, 4);
        List<String> secondRightSeats = generateSeatNumbers(4, 8, 4);

        // Запоминаем последний номер места во второй левой строке
        lastSeatNumberInSecondLeftRow = Integer.parseInt(secondLeftSeats.get(secondLeftSeats.size() - 1));

        // Занятые места
        Set<Integer> occupiedSeats = new HashSet<>();
        occupiedSeats.add(3);
        occupiedSeats.add(7);
        occupiedSeats.add(13);
        occupiedSeats.add(17);

        // Создаем адаптеры для каждого набора мест
        BusSeatsAdapter firstLeftAdapter = new BusSeatsAdapter(this, firstLeftSeats, occupiedSeats);
        BusSeatsAdapter secondLeftAdapter = new BusSeatsAdapter(this, secondLeftSeats, occupiedSeats);
        BusSeatsAdapter firstRightAdapter = new BusSeatsAdapter(this, firstRightSeats, occupiedSeats);
        BusSeatsAdapter secondRightAdapter = new BusSeatsAdapter(this, secondRightSeats, occupiedSeats);

        // Создаем адаптер для нижнего ряда
        List<String> bottomRowSeats = generateSeatNumbers(lastSeatNumberInSecondLeftRow + 1, 5, 1);
        BusSeatsAdapter bottomRowAdapter = new BusSeatsAdapter(this, bottomRowSeats, occupiedSeats);

        // Устанавливаем адаптеры для каждого контейнера
        addSeatsToLayout(firstLeftRows, firstLeftAdapter);
        addSeatsToLayout(secondLeftRows, secondLeftAdapter);
        addSeatsToLayout(firstRightRows, firstRightAdapter);
        addSeatsToLayout(secondRightRows, secondRightAdapter);
        addSeatsToLayout(bottomRow, bottomRowAdapter);
    }

    private List<String> generateSeatNumbers(int startNumber, int count, int step) {
        List<String> seatNumbers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            seatNumbers.add(String.valueOf(startNumber + i * step));
        }
        return seatNumbers;
    }

    private void addSeatsToLayout(LinearLayout layout, BusSeatsAdapter adapter) {
        int count = adapter.getItemCount();
        for (int i = 0; i < count; i++) {
            BusSeatsAdapter.BusSeatsViewHolder viewHolder = adapter.onCreateViewHolder(layout, i);
            adapter.onBindViewHolder(viewHolder, i);
            layout.addView(viewHolder.itemView);
        }
    }
}
