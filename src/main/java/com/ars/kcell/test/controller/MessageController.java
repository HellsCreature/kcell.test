package com.ars.kcell.test.controller;

import com.ars.kcell.test.domain.entity.Message;
import com.ars.kcell.test.model.MessageDto;
import com.ars.kcell.test.service.KafkaProducerService;
import com.ars.kcell.test.service.MessageService;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MessageController {

  KafkaProducerService kafkaProducerService;
  MessageService messageService;

  @PostMapping
  public void sendMessage(@RequestBody MessageDto message) {
    kafkaProducerService.sendMessage(message);
  }

  @GetMapping
  public List<Message> findAll() {
    return messageService.findAll();
  }

}
