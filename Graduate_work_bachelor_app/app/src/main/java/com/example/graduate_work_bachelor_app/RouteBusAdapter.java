package com.example.graduate_work_bachelor_app;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.graduate_work_bachelor_app.model.buses.BusRoute;

import java.util.List;

public class RouteBusAdapter extends BaseAdapter {

    private List<BusRoute> routes;
    private LayoutInflater inflater;
    private Context context;

    public RouteBusAdapter(Context context, List<BusRoute> routes) {
        this.context = context;
        this.routes = routes;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return routes.size();
    }

    @Override
    public Object getItem(int position) {
        return routes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_list, parent, false);
            holder = new ViewHolder();
            holder.itemList = convertView.findViewById(R.id.item_List);
            holder.routeId = convertView.findViewById(R.id.routeID);
            holder.routeNumber = convertView.findViewById(R.id.routeNumber);
            holder.departureCity = convertView.findViewById(R.id.departureCity);
            holder.arrivalCity = convertView.findViewById(R.id.arrivalCity);
            holder.departureStation = convertView.findViewById(R.id.departureStation);
            holder.arrivalStation = convertView.findViewById(R.id.arrivalStation);
            holder.availableSeats = convertView.findViewById(R.id.availableSeats);
            holder.price = convertView.findViewById(R.id.price);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // Получение маршрута для текущей позиции
        final BusRoute route = routes.get(position);

        // Установка данных в элементы макета
        holder.routeId.setText(String.valueOf(route.getRouteId()));
        holder.routeNumber.setText(route.getRouteNumber());
        holder.departureCity.setText(route.getDepartureCity());
        holder.arrivalCity.setText(route.getArrivalCity());
        holder.departureStation.setText(route.getDepartureStation());
        holder.arrivalStation.setText(route.getArrivalStation());
        holder.availableSeats.setText("Вільних місць: " + route.getCountSeats());
        holder.price.setText(route.getPrice() + " грн");

        // Установка обработчика нажатия на элемент списка
        holder.itemList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int routeId = Integer.parseInt(holder.routeId.getText().toString());
                Intent intent = new Intent(context, ActivityRouteInfo.class);
                intent.putExtra("ROUTE_ID", routeId);
                intent.putExtra("TRANSPORT_TYPE", "bus");
                intent.putExtra("STATUS", "update");
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    private static class ViewHolder {
        LinearLayout itemList;
        TextView routeId;
        TextView routeNumber;
        TextView departureCity;
        TextView arrivalCity;
        TextView departureStation;
        TextView arrivalStation;
        TextView availableSeats;
        TextView price;
    }
}
