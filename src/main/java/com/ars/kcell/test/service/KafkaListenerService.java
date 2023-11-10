package com.ars.kcell.test.service;

import static com.ars.kcell.test.configuration.KafkaProducerConfiguration.CHAT;
import static com.ars.kcell.test.configuration.KafkaProducerConfiguration.INCOME_MESSAGES;
import com.ars.kcell.test.domain.entity.Message;
import com.ars.kcell.test.domain.entity.User;
import com.ars.kcell.test.model.MessageDto;
import java.time.OffsetDateTime;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class KafkaListenerService {

  MessageService messageService;

  UserService userService;

  KafkaTemplate<String, MessageDto> kafkaTemplate;

  @KafkaListener(topics = INCOME_MESSAGES, groupId = "server", containerFactory = "kafkaListenerContainerFactory")
  public void listenGroupFoo(MessageDto messageDto) {

    User user = userService.getUserByName(messageDto.getUserName());

    if (!user.isBlocked()) {

      Message message = Message.builder()
          .user(user)
          .datetime(OffsetDateTime.now())
          .text(messageDto.getText())
          .build();

      messageService.save(message);
      kafkaTemplate.send(CHAT, messageDto);

      log.info("Received Message in group server: {}",  messageDto);
    } else {
      log.info("Received Message in group server: {}"
      + ", but user {} is blocked", messageDto, user.getName());

    }
  }
}
