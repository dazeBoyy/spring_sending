package com.sending.sending.controller;

import com.sending.sending.entity.ClientEntity;
import com.sending.sending.entity.SendingEntity;
import com.sending.sending.service.ClientService;
import com.sending.sending.service.SendingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sending")
public class SendingController {

    @Autowired
    private SendingService sendingService;

    @GetMapping("/all")
    public ResponseEntity getAll(){
        try {
            return ResponseEntity.ok(sendingService.getSendings());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
    @GetMapping
    public ResponseEntity getOne(@RequestParam Long id){
        try {
            return ResponseEntity.ok(sendingService.getSending(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PostMapping("/create")
    public ResponseEntity createSending(@RequestParam Long client_id, @RequestBody SendingEntity sending){
        try {
            return ResponseEntity.ok(sendingService.createSending(client_id, sending));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PutMapping(path = "{id}")
    public ResponseEntity updateItem(@PathVariable Long id, @RequestBody SendingEntity sending){
        try {
            return ResponseEntity.ok(sendingService.updateSending(id,sending));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id){
        try {
            return ResponseEntity.ok(sendingService.deleteSending(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
