package com.ars.kcell.test.controller;

import com.ars.kcell.test.domain.entity.User;
import com.ars.kcell.test.domain.repository.UserRepository;
import com.ars.kcell.test.model.MessageDto;
import com.ars.kcell.test.service.KafkaProducerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
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

  @PostMapping
  public void sendMessage(@RequestBody MessageDto message) {
    kafkaProducerService.sendMessage(message);
  }

}
