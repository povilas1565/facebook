package com.example.facebook.controllers;

import com.example.facebook.dto.MessageDTO;
import com.example.facebook.entity.Message;
import com.example.facebook.facade.MessageFacade;

import com.example.facebook.payload.response.MessageResponse;
import com.example.facebook.service.MessageService;
import com.example.facebook.validators.ResponseErrorValidator;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/message")
@CrossOrigin
public class MessageController {
    @Autowired
    private MessageFacade messageFacade;
    @Autowired
    private MessageService messageService;
    @Autowired
    private ResponseErrorValidator responseErrorValidator;

    @PostMapping("/create")
    public ResponseEntity<Object> createMessage(@Valid @RequestBody MessageDTO messageDTO, BindingResult bindingResult, Principal principal) {
        ResponseEntity<Object> listErrors = responseErrorValidator.mappedValidatorService(bindingResult);
        if (!ObjectUtils.isEmpty(listErrors)) return listErrors;

        Message message = messageService.createMessage(messageDTO, principal);
        MessageDTO messageCreated = messageFacade.messageToMessageDTO(message);

        return new ResponseEntity<>(messageCreated, HttpStatus.OK);
    }

    @PostMapping("/{messageId}/delete")
    public ResponseEntity<MessageResponse> deletePost(@PathVariable("messageId") String messageId) {
        messageService.deleteMessage(Long.parseLong(messageId));
        return new ResponseEntity<>(new MessageResponse("The message" + messageId + "were deleted"), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<MessageDTO>> getAllMessages() {
        List<MessageDTO> messageDTOList= messageService.getAllMessages()
                .stream()
                .map(messageFacade::messageToMessageDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(messageDTOList, HttpStatus.OK);
    }

    @GetMapping("/user/messages")
    public ResponseEntity<List<MessageDTO>> getAllMessagesForUser(Principal principal) {
        List<MessageDTO> messageDTOList = messageService.getAllMessagesForUser(principal)
                .stream()
                .map(messageFacade::messageToMessageDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(messageDTOList, HttpStatus.OK);
    }
}







