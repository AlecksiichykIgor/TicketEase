package com.graduatework.ticketease.model.buses.ticketsbus;

import com.graduatework.ticketease.model.buses.flightBusSchedule.BusSchedule;
import com.graduatework.ticketease.model.clients.Client;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
public class TicketsBus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ticketId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Client client;

    @Column(nullable = false)
    private String passengerFirstName;

    @Column(nullable = false)
    private String passengerLastName;

    @ManyToOne
    @JoinColumn(name = "schedule_id", nullable = false)
    private BusSchedule busSchedule;

    @Column(nullable = false)
    private double ticketPrice;

    @Column(nullable = false)
    private int seatNumber;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private LocalDate datePurchase;

    @Column(nullable = false)
    private LocalDateTime timePurchase;

    public TicketsBus() {
    }

    public TicketsBus(int ticketId, Client client, String passengerFirstName, String passengerLastName, BusSchedule busSchedule, double ticketPrice, int seatNumber, String status, LocalDate datePurchase, LocalDateTime timePurchase) {
        this.ticketId = ticketId;
        this.client = client;
        this.passengerFirstName = passengerFirstName;
        this.passengerLastName = passengerLastName;
        this.busSchedule = busSchedule;
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

    public BusSchedule getBusSchedule() {
        return busSchedule;
    }

    public void setBusSchedule(BusSchedule busSchedule) {
        this.busSchedule = busSchedule;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
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
        return "TicketsBus{" +
                "ticketId=" + ticketId +
                ", client=" + client +
                ", passengerFirstName='" + passengerFirstName + '\'' +
                ", passengerLastName='" + passengerLastName + '\'' +
                ", busSchedule=" + busSchedule +
                ", ticketPrice=" + ticketPrice +
                ", seatNumber='" + seatNumber + '\'' +
                ", status='" + status + '\'' +
                ", datePurchase=" + datePurchase +
                ", timePurchase=" + timePurchase +
                '}';
    }
}
