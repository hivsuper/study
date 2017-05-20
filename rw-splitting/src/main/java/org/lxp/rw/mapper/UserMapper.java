package org.lxp.rw.mapper;

import org.apache.ibatis.annotations.Param;
import org.lxp.rw.model.User;

public interface UserMapper {
    int insert(User user);

    User selectById(@Param("id") int id);

    int deleteById(@Param("id") int id);

    int updateById(User user);
}