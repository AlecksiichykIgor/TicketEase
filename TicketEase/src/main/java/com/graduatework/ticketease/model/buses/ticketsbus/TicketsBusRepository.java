package com.graduatework.ticketease.model.buses.ticketsbus;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketsBusRepository extends CrudRepository<TicketsBus, Integer> {
    List<TicketsBus> findByClient_Id(int clientId);

    List<TicketsBus> findByBusSchedule_ScheduleIdAndStatus(int scheduleId, String status);

    List<TicketsBus> findByClient_IdAndStatus(int clientId, String status);

    List<TicketsBus> findByStatus(String status);

}
