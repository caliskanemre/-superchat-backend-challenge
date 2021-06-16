package com.superchat;

import com.superchat.core.messages.requests.ContactRequest;
import com.superchat.core.messages.response.CommandDTO;
import com.superchat.core.messages.response.ContactDTO;
import com.superchat.core.service.ContactService;
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
public class ContactServiceImplTest {

    @Autowired
    ContactService contactService;

    @Test
    @Transactional
    @Rollback
    public void given_ContactRequest_When_Create_Then_ReturnCommandDTO() {

        ContactRequest request = new ContactRequest();
        request.setEmail("test@test.com");
        request.setName("testName");
        request.setPhone("49562565546");
        CommandDTO commandDTO = contactService.create(request);

        assertNotNull(commandDTO);
    }

    @Test
    @Transactional
    @Rollback
    public void given_ContactRequest_When_GetAllContacts_Then_ListOfContacts() {

        ContactRequest request = new ContactRequest();
        request.setEmail("test@test.com");
        request.setName("testName");
        request.setPhone("49562565546");
        contactService.create(request);

        ContactRequest request2 = new ContactRequest();
        request2.setEmail("test2@test.com");
        request2.setName("testName2");
        request2.setPhone("149562565546");
        contactService.create(request2);

        List<ContactDTO> contactDTOList = contactService.getAllContacts();

        assertEquals(request.getName(), contactDTOList.get(0).getName());
        assertEquals(request.getEmail(), contactDTOList.get(0).getEmail());
        assertEquals(request.getPhone(), contactDTOList.get(0).getPhone());
        assertEquals(request2.getName(), contactDTOList.get(1).getName());
        assertEquals(request2.getEmail(), contactDTOList.get(1).getEmail());
        assertEquals(request2.getPhone(), contactDTOList.get(1).getPhone());
    }

}
