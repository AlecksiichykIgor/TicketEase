package com.graduatework.ticketease.model.buses.flightBusSchedule;

import com.graduatework.ticketease.model.buses.routsbus.BusRoute;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class BusSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int scheduleId;

    @ManyToOne
    @JoinColumn(name = "route_id", nullable = false)
    private BusRoute busRoute;

    @Column(nullable = false)
    private LocalDate departureRouteDate;

    @Column(nullable = false)
    private LocalDate arrivalRouteDate;

    @Column(nullable = false)
    private LocalDateTime departureTime;

    @Column(nullable = false)
    private LocalDateTime arrivalTime;

    public BusSchedule() {
    }

    public BusSchedule(int scheduleId, BusRoute busRoute, LocalDate departureRouteDate, LocalDate arrivalRouteDate, LocalDateTime departureTime, LocalDateTime arrivalTime) {
        this.scheduleId = scheduleId;
        this.busRoute = busRoute;
        this.departureRouteDate = departureRouteDate;
        this.arrivalRouteDate = arrivalRouteDate;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public LocalDate getDepartureRouteDate() {
        return departureRouteDate;
    }

    public void setDepartureRouteDate(LocalDate departureRouteDate) {
        this.departureRouteDate = departureRouteDate;
    }

    public BusRoute getBusRoute() {
        return busRoute;
    }

    public void setBusRoute(BusRoute busRoute) {
        this.busRoute = busRoute;
    }

    public LocalDate getArrivalRouteDate() {
        return arrivalRouteDate;
    }

    public void setArrivalRouteDate(LocalDate arrivalRouteDate) {
        this.arrivalRouteDate = arrivalRouteDate;
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

    @Override
    public String toString() {
        return "BusSchedule{" +
                "scheduleId=" + scheduleId +
                ", busRoute=" + busRoute +
                ", departureRouteDate=" + departureRouteDate +
                ", arrivalRouteDate=" + arrivalRouteDate +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                '}';
    }
}
