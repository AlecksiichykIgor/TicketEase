package com.graduatework.ticketease.model.trains.flightTrainSchedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TrainScheduleDao {

    @Autowired
    private TrainScheduleRepository repository;

    public TrainSchedule save(TrainSchedule trainSchedule) {
        return repository.save(trainSchedule);
    }

    public List<TrainSchedule> getAllTrainSchedules() {
        List<TrainSchedule> trainSchedules = new ArrayList<>();
        Streamable.of(repository.findAll())
                .forEach(trainSchedules::add);
        return trainSchedules;
    }

    public List<TrainSchedule> getTrainSchedules(LocalDate departureFlightDate, String departureCity, String arrivalCity) {
        return repository.findByDepartureFlightDateAndTrainRoute_DepartureCityAndTrainRoute_ArrivalCity(
                departureFlightDate, departureCity, arrivalCity);
    }

    public TrainSchedule getById(int id) {
        Optional<TrainSchedule> trainSchedule = repository.findById(id);
        return trainSchedule.orElse(null);
    }

    public void delete(int scheduleId) {
        repository.deleteById(scheduleId);
    }
}
