package com.example.graduate_work_bachelor_app.model.buses;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BusSchedule {

    private int scheduleId;
    private BusRoute busRoute;
    private LocalDate departureRouteDate;
    private LocalDate arrivalRouteDate;
    private LocalDateTime departureTime;
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

    public BusRoute getBusRoute() {
        return busRoute;
    }

    public void setBusRoute(BusRoute busRoute) {
        this.busRoute = busRoute;
    }

    public LocalDate getDepartureRouteDate() {
        return departureRouteDate;
    }

    public void setDepartureRouteDate(LocalDate departureRouteDate) {
        this.departureRouteDate = departureRouteDate;
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
