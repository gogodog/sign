package com.pw.sign.enums;


import com.pw.sign.vo.UserVo;
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
public enum Anonymous {

    NO_ANONYMOUS(0, "不匿名"),
    ANONYMOUS(1, "匿名"),
    GUEST(2, "游客");

    @Getter
    private final int code;
    private final String tag;

    public static Integer getAnonymous(final UserVo vo) {
        if (vo == null) {
            return GUEST.code;
        }
        return NO_ANONYMOUS.code;
    }
}
