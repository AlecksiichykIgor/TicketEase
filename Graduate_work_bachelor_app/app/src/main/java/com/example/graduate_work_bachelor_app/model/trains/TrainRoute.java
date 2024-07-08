package com.example.graduate_work_bachelor_app.model.trains;

public class TrainRoute {

    private int routeId;
    private String routeNumber;
    private String departureCity;
    private String arrivalCity;
    private String departureStation;
    private String arrivalStation;
    private double price;
    private int countWagon;
    private int countSeats;

    public TrainRoute() {
    }

    public TrainRoute(int routeId, String routeNumber, String departureCity, String arrivalCity, String departureStation, String arrivalStation, double price, int countWagon, int countSeats) {
        this.routeId = routeId;
        this.routeNumber = routeNumber;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.departureStation = departureStation;
        this.arrivalStation = arrivalStation;
        this.price = price;
        this.countWagon = countWagon;
        this.countSeats = countSeats;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public String getRouteNumber() {
        return routeNumber;
    }

    public void setRouteNumber(String routeNumber) {
        this.routeNumber = routeNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public String getDepartureStation() {
        return departureStation;
    }

    public void setDepartureStation(String departureStation) {
        this.departureStation = departureStation;
    }

    public String getArrivalStation() {
        return arrivalStation;
    }

    public void setArrivalStation(String arrivalStation) {
        this.arrivalStation = arrivalStation;
    }

    public int getCountWagon() {
        return countWagon;
    }

    public void setCountWagon(int countWagon) {
        this.countWagon = countWagon;
    }

    public int getCountSeats() {
        return countSeats;
    }

    public void setCountSeats(int countSeats) {
        this.countSeats = countSeats;
    }

    @Override
    public String toString() {
        return "TrainRoute{" +
                "routeId=" + routeId +
                ", routeNumber='" + routeNumber + '\'' +
                ", departureCity='" + departureCity + '\'' +
                ", arrivalCity='" + arrivalCity + '\'' +
                ", departureStation='" + departureStation + '\'' +
                ", arrivalStation='" + arrivalStation + '\'' +
                ", price=" + price +
                ", countWagon=" + countWagon +
                ", countSeats=" + countSeats +
                '}';
    }
}
