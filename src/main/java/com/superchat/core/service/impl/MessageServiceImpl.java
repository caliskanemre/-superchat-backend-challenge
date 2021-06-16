package com.superchat.core.service.impl;

import com.superchat.core.messages.requests.ContactRequest;
import com.superchat.core.messages.requests.MessageRequest;
import com.superchat.core.messages.response.CommandDTO;
import com.superchat.core.messages.response.MessageDTO;
import com.superchat.core.repository.MessageRepository;
import com.superchat.core.service.ContactService;
import com.superchat.core.service.MessageService;
import com.superchat.core.service.model.Contact;
import com.superchat.core.service.model.Message;
import io.micrometer.core.ipc.http.HttpSender;
import lombok.SneakyThrows;
import org.apache.commons.lang3.text.StrSubstitutor;
import org.json.JSONException;
import org.json.JSONObject;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Service
public class MessageServiceImpl implements MessageService {

    private OkHttpClient client = new OkHttpClient();
    public static final String BITCOIN_PRICE_ENDPOINT = "https://api.coindesk.com/v1/bpi/currentprice.json";
    public String bitcoinPrice ="";
    @Autowired
    MessageRepository messageRepository;

    @Autowired
    ContactService contactService;

    @Override
    public CommandDTO newMessage(MessageRequest messageRequest) {

        Message message = new Message();
        message.setContactId(messageRequest.getContactId());
        message.setContactMessage(messageRequest.getMessage());
        message.setMessageDirection(messageRequest.getMessageDirection());
        checkPlaceHolder(message);
        Message created = messageRepository.saveAndFlush(message);
        return CommandDTO.builder()
                .id(created.getId())
                .build();
    }


    @Override
    public List<MessageDTO> getAllMessagesByContactId(long contactId) {

        List<MessageDTO> messageDTOlist = new ArrayList<>();
        List<Message> messageList = messageRepository.findAllByContactId(contactId);

        for(Message message : messageList){
            MessageDTO messageDTO = new MessageDTO(
                    message.getId(),message.getContactMessage(),message.getMessageDirection()
            );
            messageDTOlist.add(messageDTO);
        }
        return messageDTOlist;
    }


    private Message checkPlaceHolder(Message message) {

        Contact contact;
        contact = contactService.findContactById(message.getContactId());
        if(contact ==null){
            ContactRequest request= new ContactRequest();
            request.setEmail("bot@flock.com");
            request.setName("Flock");
            request.setPhone("-1");
            CommandDTO commandDTO = contactService.create(request);
            contact = contactService.findContactById(commandDTO.getId());
            message.setContactId(contact.getId());
        }
        loadBitcoinPrice(new Callback() {

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @SneakyThrows
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
                bitcoinPrice = String.valueOf(parseBitcoinPrice(str));
            }

        });
        Map<String, String> data = new HashMap<>();
        data.put("name", contact.getName());
        data.put("bitcoin price", bitcoinPrice);

        message.setContactMessage(StrSubstitutor.replace(message.getContactMessage(), data));

        return message;
    }

    private void loadBitcoinPrice(Callback callback) {
        Request request = new Request.Builder().url(BITCOIN_PRICE_ENDPOINT).build();
        client.newCall(request).enqueue(callback);
    }
    public double parseBitcoinPrice(String str) throws JSONException {
        JSONObject jsonObject = new JSONObject(str);
        return jsonObject.getJSONObject("bpi").getJSONObject("USD").getDouble("rate_float");

    }
    }
