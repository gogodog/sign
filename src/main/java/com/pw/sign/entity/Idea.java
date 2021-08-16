package com.pw.sign.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sign_idea")
public class Idea extends BaseEntity implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableLogic
    private Integer deleted;

    private String status;

    private String tags;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    private String creator;

    private String creatorName;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    private String updater;

    private String idea;

    public Idea(Integer id, Integer deleted, String status, String tags, Date createTime, String creator, String creatorName, Date updateTime, String updater, String idea) {
        this.id = id;
        this.deleted = deleted;
        this.status = status;
        this.tags = tags;
        this.createTime = createTime;
        this.creator = creator;
        this.creatorName = creatorName;
        this.updateTime = updateTime;
        this.updater = updater;
        this.idea = idea;
    }

    public Idea() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags == null ? null : tags.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName == null ? null : creatorName.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater == null ? null : updater.trim();
    }

    public String getIdea() {
        return idea;
    }

    public void setIdea(String idea) {
        this.idea = idea == null ? null : idea.trim();
    }
}