package com.superchat.core.service;


import com.superchat.core.messages.requests.MessageRequest;
import com.superchat.core.messages.response.CommandDTO;
import com.superchat.core.messages.response.MessageDTO;

import java.util.List;


public interface MessageService {

    CommandDTO newMessage(MessageRequest messageRequest);

    List<MessageDTO> getAllMessagesByContactId(long contactId);


}