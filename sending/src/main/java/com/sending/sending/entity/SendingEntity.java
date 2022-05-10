package com.sending.sending.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class SendingEntity {

    public SendingEntity() {
        this.startDateTime = LocalDateTime.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    private Character mobile_codes;
    private Character tags;

    private String text;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sending", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<MessageEntity> message = new ArrayList<>();

    public String getText() {
        return text;
    }


    public void setText(String text) {
        this.text = text;
    }

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

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public Character getMobile_codes() {
        return mobile_codes;
    }

    public void setMobile_codes(Character mobile_codes) {
        this.mobile_codes = mobile_codes;
    }

    public Character getTags() {
        return tags;
    }

    public void setTags(Character tags) {
        this.tags = tags;
    }

    public List<MessageEntity> getMessage() {
        return message;
    }

    public void setMessage(MessageEntity message) {
        this.message.add(message);
    }
}
