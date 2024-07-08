package com.graduatework.ticketease.model.trains.ticketsTrain;

import com.graduatework.ticketease.model.buses.ticketsbus.TicketsBus;
import com.graduatework.ticketease.model.planes.ticketsPlane.TicketsPlane;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainTicketRepository extends CrudRepository<TicketTrain, Integer> {
    List<TicketTrain> findByClient_Id(int clientId);

    List<TicketTrain> findByTrainSchedule_ScheduleIdAndStatus(int scheduleId, String status);

    List<TicketTrain> findByClient_IdAndStatus(int clientId, String status);

    List<TicketTrain> findByStatus(String status);

}
