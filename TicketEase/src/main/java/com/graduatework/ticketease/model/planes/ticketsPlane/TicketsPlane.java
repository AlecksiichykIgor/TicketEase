package com.graduatework.ticketease.model.planes.ticketsPlane;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.graduatework.ticketease.model.planes.flightPlaneSchedule.FlightSchedule;
import com.graduatework.ticketease.model.clients.Client;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class TicketsPlane {

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
    private FlightSchedule flightSchedule;

    @Column(nullable = false)
    private double ticketPrice;

    @Column(nullable = false)
    private String seatNumber;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate datePurchase;

    @Column(nullable = false)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
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

    public String  getSeatNumber() {
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
