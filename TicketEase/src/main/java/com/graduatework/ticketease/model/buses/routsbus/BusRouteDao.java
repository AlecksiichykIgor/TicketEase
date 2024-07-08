package com.graduatework.ticketease.model.buses.routsbus;

import com.graduatework.ticketease.model.clients.Client;
import com.graduatework.ticketease.model.trains.routeTrain.TrainRoute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class BusRouteDao {
    @Autowired
    private BusRouteRepository repository;

    public BusRoute save(BusRoute busRoute) {
        return repository.save(busRoute);
    }

    public List<BusRoute> getAllBusRoutes() {
        List<BusRoute> busRoutes = new ArrayList<>();
        Streamable.of(repository.findAll())
                .forEach(busRoutes::add);
        return busRoutes;    }

    public BusRoute getBusRouteByNumber(String routeNumber) {
        return repository.findByRouteNumber(routeNumber).orElse(null);
    }

    public BusRoute getById(int id) {
        Optional<BusRoute> busRoute = repository.findById(id);
        return busRoute.orElse(null);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }

    public List<BusRoute> getRoutesByCities(String departureCity, String arrivalCity) {
        return repository.findByDepartureCityAndArrivalCity(departureCity, arrivalCity);
    }
}
