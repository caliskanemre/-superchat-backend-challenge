package com.superchat.core.repository;

import com.superchat.core.service.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long>{

    List<Message> findAllByContactId(long contactId);
}
