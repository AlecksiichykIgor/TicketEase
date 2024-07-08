package com.graduatework.ticketease.model.trains.routeTrain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainRouteRepository extends CrudRepository<TrainRoute, Integer> {
    Optional<TrainRoute> findByRouteNumber(String routeNumber);
}
