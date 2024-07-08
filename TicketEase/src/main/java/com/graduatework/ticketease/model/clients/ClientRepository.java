package com.graduatework.ticketease.model.clients;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<Client, Integer> {
    Client findByPhoneNumber(String phoneNumber);
    Client findByEmail(String email);
}
