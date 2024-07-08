package com.graduatework.ticketease.model.trains.ticketsTrain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.graduatework.ticketease.model.trains.flightTrainSchedule.TrainSchedule;
import com.graduatework.ticketease.model.clients.Client;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class TicketTrain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ticketId;

    @ManyToOne
    @JoinColumn(name = "schedule_id", nullable = false)
    private TrainSchedule trainSchedule;

    @Column(nullable = false)
    private int wagonNumber;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int seatNumber;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Client client;

    @Column(nullable = false)
    private String passengerFirstName;

    @Column(nullable = false)
    private String passengerLastName;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate datePurchase;

    @Column(nullable = false)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
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
