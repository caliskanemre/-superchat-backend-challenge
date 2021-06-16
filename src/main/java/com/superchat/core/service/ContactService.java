package com.superchat.core.service;


import com.superchat.core.messages.requests.ContactRequest;
import com.superchat.core.messages.response.CommandDTO;
import com.superchat.core.messages.response.ContactDTO;
import com.superchat.core.service.model.Contact;

import java.util.List;

public interface ContactService {

    CommandDTO create(final ContactRequest contactRequest);

    List<ContactDTO> getAllContacts();

    Contact findContactById(long contactId);
}