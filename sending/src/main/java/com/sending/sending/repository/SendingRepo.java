package com.sending.sending.repository;

import com.sending.sending.entity.SendingEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface SendingRepo extends CrudRepository<SendingEntity,Long> {
    List<SendingEntity> findAll();

}
