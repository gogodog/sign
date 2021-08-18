package com.pw.sign.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class SignHotCode extends BaseEntity implements Serializable {
    private String ckey;

    private String type;

    private String info;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    private String creator;

    @TableLogic
    private Integer deleted;

    public SignHotCode(String ckey, String type, String info, Date createTime, String creator, Integer deleted) {
        this.ckey = ckey;
        this.type = type;
        this.info = info;
        this.createTime = createTime;
        this.creator = creator;
        this.deleted = deleted;
    }

    public SignHotCode() {
        super();
    }

    public String getCkey() {
        return ckey;
    }

    public void setCkey(String ckey) {
        this.ckey = ckey == null ? null : ckey.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
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

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}