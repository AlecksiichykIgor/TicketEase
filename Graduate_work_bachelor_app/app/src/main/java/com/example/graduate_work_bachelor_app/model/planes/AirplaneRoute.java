package com.example.graduate_work_bachelor_app.model.planes;

public class AirplaneRoute {

    private int routeId;
    private String routeNumber;
    private String departureCity;
    private String arrivalCity;
    private String departureAirport;
    private String arrivalAirport;
    private double price;
    private int countSeats;

    public AirplaneRoute() {
    }

    public AirplaneRoute(int routeId, String routeNumber, String departureCity, String arrivalCity, String departureAirport, String arrivalAirport, double price, int countSeats) {
        this.routeId = routeId;
        this.routeNumber = routeNumber;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.price = price;
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

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public int getCountSeats() {
        return countSeats;
    }

    public void setCountSeats(int countSeats) {
        this.countSeats = countSeats;
    }

    @Override
    public String toString() {
        return "AirplaneRoute{" +
                "routeId=" + routeId +
                ", routeNumber='" + routeNumber + '\'' +
                ", departureCity='" + departureCity + '\'' +
                ", arrivalCity='" + arrivalCity + '\'' +
                ", departureAirport='" + departureAirport + '\'' +
                ", arrivalAirport='" + arrivalAirport + '\'' +
                ", price=" + price +
                ", countSeats=" + countSeats +
                '}';
    }
}
