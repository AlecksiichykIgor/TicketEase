package com.graduatework.ticketease.model.trains.routeTrain;

import com.graduatework.ticketease.model.buses.routsbus.BusRoute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.util.Streamable;

@Service
public class TrainRouteDao {

    @Autowired
    private TrainRouteRepository repository;

    public TrainRoute save(TrainRoute trainRoute) {
        return repository.save(trainRoute);
    }
    public TrainRoute getTrainRouteByNumber(String routeNumber) {
        return repository.findByRouteNumber(routeNumber).orElse(null);
    }
    public List<TrainRoute> getAllTrainRoutes() {
        List<TrainRoute> trainRoutes = new ArrayList<>();
        Streamable.of(repository.findAll())
                .forEach(trainRoutes::add);
        return trainRoutes;
    }

    public TrainRoute getById(int id) {
        Optional<TrainRoute> trainRoute = repository.findById(id);
        return trainRoute.orElse(null);
    }

    public void delete(int routeId) {
        repository.deleteById(routeId);
    }
}

