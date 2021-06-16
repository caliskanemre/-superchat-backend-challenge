package com.superchat.core.web;



import com.superchat.core.messages.requests.MessageRequest;
import com.superchat.core.messages.response.CommandDTO;
import com.superchat.core.messages.response.MessageDTO;
import com.superchat.core.service.MessageService;
import com.superchat.core.service.model.MessageDirection;
import com.superchat.core.web.rest.model.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/v1/core")
@Validated
public class MessageController {

    private final MessageService messageService;
    public MessageController(
            MessageService messageService
    ){
        this.messageService = messageService;
    }

    @PostMapping("/message")
    public ResponseEntity<ApiResponse<CommandDTO>> sentMessage(
            @Valid @RequestBody MessageRequest messageRequest
    ) {

        messageRequest.setMessageDirection(MessageDirection.SENT);
        final CommandDTO created = messageService.newMessage(messageRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>("success", created));
    }
    
    @GetMapping("/message/{id}")
    public ResponseEntity<ApiResponse<List<MessageDTO>>> getMessagesByContactId(
            @PathVariable long id
    ) {
        final List<MessageDTO> messageDTOList = messageService.getAllMessagesByContactId(id);

        return ResponseEntity.ok(new ApiResponse<>( "SUCCESS", messageDTOList));
    }


}
