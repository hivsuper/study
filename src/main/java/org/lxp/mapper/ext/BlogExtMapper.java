package org.lxp.mapper.ext;

import org.apache.ibatis.annotations.Select;
import org.lxp.model.Blog;

public interface BlogExtMapper {
  @Select("SELECT * FROM blog WHERE id = #{id}")
  Blog selectBlog(int id);
}
