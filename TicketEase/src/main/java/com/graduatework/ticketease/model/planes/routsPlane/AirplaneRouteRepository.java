package com.graduatework.ticketease.model.planes.routsPlane;

import com.graduatework.ticketease.model.trains.routeTrain.TrainRoute;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AirplaneRouteRepository extends CrudRepository<AirplaneRoute, Integer> {
    Optional<AirplaneRoute> findByRouteNumber(String routeNumber);

}
