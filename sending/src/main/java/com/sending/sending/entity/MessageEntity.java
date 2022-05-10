package com.sending.sending.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
public class MessageEntity {

    public MessageEntity() {
        this.startDateTime = LocalDateTime.now();
        this.status = true;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime startDateTime;

    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "sending_id")
    @JsonManagedReference
    private SendingEntity sending;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonManagedReference
    private ClientEntity client;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public SendingEntity getSending() {
        return sending;
    }

    public void setSending(SendingEntity sending) {
        this.sending = sending;
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }

}
