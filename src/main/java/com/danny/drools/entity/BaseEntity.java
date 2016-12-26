package com.danny.drools.entity;

import java.util.Date;

/**
 * @author huyuyang@lxfintech.com
 * @Title: BaseEntity
 * @Copyright: Copyright (c) 2016
 * @Description: 实体抽象字段
 * @Company: lxjr.com
 * @Created on 2016-12-23 15:19:01
 */
public class BaseEntity {
    private String comment;//描述
    private String creator;//创建人
    private Date createTime;//创建时间
    private Date modifyTime;//修改时间

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
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
}
