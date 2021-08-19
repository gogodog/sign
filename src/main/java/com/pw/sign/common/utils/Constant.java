package com.pw.sign.common.utils;

import java.util.regex.Pattern;

/**
 * Constant
 *
 * @author Prescott.Wen
 * @version V1.0
 * @date 2021年7月18日
 */
public class Constant {

    /**
     * 未删除值
     */
    public static final Integer DATA_NOT_DELETED = 0;

    /**
     * 数据库类型
     */
    public static final String DB_TYPE_MYSQL = "mysql";

    /**
     * 用户cookie主键
     */
    public static final String USER_INFO_COOKIE_KEY = "D_MIND_SKY";

    /**
     * 用户cookie expire(second)
     */
    public static final int USER_INFO_COOKIE_EXPIRE = 60 * 60 * 24 * 7;

}
