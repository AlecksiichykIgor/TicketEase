package com.example.graduate_work_bachelor_app.model.trains;

import com.example.graduate_work_bachelor_app.model.Client;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TicketTrain {

    private int ticketId;

    private TrainSchedule trainSchedule;

    private int wagonNumber;

    private double price;

    private int seatNumber;

    private Client client;

    private String passengerFirstName;

    private String passengerLastName;

    private String status;

    private LocalDate datePurchase;

    private LocalDateTime timePurchase;

    public TicketTrain() {
    }

    public TicketTrain(int ticketId, TrainSchedule trainSchedule, int wagonNumber, double price, int seatNumber, Client client, String passengerFirstName, String passengerLastName, String status, LocalDate datePurchase, LocalDateTime timePurchase) {
        this.ticketId = ticketId;
        this.trainSchedule = trainSchedule;
        this.wagonNumber = wagonNumber;
        this.price = price;
        this.seatNumber = seatNumber;
        this.client = client;
        this.passengerFirstName = passengerFirstName;
        this.passengerLastName = passengerLastName;
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

    public TrainSchedule getTrainSchedule() {
        return trainSchedule;
    }

    public void setTrainSchedule(TrainSchedule trainSchedule) {
        this.trainSchedule = trainSchedule;
    }

    public int getWagonNumber() {
        return wagonNumber;
    }

    public void setWagonNumber(int wagonNumber) {
        this.wagonNumber = wagonNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getPassengerFirstName() {
        return passengerFirstName;
    }

    public void setPassengerFirstName(String passengerFirstName) {
        this.passengerFirstName = passengerFirstName;
    }

    public String getPassengerLastName() {
        return passengerLastName;
    }

    public void setPassengerLastName(String passengerLastName) {
        this.passengerLastName = passengerLastName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDatePurchase() {
        return datePurchase;
    }

    public void setDatePurchase(LocalDate datePurchase) {
        this.datePurchase = datePurchase;
    }

    public LocalDateTime getTimePurchase() {
        return timePurchase;
    }

    public void setTimePurchase(LocalDateTime timePurchase) {
        this.timePurchase = timePurchase;
    }

    @Override
    public String toString() {
        return "TrainTicket{" +
                "ticketId=" + ticketId +
                ", trainSchedule=" + trainSchedule +
                ", wagonNumber='" + wagonNumber + '\'' +
                ", price=" + price +
                ", seatNumber='" + seatNumber + '\'' +
                ", client=" + client +
                ", passengerFirstName='" + passengerFirstName + '\'' +
                ", passengerLastName='" + passengerLastName + '\'' +
                ", status='" + status + '\'' +
                ", datePurchase=" + datePurchase +
                ", timePurchase=" + timePurchase +
                '}';
    }
}
