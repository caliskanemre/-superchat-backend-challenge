package com.superchat;

import com.superchat.core.messages.requests.MessageRequest;
import com.superchat.core.messages.response.CommandDTO;
import com.superchat.core.messages.response.MessageDTO;
import com.superchat.core.service.MessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageServiceImplTest {

    @Autowired
    MessageService messageService;

    @Test
    @Transactional
    @Rollback
    public void given_MessageRequest_When_Create_Then_ReturnCommandDTO() {

        MessageRequest request = new MessageRequest();
        request.setMessage("new message");
        request.setContactId(1);
        CommandDTO commandDTO = messageService.newMessage(request);

        assertNotNull(commandDTO);
    }

    @Test
    @Transactional
    @Rollback
    public void given_messageRequest_when_getAllMessagesByContactId_Then_ListOfMessages() {

        MessageRequest request = new MessageRequest();
        request.setMessage("new message");
        request.setContactId(1);
        messageService.newMessage(request);

        MessageRequest request2 = new MessageRequest();
        request2.setMessage("new message2");
        request2.setContactId(2);
        messageService.newMessage(request2);

        List<MessageDTO> messageDTOList = messageService.getAllMessagesByContactId(2);

        assertEquals(request2.getMessage(), messageDTOList.get(0).getContactMessage());
    }

}
