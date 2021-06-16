package com.superchat.core.messages.requests;

import com.superchat.core.service.model.MessageDirection;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class MessageRequest {
    long contactId;
    @NotEmpty
    String message;

    MessageDirection messageDirection;
}