package com.pw.sign.enums;


import com.pw.sign.common.exception.code.ResponseCodeInterface;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * 是否值
 *
 * @author Prescott.Wen
 * @version V1.0
 * @date 2021年8月18日
 */
@AllArgsConstructor
public enum BooleanInt {

    YES(0, "T", "是", "有", "对", "已"),
    NO(1, "F", "否", "无", "错", "未");

    @Getter
    private final int code;
    @Getter
    private final String enCode;

    private final String tag1;
    private final String tag2;
    private final String tag3;
    private final String tag4;
}
