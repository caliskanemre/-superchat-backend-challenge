package com.superchat.core.messages.requests;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ContactRequest {
    @NotEmpty
    String name;
    @NotEmpty
    String email;
    String phone;
}