package com.sending.sending.service;


import com.sending.sending.entity.ClientEntity;
import com.sending.sending.entity.MessageEntity;
import com.sending.sending.exception.ClientAlreadyExist;
import com.sending.sending.exception.ClientNotFound;
import com.sending.sending.repository.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepo clientRepo;

    @GetMapping
    public List<ClientEntity> getClient() throws ClientNotFound {
        if (clientRepo.findAll() == null){
            throw new ClientNotFound("Небыл найден");
        }

        return clientRepo.findAll();
    }


    @PostMapping
    public ClientEntity createClient(ClientEntity client) throws ClientAlreadyExist {
        if (clientRepo.findByPhone(client.getPhone()) != null) {
            throw new ClientAlreadyExist("Пользователь уже существует");
        }
        return clientRepo.save(client);
    }

    @PutMapping
    public ClientEntity updateClient(@PathVariable Long id, @RequestBody ClientEntity client) throws ClientNotFound {
        ClientEntity updatedClient = clientRepo.findById(id).get();
        if (updatedClient == null) {
            throw new ClientNotFound("Небыл найден");
        }
        updatedClient.setPhone(client.getPhone());
        updatedClient.setPhoneCode(client.getPhoneCode());
        updatedClient.setTags(client.getTags());

        clientRepo.save(updatedClient);
        return updatedClient;
    }

    @DeleteMapping
    public Long deleteClient(@PathVariable Long id){
        clientRepo.deleteById(id);
        return id;
    }
}
