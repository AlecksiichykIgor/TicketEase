package com.graduatework.ticketease.controller;

import com.graduatework.ticketease.model.clients.Client;
import com.graduatework.ticketease.model.clients.ClientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private ClientDao clientDao;

    @GetMapping("/get-all")
    public List<Client> getAllUsers() {
        return clientDao.getAllUsers();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Client> getUserById(@PathVariable("userId") Integer userId) {
        Client client = clientDao.getUserById(userId);
        if (client != null) {
            return ResponseEntity.ok(client);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{userPhone}")
    public ResponseEntity<Client> getUserByPhone(@PathVariable("userPhone") String userPhone) {
        Client client = clientDao.getUserByPhoneNumber(userPhone);
        if (client != null) {
            return ResponseEntity.ok(client);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/registration")
    public ResponseEntity<String> registerClient(@RequestBody Client client) {
        Client existingClient = clientDao.getUserByPhoneNumber(client.getPhoneNumber());
        if (existingClient != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Client with this phone number already exists");
        }
        if (client.getEmail() != null) {
            Client existingClientByEmail = clientDao.getUserByEmail(client.getEmail());
            if (existingClientByEmail != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Client with this email already exists");
            }
        }
        // Сохраняем нового клиента
        Client savedClient = clientDao.save(client);

        if (savedClient != null) {
            return ResponseEntity.status(HttpStatus.OK).body("Client registered successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error registering client");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody Client client) {
        Client existingClient = clientDao.getUserByPhoneNumber(client.getPhoneNumber());
        if (existingClient == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");
        }
        if (!existingClient.getPassword().equals(client.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Login successful");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUser(@PathVariable int id, @RequestBody Client updatedClient) {
        Client existingClient = clientDao.getUserById(id);
        if (existingClient == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with this ID not found");
        }

        existingClient.setFirstName(updatedClient.getFirstName());
        existingClient.setLastName(updatedClient.getLastName());
        existingClient.setPhoneNumber(updatedClient.getPhoneNumber());
        existingClient.setEmail(updatedClient.getEmail());
        existingClient.setPassword(updatedClient.getPassword());

        clientDao.save(existingClient);
        return ResponseEntity.status(HttpStatus.OK).body("User updated successfully");
    }

}
