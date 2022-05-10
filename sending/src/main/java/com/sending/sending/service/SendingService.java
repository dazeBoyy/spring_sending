package com.sending.sending.service;


import com.sending.sending.entity.ClientEntity;
import com.sending.sending.entity.MessageEntity;
import com.sending.sending.entity.SendingEntity;
import com.sending.sending.exception.ClientNotFound;
import com.sending.sending.exception.SendingAlreadyExist;
import com.sending.sending.repository.ClientRepo;
import com.sending.sending.repository.MessageRepo;
import com.sending.sending.repository.SendingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class SendingService {

    @Autowired
    private SendingRepo sendingRepo;

    @Autowired
    private ClientRepo clientRepo;

    @Autowired
    private MessageService messageService;

    @Autowired
    private MessageRepo messageRepo;

    @GetMapping()
    public List<SendingEntity> getSendings() throws ClientNotFound {
        if (sendingRepo.findAll() == null){
            throw new ClientNotFound("Небыл найден");
        }

        return sendingRepo.findAll();
    }
    @GetMapping()
    public SendingEntity getSending(@PathVariable Long id) throws ClientNotFound {
        if (sendingRepo.findById(id).get() == null){
            throw new ClientNotFound("Небыл найден");
        }

        return sendingRepo.findById(id).get();
    }

    @PostMapping
    public SendingEntity createSending(Long client_id, SendingEntity sending) throws SendingAlreadyExist {
        ClientEntity client = clientRepo.findById(client_id).get();
        if (client.getId() == client_id){
            sending.setEndDateTime(LocalDateTime.now());
            MessageEntity message = new MessageEntity();
            message.setClient(client);
            message.setSending(sending);
            sending.setMessage(message);
            return sendingRepo.save(sending);
        }else {
            throw new SendingAlreadyExist("Пользователь не найден");
        }
    }

    @PutMapping
    public SendingEntity updateSending(@PathVariable Long id, @RequestBody SendingEntity sending) throws ClientNotFound {
        SendingEntity updatedSending = sendingRepo.findById(id).get();
        if (updatedSending == null) {
            throw new ClientNotFound("Небыл найден");
        }
        updatedSending.setMobile_codes(sending.getMobile_codes());
        updatedSending.setTags(sending.getTags());
        updatedSending.setText(sending.getText());
        updatedSending.setEndDateTime(LocalDateTime.now());


        sendingRepo.save(updatedSending);
        return updatedSending;
    }

    @DeleteMapping
    public Long deleteSending(@PathVariable Long id){
        sendingRepo.deleteById(id);
        return id;
    }
}
