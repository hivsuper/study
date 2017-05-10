package org.lxp.service;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.lxp.mapper.BlogMapper;
import org.lxp.mapper.ext.BlogExtMapper;
import org.lxp.model.Blog;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

@Service
public class BlogService {
  @Resource
  private BlogMapper blogMapper;
  @Resource
  private SqlSessionTemplate sqlSessionTemplate;
  @Resource
  private SqlSessionFactory sqlSessionFactory;

  public boolean add(String title) {
    Blog blog = new Blog();
    blog.setTitle(title);
    blog.setCreateTime(new Date());
    return blogMapper.insert(blog) > 0;
  }

  public Blog getViaMapper(int id) {
    return blogMapper.selectByPrimaryKey(id);
  }

  public Blog getViaSQLMapper(int id) {
    BlogExtMapper mapper = sqlSessionTemplate.getMapper(BlogExtMapper.class);
    return mapper.selectBlog(id);
  }

  public Blog getViaSqlSessionFactory(int id) {
    Blog blog;
    SqlSession session = sqlSessionFactory.openSession();
    try {
      blog = (Blog) sqlSessionFactory.openSession().selectOne("org.lxp.mapper.BlogMapper.selectByPrimaryKey", id);
    } finally {
      session.close();
    }
    return blog;
  }

  public Blog getViaSqlSessionTemplate(int id) {
    BlogMapper mapper = sqlSessionTemplate.getMapper(BlogMapper.class);
    return mapper.selectByPrimaryKey(id);
  }
}
