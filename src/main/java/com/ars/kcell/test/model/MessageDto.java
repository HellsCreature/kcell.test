package com.ars.kcell.test.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MessageDto {

  @JsonProperty("userName")
  String userName;

  @JsonProperty("text")
  String text;
}
