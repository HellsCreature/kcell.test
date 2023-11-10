package com.ars.kcell.test.service;

import com.ars.kcell.test.domain.entity.User;
import com.ars.kcell.test.domain.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

  UserRepository userRepository;

  public User getUserByName(String name) {
    return userRepository.findByName(name).orElseThrow(IllegalArgumentException::new);
  }

  public User createUser(String name, String password) {
    User user = User.builder()
        .name(name)
        .password("toImplement")
        .blocked(false)
        .build();
    return userRepository.save(user);
  }

  public User save(User user) {
    return userRepository.save(user);
  }
}
