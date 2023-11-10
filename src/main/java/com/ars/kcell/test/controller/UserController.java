package com.ars.kcell.test.controller;

import com.ars.kcell.test.facade.UserFacade;
import com.ars.kcell.test.model.UserCreateDto;
import com.ars.kcell.test.model.UserDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

  UserFacade userFacade;

  @GetMapping("/{name}")
  public UserDto getUserByName(@PathVariable String name) {
    return userFacade.getUserDto(name);
  }

  @PostMapping
  public void createUser(@RequestBody UserCreateDto dto) {
    userFacade.createUser(dto);
  }

  @PostMapping("/{name}/block")
  public void block(@PathVariable String name) {
    userFacade.block(name);
  }

  @PostMapping("/{name}/unblock")
  public void unblock(@PathVariable String name) {
    userFacade.unblock(name);
  }
}
