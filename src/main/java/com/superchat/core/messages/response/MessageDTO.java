package com.superchat.core.messages.response;

import com.superchat.core.service.model.Message;
import com.superchat.core.service.model.MessageDirection;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageDTO {
    long id;
    String contactMessage;
    MessageDirection messageDirection;

    public MessageDTO(final Message message) {
        this(message.getId(), message.getContactMessage(),
                message.getMessageDirection());
    }
}