package com.graduatework.ticketease.model.planes.flightPlaneSchedule;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FlightScheduleRepository extends CrudRepository<FlightSchedule, Integer> {
    List<FlightSchedule> findByDepartureFlightDateAndAirplaneRoute_DepartureCityAndAirplaneRoute_ArrivalCity(
            LocalDate departureFlightDate, String departureCity, String arrivalCity);
}
