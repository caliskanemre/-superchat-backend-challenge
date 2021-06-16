package com.superchat.core.messages.response;

import com.superchat.core.service.model.Contact;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ContactDTO {
    long id;
    String name;
    String email;
    String phone;

    public ContactDTO(final Contact contact) {
        this(contact.getId(), contact.getName(), contact.getEmail(), contact.getPhone());
    }
}