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
public class SignFollow  extends BaseEntity implements Serializable {
    private String aUser;

    private String aUserName;

    private String bUser;

    private String bUserName;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableLogic
    private Integer deleted;

    private String aUserHead;

    private String aUserInfo;

    private String bUserHead;

    private String bUserInfo;
}