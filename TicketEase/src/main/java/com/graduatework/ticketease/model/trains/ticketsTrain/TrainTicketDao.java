package com.graduatework.ticketease.model.trains.ticketsTrain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.util.Streamable;

@Service
public class TrainTicketDao {

    @Autowired
    private TrainTicketRepository repository;

    public TicketTrain save(TicketTrain ticketTrain) {
        return repository.save(ticketTrain);
    }

    public List<TicketTrain> getAllTrainTickets() {
        List<TicketTrain> ticketTrains = new ArrayList<>();
        Streamable.of(repository.findAll())
                .forEach(ticketTrains::add);
        return ticketTrains;
    }

    public void delete(int ticketId) {
        repository.deleteById(ticketId);
    }
}
