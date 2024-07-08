package com.graduatework.ticketease.model.trains.flightTrainSchedule;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TrainScheduleRepository extends CrudRepository<TrainSchedule, Integer> {
    List<TrainSchedule> findByDepartureFlightDateAndTrainRoute_DepartureCityAndTrainRoute_ArrivalCity(
            LocalDate departureFlightDate, String departureCity, String arrivalCity);
}
