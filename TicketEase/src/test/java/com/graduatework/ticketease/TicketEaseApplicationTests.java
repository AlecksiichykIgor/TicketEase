package com.graduatework.ticketease;

import com.graduatework.ticketease.model.clients.Client;
import com.graduatework.ticketease.model.clients.ClientDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootTest
class TicketEaseApplicationTests {
//    @Autowired
//    private ClientDao clientDao;
//
//    @Test
//    void addUsers(){
//        List<String> firstNamesEng = new ArrayList<>();
//        firstNamesEng.add("Alexander");
//        firstNamesEng.add("Vitaliy");
//        firstNamesEng.add("Andriy");
//        firstNamesEng.add("Sergiy");
//        firstNamesEng.add("Ivan");
//        firstNamesEng.add("Olena");
//        firstNamesEng.add("Natalia");
//        firstNamesEng.add("Yuri");
//        firstNamesEng.add("Tetiana");
//        firstNamesEng.add("Iryna");
//
//        List<String> lastNamesEng = new ArrayList<>();
//        lastNamesEng.add("Kovalchuk");
//        lastNamesEng.add("Shevchenko");
//        lastNamesEng.add("Bondarenko");
//        lastNamesEng.add("Petrenko");
//        lastNamesEng.add("Kovalenko");
//        lastNamesEng.add("Lysenko");
//        lastNamesEng.add("Shevtsov");
//        lastNamesEng.add("Moroz");
//        lastNamesEng.add("Hryhorenko");
//        lastNamesEng.add("Dmytrenko");
//
//        Random random = new Random();
//
//        List<Client> clients = new ArrayList<>();
//
//        for (int i = 0; i < 10; i++) {
//            String randomFirstName = firstNamesEng.get(random.nextInt(firstNamesEng.size()));
//            String randomLastName = lastNamesEng.get(random.nextInt(lastNamesEng.size()));
//            String randomPhoneNumber = generatePhoneNumber();
//            String randomEmail = "user" + i + "@example.com";
//            String randomPassword = "password" + i;
//
//            Client client = new Client(randomFirstName, randomLastName, randomPhoneNumber, randomEmail, randomPassword);
//            clientDao.save(client);
//            clients.add(client);
//        }
//
//        for (int i = 0; i < 3; i++) {
//            String randomFirstName = firstNamesEng.get(random.nextInt(firstNamesEng.size()));
//            String randomLastName = lastNamesEng.get(random.nextInt(lastNamesEng.size()));
//            String randomPhoneNumber = generatePhoneNumber();
//            String randomEmail = "admin" + i + "@example.com";
//            String randomPassword = "password" + i;
//            String randomAdminPassword = "adminPassword" + i;
//            Client client = new Client(randomFirstName, randomLastName, randomPhoneNumber, randomEmail, randomPassword, randomAdminPassword);
//            clientDao.save(client);
//            clients.add(client);
//        }
//        for (Client client : clients) {
//            System.out.println(client);
//        }
//    }
//
//    // Генерация номера телефона в формате Украины
//    private static String generatePhoneNumber() {
//        Random random = new Random();
//        StringBuilder phoneNumber = new StringBuilder("+380");
//
//        for (int i = 0; i < 9; i++) {
//            phoneNumber.append(random.nextInt(10));
//        }
//
//        return phoneNumber.toString();
//    }

}
