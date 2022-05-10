package com.sending.sending.service;


import com.sending.sending.entity.ClientEntity;
import com.sending.sending.entity.MessageEntity;
import com.sending.sending.entity.SendingEntity;
import com.sending.sending.exception.ClientNotFound;
import com.sending.sending.exception.MessageNotFound;
import com.sending.sending.repository.ClientRepo;
import com.sending.sending.repository.MessageRepo;
import com.sending.sending.repository.SendingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private SendingRepo sendingRepo;

    @Autowired
    private ClientRepo clientRepo;



    @GetMapping
    public List<MessageEntity> getMessages(@PathVariable Boolean status) {

        return messageRepo.findAllByStatus(status);
    }

    @GetMapping
    public MessageEntity getMessage(@PathVariable Long id) {

        return messageRepo.findById(id).get();
    }


    @PostMapping()
    public MessageEntity sendMessage(Long sendingId, Long clientId) throws MessageNotFound, URISyntaxException {

        MessageEntity message = new MessageEntity();
        SendingEntity sending = sendingRepo.findById(sendingId).get();
        ClientEntity client = clientRepo.findById(clientId).get();
        if (sending == null && client == null){
            throw new MessageNotFound("Сообщение не найдено или было уже создано");
        }
        message.setSending(sending);
        message.setClient(client);
        client.setMessage(message);
        client.setMessage(message);
        return messageRepo.save(message);
      /*  MessageEntity response = WebClient.create().post()
                .uri(new URI("https://probe.fbrq.cloud/v1/send/"+ message.getId()))
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2ODI1MzE5MTgsImlzcyI6ImZhYnJpcXVlIiwibmFtZSI6ImRhemVCb3l5In0.4PpCEhWBThBrA9slgaM7kTw5NCgsav6LiGB7r8m_Sc8")
                .syncBody(message)
                .retrieve()
                .bodyToMono(MessageEntity.class)
                .block();
        return response;*/
    }
    @PostMapping()
    public MessageEntity sendToAPI(Long id) throws MessageNotFound, URISyntaxException {

        MessageEntity message = messageRepo.findById(id).get();
        if (message == null){
            throw new MessageNotFound("Ошибка");
        }

       MessageEntity response = WebClient.create().post()
                .uri(new URI("https://probe.fbrq.cloud/v1/send/"+ message.getId()))
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2ODI1MzE5MTgsImlzcyI6ImZhYnJpcXVlIiwibmFtZSI6ImRhemVCb3l5In0.4PpCEhWBThBrA9slgaM7kTw5NCgsav6LiGB7r8m_Sc8")
               .bodyValue(message)
                .retrieve()
                .bodyToMono(MessageEntity.class)
                .block();
        return response;
    }
}
