package com.example.graduate_work_test_app;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class WagonAdapter extends BaseAdapter {
    private Context context;
    private int wagonCount;
    private Map<Integer, Set<Integer>> wagonGraySeats;

    public WagonAdapter(Context context, int wagonCount, Map<Integer, Set<Integer>> wagonGraySeats) {
        this.context = context;
        this.wagonCount = wagonCount;
        this.wagonGraySeats = wagonGraySeats;
    }

    @Override
    public int getCount() {
        return wagonCount;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.wagon_layout, parent, false);
            // Создание и нумерация для строк вагонов
            LinearLayout firstRowLeft = convertView.findViewById(R.id.first_left_rows);
            LinearLayout secondRowLeft = convertView.findViewById(R.id.second_left_rows);
            LinearLayout thirdRowLeft = convertView.findViewById(R.id.third_left_rows);
            LinearLayout fourthRowLeft = convertView.findViewById(R.id.fourth_left_rows);
            LinearLayout firstRowRight = convertView.findViewById(R.id.first_right_rows);
            LinearLayout secondRowRight = convertView.findViewById(R.id.second_right_rows);
            LinearLayout thirdRowRight = convertView.findViewById(R.id.third_right_rows);
            LinearLayout fourthRowRight = convertView.findViewById(R.id.fourth_right_rows);

            int startLeft = 1;
            int step = 4;
            createRow(firstRowLeft, startLeft, 13, step, position);
            createRow(firstRowRight, startLeft + step * 13, 2, step, position);

            createRow(secondRowLeft, startLeft + 1, 13, step, position);
            createRow(secondRowRight, startLeft + 1 + step * 13, 2, step, position);

            createRow(thirdRowLeft, startLeft + 2, 2, step, position);
            createRow(thirdRowRight, startLeft + 2 + step * 2, 13, step, position);

            createRow(fourthRowLeft, startLeft + 3, 2, step, position);
            createRow(fourthRowRight, startLeft + 3 + step * 2, 13, step, position);

            LinearLayout topLeftRows = convertView.findViewById(R.id.top_left_rows);
            LinearLayout bottomLeftRows = convertView.findViewById(R.id.bottom_left_rows);
            addTableView(topLeftRows);
            addTableView(bottomLeftRows);
        }

        TextView wagonTitle = convertView.findViewById(R.id.wagon_title);
        wagonTitle.setText("Wagon № " + (position + 1));

        return convertView;
    }

    private void createRow(LinearLayout row, int start, int count, int step, int wagonPosition) {
        int lastSeatNumber = start;
        Set<Integer> graySeats = wagonGraySeats.getOrDefault(wagonPosition, Set.of());
        for (int i = 0; i < count; i++) {
            TextView textView = createTextView(lastSeatNumber, graySeats);
            row.addView(textView);
            lastSeatNumber += step;
        }
    }

    private TextView createTextView(int number, Set<Integer> graySeats) {
        final TextView textView = new TextView(context);
        textView.setId(View.generateViewId());
        textView.setText(String.valueOf(number));
        textView.setTextSize(20);
        textView.setGravity(Gravity.CENTER);
        textView.setPadding(50, 14, 50, 14);
        if (graySeats.contains(number)) {
            textView.setBackgroundResource(R.drawable.rounded_square_gray_seat);
            textView.setTextColor(context.getResources().getColor(android.R.color.black)); // черный текст для серых сидений
            textView.setOnClickListener(null); // отключаем обработчик нажатий
        } else {
            textView.setBackgroundResource(R.drawable.rounded_square_train_seats);
            textView.setTextColor(context.getResources().getColor(android.R.color.black));
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (textView.getBackground().getConstantState().equals(context.getResources().getDrawable(R.drawable.selected_train_seat_style).getConstantState())) {
                        textView.setBackgroundResource(R.drawable.rounded_square_train_seats);
                        textView.setTextColor(context.getResources().getColor(android.R.color.black));
                    } else {
                        textView.setBackgroundResource(R.drawable.selected_train_seat_style);
                        textView.setTextColor(context.getResources().getColor(android.R.color.white));
                    }
                }
            });
        }
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                200,
                180
        );
        params.weight = 1;
        params.setMargins(8, 0, 8, 5);

        textView.setLayoutParams(params);

        return textView;
    }

    private void addTableView(LinearLayout layout) {
        TextView tableView = new TextView(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        params.setMargins(16, 0, 16, 0);
        tableView.setLayoutParams(params);
        tableView.setBackgroundResource(R.drawable.train_table_rectangle);
        tableView.setPadding(90, 0, 90, 0);

        layout.addView(tableView);
    }
}
