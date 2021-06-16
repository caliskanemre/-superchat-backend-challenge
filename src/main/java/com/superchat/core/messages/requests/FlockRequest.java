package com.superchat.core.messages.requests;

import lombok.Data;

@Data
public class FlockRequest {

    String id;
    String from;
    String to;
    String text;
    String timestamp;
    String uid;
}
