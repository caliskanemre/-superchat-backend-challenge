package com.superchat.core.service.impl;

import com.superchat.core.messages.requests.ContactRequest;
import com.superchat.core.messages.response.CommandDTO;
import com.superchat.core.messages.response.ContactDTO;
import com.superchat.core.repository.ContactRepository;
import com.superchat.core.service.ContactService;
import com.superchat.core.service.model.Contact;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {


    private final ContactRepository contactRepository;

    @Override
    public CommandDTO create(ContactRequest contactRequest) {

        Contact contact = new Contact();
        contact.setEmail(contactRequest.getEmail());
        contact.setName(contactRequest.getName());
        contact.setPhone(contactRequest.getPhone());
        Contact created = contactRepository.saveAndFlush(contact);

        return CommandDTO.builder()
                .id(created.getId())
                .build();
    }

    @Override
    public List<ContactDTO> getAllContacts() {

        List<ContactDTO> contactDTOList = new ArrayList<>();
        List<Contact> contactList = contactRepository.findAll();

        for(Contact contact : contactList){
            ContactDTO contactDTO = new ContactDTO(
                    contact.getId(),contact.getName(), contact.getEmail(), contact.getPhone()
            );
            contactDTOList.add(contactDTO);
        }
        return contactDTOList;
    }

    @Override
    public Contact findContactById(long contactId) {
        return contactRepository.findContactById(contactId);
    }
}
