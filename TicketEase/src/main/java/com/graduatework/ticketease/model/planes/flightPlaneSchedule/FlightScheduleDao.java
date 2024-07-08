package com.graduatework.ticketease.model.planes.flightPlaneSchedule;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

@Service
public class FlightScheduleDao {
    @Autowired
    private  FlightScheduleRepository repository;

    public FlightSchedule save(FlightSchedule flightSchedule) {
        return repository.save(flightSchedule);
    }

    public List<FlightSchedule> getAllFlightSchedules() {
        List<FlightSchedule> flightSchedules = new ArrayList<>();
        Streamable.of(repository.findAll())
                .forEach(flightSchedules::add);
        return flightSchedules;
    }

    public List<FlightSchedule> getFlightSchedules(LocalDate departureFlightDate, String departureCity, String arrivalCity) {
        return repository.findByDepartureFlightDateAndAirplaneRoute_DepartureCityAndAirplaneRoute_ArrivalCity(
                departureFlightDate, departureCity, arrivalCity);
    }

    public FlightSchedule getById(int id) {
        Optional<FlightSchedule> flightSchedule = repository.findById(id);
        return flightSchedule.orElse(null);
    }

    public void delete(int scheduleId) {
        repository.deleteById(scheduleId);
    }
}
