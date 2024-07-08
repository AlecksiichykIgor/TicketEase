package com.graduatework.ticketease.controller;

import com.graduatework.ticketease.model.buses.ticketsbus.TicketsBus;
import com.graduatework.ticketease.model.trains.ticketsTrain.TicketTrain;
import com.graduatework.ticketease.model.trains.ticketsTrain.TrainTicketDao;
import com.graduatework.ticketease.model.trains.ticketsTrain.TrainTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trains/tickets")
public class TrainTicketController {
    private final TrainTicketRepository trainTicketRepository;

    @Autowired
    private TrainTicketDao trainTicketDao;

    @Autowired
    public TrainTicketController(TrainTicketRepository trainTicketRepository) {
        this.trainTicketRepository = trainTicketRepository;
    }

    @GetMapping("/client/{clientId}/active")
    public List<TicketTrain> getActiveTicketsByClientId(@PathVariable int clientId) {
        return trainTicketRepository.findByClient_IdAndStatus(clientId, "Активний");
    }

    @GetMapping("/active")
    public List<TicketTrain> getActiveTickets() {
        return trainTicketRepository.findByStatus("Активний");
    }

    @GetMapping("/schedule/{scheduleId}")
    public List<TicketTrain> getActiveTicketsByScheduleId(@PathVariable int scheduleId) {
        return trainTicketRepository.findByTrainSchedule_ScheduleIdAndStatus(scheduleId, "Активний");
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveTicket(@RequestBody TicketTrain ticket) {
        TicketTrain savedTicket = trainTicketDao.save(ticket);
        if (savedTicket != null) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();        }
    }
    @PutMapping("/{ticketId}/return")
    public void returnTicket(@PathVariable int ticketId) {
        TicketTrain ticket = trainTicketRepository.findById(ticketId).orElse(null);

        if (ticket != null) {
            ticket.setStatus("Повернуто");
            trainTicketRepository.save(ticket);
        } else {
            System.out.println("Билет с идентификатором " + ticketId + " не найден.");
        }
    }
}
