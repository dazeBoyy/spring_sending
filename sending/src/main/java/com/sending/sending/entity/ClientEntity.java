package com.sending.sending.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Entity
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer phone;
    private Character phoneCode;
    private Character tags;
    private TimeZone timeZone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<MessageEntity> message = new ArrayList<>();

    public ClientEntity() {
        this.timeZone = TimeZone.getDefault();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPhoneCode(Character phoneCode) {
        this.phoneCode = phoneCode;
    }

    public void setTags(Character tags) {
        this.tags = tags;
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    public Long getId() {
        return id;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public Character getPhoneCode() {
        return phoneCode;
    }

    public Character getTags() {
        return tags;
    }

    public TimeZone getTimeZone() {
        return timeZone;
    }

    public List<MessageEntity> getMessage() {
        return message;
    }

    public void setMessage(MessageEntity message) {
        this.message.add(message);
    }
}
