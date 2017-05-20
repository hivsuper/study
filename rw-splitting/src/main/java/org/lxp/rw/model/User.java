package org.lxp.rw.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class User {
    private static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    private int id;
    private String name;
    @JsonFormat(pattern = YYYY_MM_DD_HH_MM_SS)
    private Date createTime;
    @JsonFormat(pattern = YYYY_MM_DD_HH_MM_SS)
    private Date modifyTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public String toString() {
        return String.format("User [id=%s, name=%s, createTime=%s, modifyTime=%s]", id, name, createTime, modifyTime);
    }
}
