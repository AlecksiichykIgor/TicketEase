package com.example.graduate_work_bachelor_app;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.graduate_work_bachelor_app.model.planes.FlightSchedule;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class FlightScheduleAdapter extends BaseAdapter {
    private Context context;
    private List<FlightSchedule> flightSchedules;
    private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    public FlightScheduleAdapter(Context context, List<FlightSchedule> flightSchedules) {
        this.context = context;
        this.flightSchedules = flightSchedules;
    }

    @Override
    public int getCount() {
        return flightSchedules.size();
    }

    @Override
    public Object getItem(int position) {
        return flightSchedules.get(position);
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

        FlightSchedule flightSchedule = flightSchedules.get(position);

        TextView routeNumber = convertView.findViewById(R.id.routeNumber);
        TextView departureDate = convertView.findViewById(R.id.departureRouteDate);
        TextView arrivalDate = convertView.findViewById(R.id.arrivalRouteDate);
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
        routeNumber.setText(flightSchedule.getAirplaneRoute().getRouteNumber());
        departureDate.setText(reformatDate(flightSchedule.getDepartureFlightDate().toString()));
        arrivalDate.setText(reformatDate(flightSchedule.getArrivalFlightDate().toString()));
        departureTime.setText(flightSchedule.getDepartureTime().format(timeFormatter));
        arrivalTime.setText(flightSchedule.getArrivalTime().format(timeFormatter));
        departureCity.setText(flightSchedule.getAirplaneRoute().getDepartureCity());
        departureStation.setText(flightSchedule.getAirplaneRoute().getDepartureAirport());
        arrivalCity.setText(flightSchedule.getAirplaneRoute().getArrivalCity());
        arrivalStation.setText(flightSchedule.getAirplaneRoute().getArrivalAirport());
        availableSeats.setText(String.format("Вільних місць: %d", flightSchedule.getAirplaneRoute().getCountSeats()));
        price.setText(String.format("%.2f грн", flightSchedule.getAirplaneRoute().getPrice()));
        schedule.setText(String.valueOf(flightSchedule.getScheduleId()));

        long travelMinutes = java.time.Duration.between(flightSchedule.getDepartureTime(), flightSchedule.getArrivalTime()).toMinutes();
        long hours = travelMinutes / 60;
        long minutes = travelMinutes % 60;
        travelTime.setText(String.format("%02d:%02d", hours, minutes));

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = flightSchedule.getScheduleId();
                Intent intent = new Intent(context, ActivityPlaneSeats.class);
                intent.putExtra("scheduleId", id);
                intent.putExtra("departure_city", flightSchedule.getAirplaneRoute().getDepartureCity());
                intent.putExtra("arrival_city", flightSchedule.getAirplaneRoute().getArrivalCity());
                intent.putExtra("date", flightSchedule.getDepartureFlightDate().toString());
                intent.putExtra("price", flightSchedule.getAirplaneRoute().getPrice());
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    public String reformatDate(String dateStr) {
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
