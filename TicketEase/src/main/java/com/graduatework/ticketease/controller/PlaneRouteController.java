package com.graduatework.ticketease.controller;

import com.graduatework.ticketease.model.buses.flightBusSchedule.BusSchedule;
import com.graduatework.ticketease.model.planes.flightPlaneSchedule.FlightSchedule;
import com.graduatework.ticketease.model.planes.flightPlaneSchedule.FlightScheduleDao;
import com.graduatework.ticketease.model.planes.routsPlane.AirplaneRoute;
import com.graduatework.ticketease.model.planes.routsPlane.AirplaneRouteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/planes")
public class PlaneRouteController {

    @Autowired
    private AirplaneRouteDao airplaneRouteDao;

    @Autowired
    private FlightScheduleDao flightScheduleDao;

    // Get flight schedule by departure city, arrival city, and departure date
    @GetMapping("/schedules")
    public List<FlightSchedule> getFlightSchedule(
            @RequestParam LocalDate departureDate,
            @RequestParam String departureCity,
            @RequestParam String arrivalCity) {
        return flightScheduleDao.getFlightSchedules(departureDate, departureCity, arrivalCity);
    }

    // Get airplane route by ID
    @GetMapping("/routes/{id}")
    public AirplaneRoute getAirplaneRouteById(@PathVariable int id) {
        return airplaneRouteDao.getById(id);
    }

    // Get bus schedule by ID
    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<FlightSchedule> getFlightScheduleById(@PathVariable Integer scheduleId) {
        FlightSchedule flightSchedule = flightScheduleDao.getById(scheduleId);
        if (flightSchedule != null) {
            return ResponseEntity.ok(flightSchedule);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Get airplane route by route number
    @GetMapping("/routes/number/{routeNumber}")
    public AirplaneRoute getAirplaneRouteByNumber(@PathVariable String routeNumber) {
        return airplaneRouteDao.getAirplaneRouteByNumber(routeNumber);
    }

    // Get all airplane routes
    @GetMapping("/routes")
    public List<AirplaneRoute> getAllAirplaneRoutes() {
        return airplaneRouteDao.getAllAirplaneRoutes();
    }

    // Create a new airplane route
    @PostMapping("/routes/save")
    public ResponseEntity<AirplaneRoute> createAirplaneRoute(@RequestBody AirplaneRoute airplaneRoute) {
        AirplaneRoute createdRoute = airplaneRouteDao.save(airplaneRoute);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRoute);
    }

    // Update an airplane route
    @PutMapping("/routes/update/{id}")
    public ResponseEntity<AirplaneRoute> updateAirplaneRoute(
            @PathVariable int id, @RequestBody AirplaneRoute updatedRoute) {
        AirplaneRoute existingRoute = airplaneRouteDao.getById(id);
        if (existingRoute == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        existingRoute.setRouteNumber(updatedRoute.getRouteNumber());
        existingRoute.setDepartureCity(updatedRoute.getDepartureCity());
        existingRoute.setArrivalCity(updatedRoute.getArrivalCity());
        existingRoute.setDepartureAirport(updatedRoute.getDepartureAirport());
        existingRoute.setArrivalAirport(updatedRoute.getArrivalAirport());
        existingRoute.setPrice(updatedRoute.getPrice());
        existingRoute.setCountSeats(updatedRoute.getCountSeats());

        airplaneRouteDao.save(existingRoute);
        return ResponseEntity.ok(existingRoute);
    }

    // Create a new flight schedule for a route
    @PostMapping("/schedules/save")
    public ResponseEntity<FlightSchedule> createFlightSchedule(@RequestBody FlightSchedule flightSchedule) {
        FlightSchedule createdSchedule = flightScheduleDao.save(flightSchedule);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSchedule);
    }

    // Update a flight schedule
    @PutMapping("/schedules/update/{id}")
    public ResponseEntity<FlightSchedule> updateFlightSchedule(
            @PathVariable int id, @RequestBody FlightSchedule flightSchedule) {
        FlightSchedule existingSchedule = flightScheduleDao.getById(id);
        if (existingSchedule == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        existingSchedule.setAirplaneRoute(flightSchedule.getAirplaneRoute());
        existingSchedule.setArrivalTime(flightSchedule.getArrivalTime());
        existingSchedule.setArrivalFlightDate(flightSchedule.getArrivalFlightDate());
        existingSchedule.setDepartureTime(flightSchedule.getDepartureTime());
        existingSchedule.setDepartureFlightDate(flightSchedule.getDepartureFlightDate());

        flightScheduleDao.save(existingSchedule);
        return ResponseEntity.ok(existingSchedule);
    }
}
