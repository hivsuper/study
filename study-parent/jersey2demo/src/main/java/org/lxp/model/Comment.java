package org.lxp.model;

import java.util.Date;
import org.lxp.vo.CommentStateEnum;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Comment {
    private Integer id;

    private Integer postId;

    private String name;

    private String text;

    private CommentStateEnum state;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public CommentStateEnum getState() {
        return state;
    }

    public void setState(CommentStateEnum state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}