package com.pw.sign.vo;

import com.alibaba.fastjson.JSONObject;
import com.pw.sign.entity.SysXUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
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

    private Other other;

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
        this.other = cetUserOther(user.getOther());
        this.md5 = md5;
    }

    private Other cetUserOther(String other) {
        if (StringUtils.isNotEmpty(other)) {
            return JSONObject.parseObject(other, Other.class);
        }
        return null;
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
        this.other = cetUserOther(user.getOther());
    }

    @Data
    private static class Other {
        private String sign;
    }
}