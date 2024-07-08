package com.example.graduate_work_bachelor_app.model.planes;
import com.example.graduate_work_bachelor_app.model.Client;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TicketsPlane {

    private int ticketId;

    private Client client;

    private String passengerFirstName;

    private String passengerLastName;

    private FlightSchedule flightSchedule;

    private double ticketPrice;

    private String  seatNumber;

    private String status;

    private LocalDate datePurchase;

    private LocalDateTime timePurchase;

    public TicketsPlane() {
    }

    public TicketsPlane(int ticketId, Client client, String passengerFirstName, String passengerLastName, FlightSchedule flightSchedule, double ticketPrice, String seatNumber, String status, LocalDate datePurchase, LocalDateTime timePurchase) {
        this.ticketId = ticketId;
        this.client = client;
        this.passengerFirstName = passengerFirstName;
        this.passengerLastName = passengerLastName;
        this.flightSchedule = flightSchedule;
        this.ticketPrice = ticketPrice;
        this.seatNumber = seatNumber;
        this.status = status;
        this.datePurchase = datePurchase;
        this.timePurchase = timePurchase;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public FlightSchedule getFlightSchedule() {
        return flightSchedule;
    }

    public void setFlightSchedule(FlightSchedule flightSchedule) {
        this.flightSchedule = flightSchedule;
    }

    public String getPassengerLastName() {
        return passengerLastName;
    }

    public void setPassengerLastName(String passengerLastName) {
        this.passengerLastName = passengerLastName;
    }

    public String getPassengerFirstName() {
        return passengerFirstName;
    }

    public void setPassengerFirstName(String passengerFirstName) {
        this.passengerFirstName = passengerFirstName;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getTimePurchase() {
        return timePurchase;
    }

    public void setTimePurchase(LocalDateTime timePurchase) {
        this.timePurchase = timePurchase;
    }

    public LocalDate getDatePurchase() {
        return datePurchase;
    }

    public void setDatePurchase(LocalDate datePurchase) {
        this.datePurchase = datePurchase;
    }

    @Override
    public String toString() {
        return "TicketsPlane{" +
                "ticketId=" + ticketId +
                ", client=" + client +
                ", passengerFirstName='" + passengerFirstName + '\'' +
                ", passengerLastName='" + passengerLastName + '\'' +
                ", flightSchedule=" + flightSchedule +
                ", ticketPrice=" + ticketPrice +
                ", seatNumber='" + seatNumber + '\'' +
                ", status='" + status + '\'' +
                ", datePurchase=" + datePurchase +
                ", timePurchase=" + timePurchase +
                '}';
    }
}
