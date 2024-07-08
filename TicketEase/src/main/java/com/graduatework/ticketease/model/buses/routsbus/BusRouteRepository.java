package com.graduatework.ticketease.model.buses.routsbus;

import com.graduatework.ticketease.model.trains.routeTrain.TrainRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BusRouteRepository extends JpaRepository<BusRoute, Integer> {
    List<BusRoute> findByDepartureCityAndArrivalCity(String departureCity, String arrivalCity);
    Optional<BusRoute> findByRouteNumber(String routeNumber);

}
