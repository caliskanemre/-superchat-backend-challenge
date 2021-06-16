package com.superchat.core.web;

import com.superchat.core.messages.requests.FlockRequest;
import com.superchat.core.messages.requests.MessageRequest;
import com.superchat.core.service.MessageService;
import com.superchat.core.service.model.MessageDirection;
import com.superchat.core.web.rest.model.response.ApiResponse;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/core")
public class FlockWebhookController {

    @Autowired
    MessageService messageService;

    @PostMapping("/webhook")
    public ResponseEntity<ApiResponse<String>> createContact(
             @RequestBody FlockRequest request
    ) {
        String[] arrOfStr = request.getUid().split("-");
        MessageRequest messageRequest = new MessageRequest();
        messageRequest.setMessage(request.getText());
        messageRequest.setContactId(Long.parseLong(arrOfStr[0]));
        messageRequest.setMessageDirection(MessageDirection.RECEIVED);
        messageService.newMessage(messageRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>("success", "created"));
    }

}
