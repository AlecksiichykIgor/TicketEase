package com.graduatework.ticketease.model.buses.flightBusSchedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BusScheduleDao {

    @Autowired
    private BusScheduleRepository repository;

    public BusSchedule save(BusSchedule busSchedule) {
        return repository.save(busSchedule);
    }

    public List<BusSchedule> getAllFlightSchedules() {
        List<BusSchedule> busSchedules = new ArrayList<>();
        Streamable.of(repository.findAll())
                .forEach(busSchedules::add);
        return busSchedules;
    }
    public List<BusSchedule> getBusSchedules(LocalDate departureRouteDate, String departureCity, String arrivalCity) {
        return repository.findByDepartureRouteDateAndBusRoute_DepartureCityAndBusRoute_ArrivalCity(
                departureRouteDate, departureCity, arrivalCity);
    }
    public void delete(int scheduleId) {
        repository.deleteById(scheduleId);
    }

    public BusSchedule getBusScheduleById(int scheduleId) {
        Optional<BusSchedule> optionalBusSchedule = repository.findById(scheduleId);
        return optionalBusSchedule.orElse(null);
    }
}
