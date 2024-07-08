package com.graduatework.ticketease.model.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientDao {

    @Autowired
    private ClientRepository repository;

    public Client save(Client client) {
        return repository.save(client);
    }

    public List<Client> getAllUsers() {
        List<Client> clients = new ArrayList<>();
        Streamable.of(repository.findAll()).forEach(clients::add);
        return clients;
    }

    public void delete(int userId) {
        repository.deleteById(userId);
    }

    public Client getUserByPhoneNumber(String phoneNumber) {
        return repository.findByPhoneNumber(phoneNumber);
    }
    public Client getUserByEmail(String email) {
        return repository.findByEmail(email);
    }

    public Client getUserById(int userId) {
        Optional<Client> user = repository.findById(userId);
        return user.orElse(null);
    }
}
