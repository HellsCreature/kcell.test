package com.ars.kcell.test.facade;

import com.ars.kcell.test.domain.entity.User;
import com.ars.kcell.test.model.UserCreateDto;
import com.ars.kcell.test.model.UserDto;
import com.ars.kcell.test.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserFacade {

  UserService userService;

  ObjectMapper objectMapper;

  public UserDto getUserDto(String name) {
    User user = userService.getUserByName(name);
    return objectMapper.convertValue(user, UserDto.class);
  }

  public void createUser(UserCreateDto dto) {
    userService.createUser(dto.getName(), dto.getPassword());
  }

  public void block(String name) {
    User user = userService.getUserByName(name);
    user.setBlocked(true);
    userService.save(user);
  }

  public void unblock(String name) {
    User user = userService.getUserByName(name);
    user.setBlocked(false);
    userService.save(user);
  }
}
