package com.example.graduate_work_bachelor_app.model.planes;


import java.time.LocalDate;
import java.time.LocalDateTime;

public class FlightSchedule {

    private int scheduleId;
    private AirplaneRoute airplaneRoute;
    private LocalDate departureFlightDate;
    private LocalDate arrivalFlightDate;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

    public FlightSchedule() {
    }

    public FlightSchedule(int scheduleId, AirplaneRoute airplaneRoute, LocalDate departureFlightDate, LocalDate arrivalFlightDate, LocalDateTime departureTime, LocalDateTime arrivalTime) {
        this.scheduleId = scheduleId;
        this.airplaneRoute = airplaneRoute;
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

    public AirplaneRoute getAirplaneRoute() {
        return airplaneRoute;
    }

    public void setAirplaneRoute(AirplaneRoute airplaneRoute) {
        this.airplaneRoute = airplaneRoute;
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
        return "FlightSchedule{" +
                "scheduleId=" + scheduleId +
                ", airplaneRoute=" + airplaneRoute +
                ", departureFlightDate=" + departureFlightDate +
                ", arrivalFlightDate=" + arrivalFlightDate +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                '}';
    }
}
