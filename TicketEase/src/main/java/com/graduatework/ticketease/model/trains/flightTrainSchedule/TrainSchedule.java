package com.graduatework.ticketease.model.trains.flightTrainSchedule;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.graduatework.ticketease.model.trains.routeTrain.TrainRoute;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class TrainSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int scheduleId;

    @ManyToOne
    @JoinColumn(name = "route_id", nullable = false)
    private TrainRoute trainRoute;

    @Column(nullable = false)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate departureFlightDate;

    @Column(nullable = false)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate arrivalFlightDate;

    @Column(nullable = false)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime departureTime;

    @Column(nullable = false)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime arrivalTime;

    public TrainSchedule() {
    }

    public TrainSchedule(int scheduleId, TrainRoute trainRoute, LocalDate departureFlightDate, LocalDate arrivalFlightDate, LocalDateTime departureTime, LocalDateTime arrivalTime) {
        this.scheduleId = scheduleId;
        this.trainRoute = trainRoute;
        this.departureFlightDate = departureFlightDate;
        this.arrivalFlightDate = arrivalFlightDate;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public TrainRoute getTrainRoute() {
        return trainRoute;
    }

    public void setTrainRoute(TrainRoute trainRoute) {
        this.trainRoute = trainRoute;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public LocalDate getDepartureFlightDate() {
        return departureFlightDate;
    }

    public void setDepartureFlightDate(LocalDate departureFlightDate) {
        this.departureFlightDate = departureFlightDate;
    }

    public LocalDate getArrivalFlightDate() {
        return arrivalFlightDate;
    }

    public void setArrivalFlightDate(LocalDate arrivalFlightDate) {
        this.arrivalFlightDate = arrivalFlightDate;
    }

    @Override
    public String toString() {
        return "TrainSchedule{" +
                "scheduleId=" + scheduleId +
                ", trainRoute=" + trainRoute +
                ", departureFlightDate=" + departureFlightDate +
                ", arrivalFlightDate=" + arrivalFlightDate +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                '}';
    }
}
