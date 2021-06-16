package com.superchat.core.messages.response;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
@Builder
public class CommandDTO {
    long id;
}
