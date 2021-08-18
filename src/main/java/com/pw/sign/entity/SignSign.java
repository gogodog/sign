package com.pw.sign.entity;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.*;
import com.pw.sign.vo.IdeaSignVoRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class SignSign extends BaseEntity implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer ideaId;

    private String type;

    private Integer anonymous;

    private String talkContent;

    private Integer talkParent;

    private String ip;

    private String mac;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    private String creator;

    private String creatorName;

    private String creatorHead;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    private String updater;

    @TableLogic
    private Integer deleted;

    private String info;

    @TableField(exist = false)
    private IdeaSignVoRequest infoVo;

    public SignSign(Integer id, Integer ideaId, String type, Integer anonymous, String talkContent, Integer talkParent, String ip, String mac, Date createTime, String creator, String creatorName, String creatorHead, Date updateTime, String updater, Integer deleted, String info) {
        this.id = id;
        this.ideaId = ideaId;
        this.type = type;
        this.anonymous = anonymous;
        this.talkContent = talkContent;
        this.talkParent = talkParent;
        this.ip = ip;
        this.mac = mac;
        this.createTime = createTime;
        this.creator = creator;
        this.creatorName = creatorName;
        this.creatorHead = creatorHead;
        this.updateTime = updateTime;
        this.updater = updater;
        this.deleted = deleted;
        this.info = info;
    }

    public SignSign() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdeaId() {
        return ideaId;
    }

    public void setIdeaId(Integer ideaId) {
        this.ideaId = ideaId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getAnonymous() {
        return anonymous;
    }

    public void setAnonymous(Integer anonymous) {
        this.anonymous = anonymous;
    }

    public String getTalkContent() {
        return talkContent;
    }

    public void setTalkContent(String talkContent) {
        this.talkContent = talkContent == null ? null : talkContent.trim();
    }

    public Integer getTalkParent() {
        return talkParent;
    }

    public void setTalkParent(Integer talkParent) {
        this.talkParent = talkParent;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac == null ? null : mac.trim();
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

    public String getCreatorHead() {
        return creatorHead;
    }

    public void setCreatorHead(String creatorHead) {
        this.creatorHead = creatorHead == null ? null : creatorHead.trim();
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

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    public IdeaSignVoRequest getInfoVo() {
        return StringUtils.isEmpty(this.info) ? null : JSONObject.parseObject(this.info, IdeaSignVoRequest.class);
    }
}