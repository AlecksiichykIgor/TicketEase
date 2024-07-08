package com.graduatework.ticketease.model.buses.ticketsbus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.util.Streamable;

@Service
public class TicketsBusDao {

    @Autowired
    private TicketsBusRepository repository;

    public TicketsBus save(TicketsBus ticket) {
        return repository.save(ticket);
    }

    public List<TicketsBus> getAllTickets() {
        List<TicketsBus> tickets = new ArrayList<>();
        Streamable.of(repository.findAll())
                .forEach(tickets::add);
        return tickets;
    }

    public void delete(int ticketId) {
        repository.deleteById(ticketId);
    }
    public List<TicketsBus> getTicketsByClientId(int clientId) {
        return repository.findByClient_Id(clientId);
    }

}
