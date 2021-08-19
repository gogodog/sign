package com.pw.sign.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.pw.sign.entity.BaseEntity;
import com.pw.sign.entity.SysXUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {

    private String id;

    private String username;

    private String address;

    private String headImg;

    private String nickName;

    private String email;

    private Integer sex;

    private String createWhere;

    private Date createTime;

    private Date updateTime;

    private String md5;

    public UserVo(SysXUser user, String md5) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.address = user.getAddress();
        this.headImg = user.getHeadImg();
        this.nickName = user.getNickName();
        this.email = user.getEmail();
        this.sex = user.getSex();
        this.createWhere = user.getCreateWhere();
        this.createTime = user.getCreateTime();
        this.updateTime = user.getUpdateTime();
        this.md5 = md5;
    }

    public UserVo(SysXUser user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.address = user.getAddress();
        this.headImg = user.getHeadImg();
        this.nickName = user.getNickName();
        this.email = user.getEmail();
        this.sex = user.getSex();
        this.createWhere = user.getCreateWhere();
        this.createTime = user.getCreateTime();
        this.updateTime = user.getUpdateTime();
    }
}