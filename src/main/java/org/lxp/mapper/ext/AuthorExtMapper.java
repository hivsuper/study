package org.lxp.mapper.ext;

import org.apache.ibatis.annotations.Param;
import org.lxp.model.Author;

public interface AuthorExtMapper {
  Author searchAuthor(@Param("keyword") String keyword);
}