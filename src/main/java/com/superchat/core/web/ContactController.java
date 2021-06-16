package com.superchat.core.web;

import com.superchat.core.messages.requests.ContactRequest;
import com.superchat.core.messages.response.CommandDTO;
import com.superchat.core.messages.response.ContactDTO;
import com.superchat.core.service.ContactService;
import com.superchat.core.web.rest.model.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Clock;
import java.time.Instant;
import java.util.List;


@RestController
@RequestMapping("/api/v1/core")
@Validated
public class ContactController {


    private final ContactService contactService;
    public ContactController(
            ContactService contactService
    ){
        this.contactService = contactService;
    }

    @PostMapping("/contact")
    public ResponseEntity<ApiResponse<CommandDTO>> createContact(
            @Valid @RequestBody ContactRequest request
    ) {
        final CommandDTO created = contactService.create(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>( "success", created));
    }
    
    @GetMapping("/contacts")
    public ResponseEntity<ApiResponse<List<ContactDTO>>> getContacts(
    ) {
        final List<ContactDTO> contactDTOList = contactService.getAllContacts();

        return ResponseEntity.ok(new ApiResponse<>("SUCCESS", contactDTOList));
    }


}
