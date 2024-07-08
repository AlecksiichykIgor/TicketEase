package com.example.graduate_work_bachelor_app;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.graduate_work_bachelor_app.model.buses.BusSchedule;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class BusScheduleAdapter extends BaseAdapter {
    private Context context;
    private List<BusSchedule> busSchedules;
    private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    public BusScheduleAdapter(Context context, List<BusSchedule> busSchedules) {
        this.context = context;
        this.busSchedules = busSchedules;
    }

    @Override
    public int getCount() {
        return busSchedules.size();
    }

    @Override
    public Object getItem(int position) {
        return busSchedules.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_schedule, parent, false);
        }

        BusSchedule busSchedule = busSchedules.get(position);

        TextView routeNumber = convertView.findViewById(R.id.routeNumber);
        TextView departureRouteDate = convertView.findViewById(R.id.departureRouteDate);
        TextView arrivalRouteDate = convertView.findViewById(R.id.arrivalRouteDate);
        TextView departureTime = convertView.findViewById(R.id.departureTime);
        TextView arrivalTime = convertView.findViewById(R.id.arrivalTime);
        TextView departureCity = convertView.findViewById(R.id.departureCity);
        TextView departureStation = convertView.findViewById(R.id.departureStation);
        TextView arrivalCity = convertView.findViewById(R.id.arrivalCity);
        TextView arrivalStation = convertView.findViewById(R.id.arrivalStation);
        TextView availableSeats = convertView.findViewById(R.id.availableSeats);
        TextView price = convertView.findViewById(R.id.price);
        TextView schedule = convertView.findViewById(R.id.item_bus_schedule_layout);
        TextView travelTime = convertView.findViewById(R.id.travelTime);

        // Заполнение данных
        routeNumber.setText(busSchedule.getBusRoute().getRouteNumber());
        departureRouteDate.setText(reformatDate(busSchedule.getDepartureRouteDate().toString()));
        arrivalRouteDate.setText(reformatDate(busSchedule.getArrivalRouteDate().toString()));
        departureTime.setText(busSchedule.getDepartureTime().format(timeFormatter));
        arrivalTime.setText(busSchedule.getArrivalTime().format(timeFormatter));
        departureCity.setText(busSchedule.getBusRoute().getDepartureCity());
        departureStation.setText(busSchedule.getBusRoute().getDepartureStation());
        arrivalCity.setText(busSchedule.getBusRoute().getArrivalCity());
        arrivalStation.setText(busSchedule.getBusRoute().getArrivalStation());
        availableSeats.setText(String.format("Вільних місць: %d", busSchedule.getBusRoute().getCountSeats()));
        price.setText(String.format("%.2f грн", busSchedule.getBusRoute().getPrice()));
        schedule.setText(String.valueOf(busSchedule.getScheduleId()));

        // Расчет времени в пути
        long travelMinutes = java.time.Duration.between(busSchedule.getDepartureTime(), busSchedule.getArrivalTime()).toMinutes();
        long hours = travelMinutes / 60;
        long minutes = travelMinutes % 60;
        travelTime.setText(String.format("%02d:%02d", hours, minutes));

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = busSchedule.getScheduleId();
                Intent intent = new Intent(context, ActivityBusSeats.class);
                intent.putExtra("scheduleId", id);
                intent.putExtra("departure_city", busSchedule.getBusRoute().getDepartureCity());
                intent.putExtra("arrival_city", busSchedule.getBusRoute().getArrivalCity());
                intent.putExtra("date", busSchedule.getDepartureRouteDate().toString());
                intent.putExtra("price", busSchedule.getBusRoute().getPrice());
                context.startActivity(intent);
            }
        });

        return convertView;
    }
    public String reformatDate(String dateStr){
        LocalDate dateDateFormat = LocalDate.parse(dateStr);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM, EEE", new Locale("uk"));

        String formattedDate = dateDateFormat.format(formatter);

        int commaIndex = formattedDate.indexOf(",");
        if (commaIndex != -1 && commaIndex + 2 < formattedDate.length()) {
            formattedDate = formattedDate.substring(0, commaIndex + 2) +
                    formattedDate.substring(commaIndex + 2, commaIndex + 3).toUpperCase() +
                    formattedDate.substring(commaIndex + 3);
        }
        return formattedDate;
    }
}
