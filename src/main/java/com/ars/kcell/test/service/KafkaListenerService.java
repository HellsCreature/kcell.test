package com.ars.kcell.test.service;

import static com.ars.kcell.test.configuration.KafkaProducerConfiguration.CHAT;
import static com.ars.kcell.test.configuration.KafkaProducerConfiguration.INCOME_MESSAGES;
import com.ars.kcell.test.domain.entity.Message;
import com.ars.kcell.test.domain.entity.User;
import com.ars.kcell.test.domain.repository.MessageRepository;
import com.ars.kcell.test.model.MessageDto;
import java.time.OffsetDateTime;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class KafkaListenerService {

  MessageRepository messageRepository;

  UserService userService;

  KafkaTemplate<String, MessageDto> kafkaTemplate;

  @KafkaListener(topics = INCOME_MESSAGES, groupId = "server", containerFactory = "kafkaListenerContainerFactory")
  public void listenGroupFoo(MessageDto messageDto) {

    User user = userService.getUserByName(messageDto.getUserName());

    Message message = Message.builder()
        .user(user)
        .datetime(OffsetDateTime.now())
        .text(messageDto.getText())
        .build();

    messageRepository.save(message);
    kafkaTemplate.send(CHAT, messageDto);

    System.out.println("Received Message in group server: " + messageDto);
  }
}
