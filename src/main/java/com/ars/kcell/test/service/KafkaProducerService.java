package com.ars.kcell.test.service;

import static com.ars.kcell.test.configuration.KafkaProducerConfiguration.INCOME_MESSAGES;
import com.ars.kcell.test.model.MessageDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class KafkaProducerService {

  KafkaTemplate<String, MessageDto> kafkaTemplate;

  public void sendMessage(MessageDto message) {
    kafkaTemplate.send(INCOME_MESSAGES, message);
  }

}
