package com.sending.sending.repository;

import com.sending.sending.entity.ClientEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClientRepo extends CrudRepository<ClientEntity,Long> {
    ClientEntity findByPhone(Integer phone);
    List<ClientEntity> findAll();
}
