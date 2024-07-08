package com.graduatework.ticketease.controller;

import com.graduatework.ticketease.model.clients.Client;
import com.graduatework.ticketease.model.clients.ClientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ClientDao clientDao;

    @GetMapping("/clients")
    public List<Client> getAllClients() {
        return clientDao.getAllUsers();
    }

    @GetMapping("/clients/{clientId}")
    public ResponseEntity<Client> getClientById(@PathVariable("clientId") Integer clientId) {
        Client client = clientDao.getUserById(clientId);
        if (client != null) {
            return ResponseEntity.ok(client);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/clients/{clientId}")
    public ResponseEntity<String> updateClient(@PathVariable int clientId, @RequestBody Client updatedClient) {
        Client existingClient = clientDao.getUserById(clientId);
        if (existingClient == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client with this ID not found");
        }

        existingClient.setFirstName(updatedClient.getFirstName());
        existingClient.setLastName(updatedClient.getLastName());
        existingClient.setPhoneNumber(updatedClient.getPhoneNumber());
        existingClient.setEmail(updatedClient.getEmail());
        existingClient.setPassword(updatedClient.getPassword());

        clientDao.save(existingClient);
        return ResponseEntity.status(HttpStatus.OK).body("Client updated successfully");
    }

    @DeleteMapping("/clients/{clientId}")
    public ResponseEntity<String> deleteClient(@PathVariable int clientId) {
        Client existingClient = clientDao.getUserById(clientId);
        if (existingClient == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client with this ID not found");
        }
        clientDao.delete(existingClient.getId());
        return ResponseEntity.status(HttpStatus.OK).body("Client deleted successfully");
    }

    @PostMapping("/clients/adminLogin")
    public ResponseEntity<String> adminLogin(@RequestBody Client admin) {
        Client existingAdmin = clientDao.getUserByPhoneNumber(admin.getPhoneNumber());
        if (existingAdmin == null || existingAdmin.getAdminPass() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin not found");
        }
        if (!existingAdmin.getAdminPass().equals(admin.getAdminPass())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect admin password");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Admin login successful");
    }

    @PutMapping("/admin/adminUpdate/{clientId}")
    public ResponseEntity<String> updateAdmin(@PathVariable int clientId, @RequestBody Client updatedAdmin) {
        Client existingAdmin = clientDao.getUserById(clientId);
        if (existingAdmin == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin with this ID not found");
        }

        existingAdmin.setFirstName(updatedAdmin.getFirstName());
        existingAdmin.setLastName(updatedAdmin.getLastName());
        existingAdmin.setPhoneNumber(updatedAdmin.getPhoneNumber());
        existingAdmin.setEmail(updatedAdmin.getEmail());
        existingAdmin.setPassword(updatedAdmin.getPassword());
        existingAdmin.setAdminPass(updatedAdmin.getAdminPass());

        clientDao.save(existingAdmin);
        return ResponseEntity.status(HttpStatus.OK).body("Admin updated successfully");
    }
}
