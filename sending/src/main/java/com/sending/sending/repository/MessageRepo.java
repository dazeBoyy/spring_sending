package com.sending.sending.repository;

import com.sending.sending.entity.MessageEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepo extends CrudRepository<MessageEntity,Long> {
    List<MessageEntity> findAll();
    List<MessageEntity> findAllByStatus(Boolean status);
}
