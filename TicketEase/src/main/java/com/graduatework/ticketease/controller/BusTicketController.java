package com.graduatework.ticketease.controller;

import com.graduatework.ticketease.model.buses.ticketsbus.TicketsBus;
import com.graduatework.ticketease.model.buses.ticketsbus.TicketsBusDao;
import com.graduatework.ticketease.model.buses.ticketsbus.TicketsBusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bus/tickets")
public class BusTicketController {

    private final TicketsBusRepository ticketsBusRepository;

    @Autowired
    private TicketsBusDao ticketsBusDao;

    @Autowired
    public BusTicketController(TicketsBusRepository ticketsBusRepository) {
        this.ticketsBusRepository = ticketsBusRepository;
    }

    @GetMapping("/client/{clientId}/active")
    public List<TicketsBus> getActiveTicketsByClientId(@PathVariable int clientId) {
        return ticketsBusRepository.findByClient_IdAndStatus(clientId, "Активний");
    }

    @GetMapping("/active")
    public List<TicketsBus> getActiveTickets() {
        return ticketsBusRepository.findByStatus("Активний");
    }

    @GetMapping("/schedule/{scheduleId}")
    public List<TicketsBus> getActiveTicketsByScheduleId(@PathVariable int scheduleId) {
        return ticketsBusRepository.findByBusSchedule_ScheduleIdAndStatus(scheduleId, "Активний");
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveTicket(@RequestBody TicketsBus ticket) {
        TicketsBus savedTicket = ticketsBusDao.save(ticket);
        if (savedTicket != null) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();        }
    }
    @PutMapping("/{ticketId}/return")
    public void returnTicket(@PathVariable int ticketId) {
        TicketsBus ticket = ticketsBusRepository.findById(ticketId).orElse(null);

        if (ticket != null) {
            ticket.setStatus("Повернуто");
            ticketsBusRepository.save(ticket);
        } else {
            System.out.println("Билет с идентификатором " + ticketId + " не найден.");
        }
    }
}
