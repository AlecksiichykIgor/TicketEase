package com.graduatework.ticketease.controller;

import com.graduatework.ticketease.model.buses.flightBusSchedule.BusSchedule;
import com.graduatework.ticketease.model.buses.flightBusSchedule.BusScheduleDao;
import com.graduatework.ticketease.model.buses.routsbus.BusRoute;
import com.graduatework.ticketease.model.buses.routsbus.BusRouteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/buses")
public class BusRouteController {

    @Autowired
    private BusScheduleDao busScheduleDao;

    @Autowired
    private BusRouteDao busRouteDao;

    @GetMapping("/schedules")
    public List<BusSchedule> getBusSchedules(
            @RequestParam LocalDate departureDate,
            @RequestParam String departureCity,
            @RequestParam String arrivalCity) {
        return busScheduleDao.getBusSchedules(departureDate, departureCity, arrivalCity);
    }

    @GetMapping("/schedules/all")
    public ResponseEntity<List<BusSchedule>> getAllSchedules() {
        List<BusSchedule> schedules = busScheduleDao.getAllFlightSchedules();
        return ResponseEntity.ok(schedules);
    }

    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<BusSchedule> getBusScheduleById(@PathVariable Integer scheduleId) {
        BusSchedule busSchedule = busScheduleDao.getBusScheduleById(scheduleId);
        if (busSchedule != null) {
            return ResponseEntity.ok(busSchedule);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/routes")
    public List<BusRoute> getAllRoutes() {
        return busRouteDao.getAllBusRoutes();
    }

    @GetMapping("/routes/{routeId}")
    public ResponseEntity<BusRoute> getRouteById(@PathVariable Integer routeId) {
        BusRoute busRoute = busRouteDao.getById(routeId);
        if (busRoute != null) {
            return ResponseEntity.ok(busRoute);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/routes/number/{routeNumber}")
    public ResponseEntity<BusRoute> getBusRouteByNumber(@PathVariable String routeNumber) {
        BusRoute busRoute = busRouteDao.getBusRouteByNumber(routeNumber);
        if (busRoute != null) {
            return ResponseEntity.ok(busRoute);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/routes/search")
    public List<BusRoute> searchRoutes(
            @RequestParam String departureCity,
            @RequestParam String arrivalCity) {
        return busRouteDao.getRoutesByCities(departureCity, arrivalCity);
    }

    @PostMapping("/routes/save")
    public ResponseEntity<BusRoute> createRoute(@RequestBody BusRoute busRoute) {
        BusRoute createdRoute = busRouteDao.save(busRoute);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRoute);
    }

    @PutMapping("/routes/update/{routeId}")
    public ResponseEntity<BusRoute> updateRoute(
            @PathVariable Integer routeId, @RequestBody BusRoute updatedRoute) {
        BusRoute existingRoute = busRouteDao.getById(routeId);
        if (existingRoute == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        existingRoute.setRouteNumber(updatedRoute.getRouteNumber());
        existingRoute.setDepartureCity(updatedRoute.getDepartureCity());
        existingRoute.setArrivalCity(updatedRoute.getArrivalCity());
        existingRoute.setDepartureStation(updatedRoute.getDepartureStation());
        existingRoute.setArrivalStation(updatedRoute.getArrivalStation());
        existingRoute.setPrice(updatedRoute.getPrice());
        existingRoute.setCountSeats(updatedRoute.getCountSeats());

        busRouteDao.save(existingRoute);
        return ResponseEntity.ok(existingRoute);
    }

    @DeleteMapping("/routes/delete/{routeId}")
    public ResponseEntity<String> deleteRoute(@PathVariable Integer routeId) {
        BusRoute existingRoute = busRouteDao.getById(routeId);
        if (existingRoute == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Маршрут с указанным ID не найден.");
        }
        busRouteDao.delete(routeId);
        return ResponseEntity.ok("Маршрут успешно удален.");
    }
}
