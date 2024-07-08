package com.example.graduate_work_bachelor_app;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.graduate_work_bachelor_app.model.buses.TicketsBus;
import com.example.graduate_work_bachelor_app.retrofit.RetrofitService;
import com.example.graduate_work_bachelor_app.retrofit.TicketsBusApi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TicketAdapter extends BaseAdapter {
    private Context context;
    private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    private List<TicketsBus> tickets;
    private TicketsBusApi ticketsBusApi;
    private TicketReturnListener ticketReturnListener;
    public interface TicketReturnListener {
        void onTicketReturned();
    }

    public TicketAdapter(Context context, List<TicketsBus> tickets) {
        this.context = context;
        this.tickets = tickets;
    }

    // Add this method to set the listener
    public void setTicketReturnListener(TicketReturnListener listener) {
        this.ticketReturnListener = listener;
    }

    @Override
    public int getCount() {
        return tickets.size();
    }

    @Override
    public Object getItem(int position) {
        return tickets.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_ticket_list, parent, false);
        }

        TicketsBus ticket = tickets.get(position);

        TextView tvName = convertView.findViewById(R.id.tvName);
        TextView tvTicketTime = convertView.findViewById(R.id.tvTicketDateTime);
        TextView tvRouteNumber = convertView.findViewById(R.id.tvRouteNumber);
        TextView tvDepartureRouteDate = convertView.findViewById(R.id.tvDepartureRouteDate);
        TextView tvArrivalRouteDate = convertView.findViewById(R.id.tvArrivalRouteDate);
        TextView tvDepartureTime = convertView.findViewById(R.id.tvDepartureTime);
        TextView tvTravelTime = convertView.findViewById(R.id.tvTravelTime);
        TextView tvArrivalTime = convertView.findViewById(R.id.tvArrivalTime);
        TextView tvDepartureCity = convertView.findViewById(R.id.tvDepartureCity);
        TextView tvDepartureStation = convertView.findViewById(R.id.tvDepartureStation);
        TextView tvArrivalCity = convertView.findViewById(R.id.tvArrivalCity);
        TextView tvArrivalStation = convertView.findViewById(R.id.tvArrivalStation);
        TextView tvNumberWagon = convertView.findViewById(R.id.tvNumberWagon);
        TextView tvNumberSeat = convertView.findViewById(R.id.tvNumberSeat);
        TextView tvPrice = convertView.findViewById(R.id.tvPrice);
        TextView btnOption = convertView.findViewById(R.id.btnOption);

        ticket.getTicketId();
        tvName.setText(ticket.getPassengerFirstName() + " " + ticket.getPassengerLastName());
        tvTicketTime.setText(ticket.getDatePurchase() + " " + formatLocalDateTime(ticket.getTimePurchase()));
        tvRouteNumber.setText(ticket.getBusSchedule().getBusRoute().getRouteNumber());
        tvDepartureRouteDate.setText(ticket.getBusSchedule().getDepartureRouteDate().toString());
        tvArrivalRouteDate.setText(ticket.getBusSchedule().getArrivalRouteDate().toString());
        tvDepartureTime.setText(ticket.getBusSchedule().getDepartureTime().format(timeFormatter));
        tvArrivalTime.setText(ticket.getBusSchedule().getArrivalTime().format(timeFormatter));
        tvDepartureCity.setText(ticket.getBusSchedule().getBusRoute().getDepartureCity());
        tvDepartureStation.setText(ticket.getBusSchedule().getBusRoute().getDepartureStation());
        tvArrivalCity.setText(ticket.getBusSchedule().getBusRoute().getArrivalCity());
        tvArrivalStation.setText(ticket.getBusSchedule().getBusRoute().getArrivalStation());
        tvNumberWagon.setHeight(0);
        tvNumberWagon.setWidth(0);
        tvNumberSeat.setText("Місце: " + ticket.getSeatNumber());
        tvNumberSeat.setTextSize(20);
        tvPrice.setText(ticket.getTicketPrice() + " грн");

        long travelMinutes = java.time.Duration.between(ticket.getBusSchedule().getDepartureTime(), ticket.getBusSchedule().getArrivalTime()).toMinutes();
        long hours = travelMinutes / 60;
        long minutes = travelMinutes % 60;
        tvTravelTime.setText(String.format("%02d:%02d", hours, minutes));

        // Установка слушателя для btnOption
        btnOption.setOnClickListener(v -> showPopupMenu(v, ticket));

        return convertView;
    }

    private void showPopupMenu(View view, TicketsBus ticket) {
        PopupMenu popupMenu = new PopupMenu(context, view);
        popupMenu.getMenuInflater().inflate(R.menu.ticket_options_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_return:
                    RetrofitService retrofitService = new RetrofitService();
                    Retrofit retrofit = retrofitService.getRetrofit();
                    ticketsBusApi = retrofit.create(TicketsBusApi.class);

                    int ticketId = ticket.getTicketId(); // Получаем идентификатор билета

                    Call<Void> call = ticketsBusApi.returnTicket(ticketId);

                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.isSuccessful()) {
                                // Билет успешно помечен как "Повернуто"
                                // Обработка успешного ответа
                                if (ticketReturnListener != null) {
                                    ticketReturnListener.onTicketReturned(); // Notify listener
                                }
                            } else {
                                // Обработка неуспешного ответа
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            // Обработка ошибки при выполнении запроса
                        }
                    });
                    return true;
                case R.id.action_download:
                    if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                    }
                    handleDownload(ticket);
                    return true;
                default:
                    return false;
            }
        });
        popupMenu.show();
    }


    private void handleDownload(TicketsBus ticket) {
        // Обработка скачивания билета
    }

    public static String formatLocalDateTime(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return localDateTime.format(formatter);
    }
}
