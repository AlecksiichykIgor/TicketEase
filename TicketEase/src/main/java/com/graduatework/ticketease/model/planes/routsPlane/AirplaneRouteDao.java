package com.graduatework.ticketease.model.planes.routsPlane;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.graduatework.ticketease.model.trains.routeTrain.TrainRoute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

@Service
public class AirplaneRouteDao {
    @Autowired
    private AirplaneRouteRepository repository;

    public AirplaneRoute save(AirplaneRoute airplaneRoute) {
        return repository.save(airplaneRoute);
    }

    public AirplaneRoute getAirplaneRouteByNumber(String routeNumber) {
        return repository.findByRouteNumber(routeNumber).orElse(null);
    }

    public List<AirplaneRoute> getAllAirplaneRoutes() {
        List<AirplaneRoute> airplaneRoutes = new ArrayList<>();
        Streamable.of(repository.findAll())
                .forEach(airplaneRoutes::add);
        return airplaneRoutes;
    }
    public AirplaneRoute getById(int id) {
        Optional<AirplaneRoute> airplaneRoute = repository.findById(id);
        return airplaneRoute.orElse(null);
    }
    public void delete(int airplaneRouteId) {
        repository.deleteById(airplaneRouteId);
    }
}
