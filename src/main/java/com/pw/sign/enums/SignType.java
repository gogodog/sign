package com.pw.sign.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 是否值
 *
 * @author Prescott.Wen
 * @version V1.0
 * @date 2021年8月18日
 */
@AllArgsConstructor
public enum SignType {

    TEXT("TEXT", "填写名称"),
    HAND("HAND", "手写名称");

    @Getter
    private final String code;
    private final String tag;
}
