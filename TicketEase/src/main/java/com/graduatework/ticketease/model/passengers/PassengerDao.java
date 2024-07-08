package com.graduatework.ticketease.model.passengers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PassengerDao {

    @Autowired
    private PassengerRepository repository;

    public Passenger save(Passenger passenger) {
        return repository.save(passenger);
    }

    public List<Passenger> getAllPassengers() {
        List<Passenger> passengers = new ArrayList<>();
        Streamable.of(repository.findAll()).forEach(passengers::add);
        return passengers;
    }

    public void delete(int passengerId) {
        repository.deleteById(passengerId);
    }

    public Passenger getPassengerById(int passengerId) {
        Optional<Passenger> passenger = repository.findById(passengerId);
        return passenger.orElse(null); // Return passenger if found, otherwise return null
    }
}
