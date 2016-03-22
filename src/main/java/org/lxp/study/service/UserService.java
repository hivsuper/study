package org.lxp.study.service;

import org.lxp.study.model.UserBase;

public interface UserService {
  public UserBase login(String account, String password);
}
