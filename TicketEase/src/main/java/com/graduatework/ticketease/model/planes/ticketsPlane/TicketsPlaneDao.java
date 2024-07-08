package com.graduatework.ticketease.model.planes.ticketsPlane;

import com.graduatework.ticketease.model.buses.ticketsbus.TicketsBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketsPlaneDao {

    @Autowired
    private TicketsPlaneRepository repository;

    public TicketsPlane save(TicketsPlane ticketsPlane) {
        return repository.save(ticketsPlane);
    }

    public List<TicketsPlane> getAllTickets() {
        List<TicketsPlane> tickets = new ArrayList<>();
        Streamable.of(repository.findAll())
                .forEach(tickets::add);
        return tickets;
    }

    public List<TicketsPlane> getTicketsByClientId(int clientId) {
        return repository.findByClient_Id(clientId);
    }

    public void delete(int ticketId) {
        repository.deleteById(ticketId);
    }
}
