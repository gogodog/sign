package com.pw.sign.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class RegisterVo {

    @NotNull(message = "电话号码不能为空")
    @Pattern(regexp = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$", message = "电话号码格式错误")
    private String tel;
    @NotNull(message = "验证码不能为空")
    @Pattern(regexp = "^[0-9]{4}$", message = "验证码错误")
    private String verityCode;
    private String area;
}