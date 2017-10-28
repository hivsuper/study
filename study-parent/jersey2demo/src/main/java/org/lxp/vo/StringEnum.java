package org.lxp.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public interface StringEnum<E extends Enum<E>> {

  @JsonValue
  String getText();

  @JsonCreator
  public String formText(String value);
}
