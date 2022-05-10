package com.sending.sending.controller;


import com.sending.sending.entity.ClientEntity;
import com.sending.sending.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;


    @GetMapping("")
    public ResponseEntity getAll(){
        try {
            return ResponseEntity.ok(clientService.getClient());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PostMapping("/create")
    public ResponseEntity createUser(@RequestBody ClientEntity client){
        try {
            clientService.createClient(client);
            return ResponseEntity.ok("Клиент добавлен");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PutMapping(path = "{id}")
    public ResponseEntity updateItem(@PathVariable Long id, @RequestBody ClientEntity client){
        try {
            return ResponseEntity.ok(clientService.updateClient(id,client));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id){
        try {
            return ResponseEntity.ok(clientService.deleteClient(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
