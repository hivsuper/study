package org.lxp.study.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.lxp.study.dao.mapper.UserBaseMapper;
import org.lxp.study.model.UserBase;
import org.lxp.study.model.UserBaseExample;
import org.lxp.study.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
  @Resource
  private UserBaseMapper userBaseMapper;

  @Override
  public UserBase login(String account, String password) {
    UserBaseExample example = new UserBaseExample();
    example.createCriteria().andAccountEqualTo(account);
    List<UserBase> users = userBaseMapper.selectByExample(example);
    if (!users.isEmpty()) {
      UserBase user = users.get(0);
      return password.equals(user.getPassword()) ? user : null;
    }
    return null;
  }

}
