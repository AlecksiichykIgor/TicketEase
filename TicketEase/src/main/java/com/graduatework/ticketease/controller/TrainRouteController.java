package com.graduatework.ticketease.controller;

import com.graduatework.ticketease.model.buses.routsbus.BusRoute;
import com.graduatework.ticketease.model.planes.flightPlaneSchedule.FlightSchedule;
import com.graduatework.ticketease.model.trains.flightTrainSchedule.TrainSchedule;
import com.graduatework.ticketease.model.trains.flightTrainSchedule.TrainScheduleDao;
import com.graduatework.ticketease.model.trains.routeTrain.TrainRoute;
import com.graduatework.ticketease.model.trains.routeTrain.TrainRouteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/trains")
public class TrainRouteController {

    @Autowired
    private TrainRouteDao trainRouteDao;

    @Autowired
    private TrainScheduleDao trainScheduleDao;

    // Get train schedule by departure city, arrival city, and departure date
    @GetMapping("/schedules")
    public List<TrainSchedule> getTrainSchedule(
            @RequestParam LocalDate departureDate,
            @RequestParam String departureCity,
            @RequestParam String arrivalCity) {
        return trainScheduleDao.getTrainSchedules(departureDate, departureCity, arrivalCity);
    }
    // Get bus schedule by ID
    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<TrainSchedule> getTrainScheduleById(@PathVariable Integer scheduleId) {
        TrainSchedule trainSchedule = trainScheduleDao.getById(scheduleId);
        if (trainSchedule != null) {
            return ResponseEntity.ok(trainSchedule);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    // Get train route by ID
    @GetMapping("/routes/{id}")
    public TrainRoute getTrainRouteById(@PathVariable int id) {
        return trainRouteDao.getById(id);
    }

    // Get train route by route number
    @GetMapping("/routes/number/{routeNumber}")
    public TrainRoute getTrainRouteByNumber(@PathVariable String routeNumber) {
        return trainRouteDao.getTrainRouteByNumber(routeNumber);
    }

    // Get all train routes
    @GetMapping("/routes")
    public List<TrainRoute> getAllTrainRoutes() {
        return trainRouteDao.getAllTrainRoutes();
    }

    // Create a new train route
    @PostMapping("/routes/save")
    public ResponseEntity<TrainRoute> createTrainRoute(@RequestBody TrainRoute trainRoute) {
        TrainRoute createdRoute = trainRouteDao.save(trainRoute);
        return ResponseEntity.status(HttpStatus.CREATED).body((createdRoute));
    }

    // Update a train route
    @PutMapping("/routes/update/{id}")
    public ResponseEntity<TrainRoute> updateRoute(@PathVariable Integer routeId, @RequestBody TrainRoute updatedRoute) {
        TrainRoute existingRoute = trainRouteDao.getById(routeId);
        if (existingRoute == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        existingRoute.setRouteNumber(updatedRoute.getRouteNumber());
        existingRoute.setDepartureCity(updatedRoute.getDepartureCity());
        existingRoute.setArrivalCity(updatedRoute.getArrivalCity());
        existingRoute.setDepartureStation(updatedRoute.getDepartureStation());
        existingRoute.setArrivalStation(updatedRoute.getArrivalStation());
        existingRoute.setPrice(updatedRoute.getPrice());
        existingRoute.setCountWagon(updatedRoute.getCountWagon());

        trainRouteDao.save(existingRoute);
        return ResponseEntity.ok(existingRoute);
    }

    // Create a new train schedule for a route
    @PostMapping("/schedules/save")
    public ResponseEntity<TrainSchedule> createTrainSchedule(@RequestBody TrainSchedule trainSchedule) {
        TrainSchedule createdSchedule = trainScheduleDao.save(trainSchedule);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSchedule);
    }

    // Update a train schedule
    @PutMapping("/schedules/update/{id}")
    public ResponseEntity<TrainSchedule> updateTrainSchedule(@PathVariable int id, @RequestBody TrainSchedule trainSchedule) {
        TrainSchedule existingSchedule = trainScheduleDao.getById(id);
        if (existingSchedule == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        existingSchedule.setTrainRoute(trainSchedule.getTrainRoute());
        existingSchedule.setArrivalTime(trainSchedule.getArrivalTime());
        existingSchedule.setArrivalFlightDate(trainSchedule.getArrivalFlightDate());
        existingSchedule.setDepartureTime(trainSchedule.getDepartureTime());
        existingSchedule.setDepartureFlightDate(trainSchedule.getDepartureFlightDate());

        trainScheduleDao.save(existingSchedule);
        return ResponseEntity.ok(existingSchedule);
    }
}
