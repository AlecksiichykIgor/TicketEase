package com.example.graduate_work_bachelor_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class TicketAdminAdapter extends BaseAdapter {
    private final Context context;
    private final List<Ticket> tickets;

    public TicketAdminAdapter(Context context, List<Ticket> tickets) {
        this.context = context;
        this.tickets = tickets;
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
        return tickets.get(position).getTicketId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.ticket_admin_item, parent, false);
        }

        Ticket ticket = tickets.get(position);

        TextView tvTicketId = convertView.findViewById(R.id.tvTicketId);
        TextView tvDatePurchase = convertView.findViewById(R.id.tvDatePurchase);
        TextView tvTicketPrice = convertView.findViewById(R.id.tvTicketPrice);

        tvTicketId.setText("ID: " + ticket.getTicketId());
        tvDatePurchase.setText("Дата покупки: " + ticket.getDatePurchase());
        tvTicketPrice.setText("Сума: " + ticket.getTicketPrice() + " UAH");

        return convertView;
    }
}
