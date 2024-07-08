package com.graduatework.ticketease.model.planes.ticketsPlane;

import com.graduatework.ticketease.model.trains.ticketsTrain.TicketTrain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketsPlaneRepository extends JpaRepository<TicketsPlane, Integer> {
    List<TicketsPlane> findByClient_Id(int clientId);

    List<TicketsPlane> findByFlightSchedule_ScheduleIdAndStatus(int scheduleId, String status);

    List<TicketsPlane> findByClient_IdAndStatus(int clientId, String status);

    List<TicketsPlane> findByStatus(String status);
}
