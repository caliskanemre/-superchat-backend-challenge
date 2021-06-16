package com.superchat.core.repository;

import com.superchat.core.service.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long>{

    Contact findContactById(long contactId);

    List<Contact> findAll();
}
