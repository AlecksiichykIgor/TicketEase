package com.graduatework.ticketease.controller;

import com.graduatework.ticketease.model.planes.ticketsPlane.TicketsPlane;
import com.graduatework.ticketease.model.planes.ticketsPlane.TicketsPlaneDao;
import com.graduatework.ticketease.model.planes.ticketsPlane.TicketsPlaneRepository;
import com.graduatework.ticketease.model.trains.ticketsTrain.TicketTrain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plane/tickets")
public class PlaneTicketController {

    private final TicketsPlaneRepository ticketsPlaneRepository;

    @Autowired
    private TicketsPlaneDao ticketsPlaneDao;

    @Autowired
    public PlaneTicketController(TicketsPlaneRepository ticketsPlaneRepository) {
        this.ticketsPlaneRepository = ticketsPlaneRepository;
    }

    @GetMapping("/client/{clientId}/active")
    public List<TicketsPlane> getActiveTicketsByClientId(@PathVariable int clientId) {
        return ticketsPlaneRepository.findByClient_IdAndStatus(clientId, "Активний");
    }

    @GetMapping("/active")
    public List<TicketsPlane> getActiveTickets() {
        return ticketsPlaneRepository.findByStatus("Активний");
    }

    @GetMapping("/schedule/{scheduleId}")
    public List<TicketsPlane> getActiveTicketsByScheduleId(@PathVariable int scheduleId) {
        return ticketsPlaneRepository.findByFlightSchedule_ScheduleIdAndStatus(scheduleId, "Активний");
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveTicket(@RequestBody TicketsPlane ticket) {
        TicketsPlane savedTicket = ticketsPlaneDao.save(ticket);
        if (savedTicket != null) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();        }
    }
    @PutMapping("/{ticketId}/return")
    public void returnTicket(@PathVariable int ticketId) {
        TicketsPlane ticket = ticketsPlaneRepository.findById(ticketId).orElse(null);

        if (ticket != null) {
            ticket.setStatus("Повернуто");
            ticketsPlaneRepository.save(ticket);
        } else {
            System.out.println("Билет с идентификатором " + ticketId + " не найден.");
        }
    }
}
