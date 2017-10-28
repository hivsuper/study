package org.lxp.service;

import java.util.Date;

import javax.annotation.Resource;

import org.lxp.mapper.CommentMapper;
import org.lxp.model.Comment;
import org.lxp.vo.CommentStateEnum;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
  @Resource
  private CommentMapper commentMapper;

  public boolean add(int postId, String name, String text) {
    Comment comment = new Comment();
    comment.setPostId(postId);
    comment.setName(name);
    comment.setText(text);
    comment.setState(CommentStateEnum.NOMARL);
    comment.setCreateTime(new Date());
    return commentMapper.insert(comment) > 0;
  }

  public boolean update(int commentId, CommentStateEnum state) {
    Comment comment = new Comment();
    comment.setId(commentId);
    comment.setState(state);
    return commentMapper.updateByPrimaryKeySelective(comment) > 0;
  }

  public Comment get(int commentId) {
    return commentMapper.selectByPrimaryKey(commentId);
  }
}
