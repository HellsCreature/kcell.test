package com.ars.kcell.test.service;

import com.ars.kcell.test.domain.entity.Message;
import com.ars.kcell.test.domain.repository.MessageRepository;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MessageService {

  MessageRepository messageRepository;

  public Message save(Message message) {
    return messageRepository.save(message);
  }

  public List<Message> findAll() {
    return messageRepository.findAll();
  }

}
