package org.lxp.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.lxp.model.PostTag;
import org.lxp.model.PostTagExample;

public interface PostTagMapper {
    int countByExample(PostTagExample example);

    int deleteByExample(PostTagExample example);

    int deleteByPrimaryKey(@Param("postId") Integer postId, @Param("tagId") Integer tagId);

    int insert(PostTag record);

    int insertSelective(PostTag record);

    List<PostTag> selectByExample(PostTagExample example);

    int updateByExampleSelective(@Param("record") PostTag record, @Param("example") PostTagExample example);

    int updateByExample(@Param("record") PostTag record, @Param("example") PostTagExample example);
}