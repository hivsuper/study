package org.lxp.service;

import javax.annotation.Resource;

import org.lxp.mapper.AuthorMapper;
import org.lxp.mapper.ext.AuthorExtMapper;
import org.lxp.model.Author;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
  @Resource
  private AuthorMapper authorMapper;
  @Resource
  private AuthorExtMapper authorExtMapper;

  public boolean add(String username, String password) {
    Author author = new Author();
    author.setUsername(username);
    author.setPassword(password);
    return authorMapper.insert(author) > 0;
  }

  public Author getAuthor(String username) {
    return authorExtMapper.searchAuthor(username);
  }
}
