package com.example.graduate_work_test_app;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import java.util.Set;

public class BusSeatsAdapter extends RecyclerView.Adapter<BusSeatsAdapter.BusSeatsViewHolder> {

    private final Context context;
    private final List<String> seatNumbers;
    private final Set<Integer> graySeats;

    public BusSeatsAdapter(Context context, List<String> seatNumbers, Set<Integer> graySeats) {
        this.context = context;
        this.seatNumbers = seatNumbers;
        this.graySeats = graySeats;
    }

    @NonNull
    @Override
    public BusSeatsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        return new BusSeatsViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull BusSeatsViewHolder holder, int position) {
        holder.bind(seatNumbers.get(position), graySeats);
    }

    @Override
    public int getItemCount() {
        return seatNumbers.size();
    }

    static class BusSeatsViewHolder extends RecyclerView.ViewHolder {

        private final LinearLayout seatContainer;

        public BusSeatsViewHolder(@NonNull LinearLayout itemView) {
            super(itemView);
            this.seatContainer = itemView;
        }

        public void bind(String seatNumber, Set<Integer> graySeats) {
            TextView seatView = new TextView(seatContainer.getContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(8, 8, 8, 8); // Отступы между элементами
            seatView.setLayoutParams(params);
            seatView.setText(seatNumber);
            seatView.setTextSize(20);
            seatView.setGravity(Gravity.CENTER);
            seatView.setPadding(50, 30, 50, 30);

            int seatNum = Integer.parseInt(seatNumber);

            if (graySeats.contains(seatNum)) {
                // Если место занято
                seatView.setBackgroundResource(R.drawable.rounded_square_gray_seat);
                seatView.setTextColor(Color.BLACK);
                seatView.setOnClickListener(null); // Отключаем обработчик нажатий
            } else {
                // Если место свободно
                seatView.setBackgroundResource(R.drawable.rounded_square_train_seats);
                seatView.setTextColor(Color.BLACK);
                seatView.setOnClickListener(new View.OnClickListener() {
                    private boolean isSelected = false;

                    @Override
                    public void onClick(View v) {
                        if (isSelected) {
                            // Если место уже выбрано, то отменяем выбор
                            seatView.setBackgroundResource(R.drawable.rounded_square_train_seats);
                            seatView.setTextColor(Color.BLACK);
                            isSelected = false;
                        } else {
                            // Если место не выбрано, то выбираем его
                            seatView.setBackgroundResource(R.drawable.selected_train_seat_style);
                            seatView.setTextColor(Color.WHITE);
                            isSelected = true;
                        }
                    }
                });
            }

            seatContainer.addView(seatView);
        }
    }
}
