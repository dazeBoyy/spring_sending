package com.sending.sending.controller;

import com.sending.sending.entity.ClientEntity;
import com.sending.sending.entity.MessageEntity;
import com.sending.sending.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/all")
    public ResponseEntity getAll(@RequestParam Boolean status){
        try {
            return ResponseEntity.ok(messageService.getMessages(status));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
    @GetMapping()
    public ResponseEntity getOne(@RequestParam Long id){
        try {
            return ResponseEntity.ok(messageService.getMessage(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }


    @PostMapping()
    public ResponseEntity sendMessage(@RequestParam Long sendingId, @RequestParam Long clientId){
        try {
            return ResponseEntity.ok(messageService.sendMessage(sendingId, clientId));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PostMapping("/send")
    public ResponseEntity sendtoAPI(@RequestParam Long id){
        try {
            return ResponseEntity.ok(messageService.sendToAPI(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
