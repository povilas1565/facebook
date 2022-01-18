package com.example.facebook.facade;

import com.example.facebook.dto.MessageDTO;
import com.example.facebook.entity.Message;
import org.springframework.stereotype.Component;

@Component
public class MessageFacade {
    public MessageDTO messageToMessageDTO(Message message) {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setId(message.getId());
        messageDTO.setUsername(message.getUser().getUsername());


        return messageDTO;
    }
}

