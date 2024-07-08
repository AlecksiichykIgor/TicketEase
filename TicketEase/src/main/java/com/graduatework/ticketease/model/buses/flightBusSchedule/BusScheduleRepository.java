package com.graduatework.ticketease.model.buses.flightBusSchedule;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BusScheduleRepository extends CrudRepository<BusSchedule, Integer> {
    List<BusSchedule> findByDepartureRouteDateAndBusRoute_DepartureCityAndBusRoute_ArrivalCity(
            LocalDate departureRouteDate, String departureCity, String arrivalCity);
}
