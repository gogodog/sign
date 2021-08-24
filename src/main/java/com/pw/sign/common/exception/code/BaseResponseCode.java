package com.pw.sign.common.exception.code;


/**
 * 错误码
 *
 * @author Prescott.Wen
 * @version V1.0
 * @date 2021年7月18日
 */
public enum BaseResponseCode implements ResponseCodeInterface {
    /**
     * 错误码
     */
    SUCCESS(0, "操作成功"),
    SYSTEM_BUSY(500001, "系统繁忙，请稍候再试"),
    OPERATION_ERRO(500002, "操作失败"),
    DATA_ERROR(401003, "传入数据异常"),
    METHOD_ARGUMENT_NOT_VALID_EXCEPTION(401007, "方法参数校验异常"),
    VERITY_CODE_NOT_VALID_EXCEPTION(402001, "验证码校验失败"),
    NEW_USER_ERRO(402002, "新建用户异常"),
    NO_USER(402003, "用户不存在"),
    EXISTED_USER(402004, "用户已存在"),
    NO_LOGIN(402005, "用户未登录"),
    TEL_FORMAT_ERROR(402006, "电话号码格式错误"),
    FOLLOW_ADD_SUCCESS(403000, "关注成功"),
    FOLLOW_CANCEL_SUCCESS(403001, "关注取消成功");

    /**
     * 错误码
     */
    private final int code;
    /**
     * 错误消息
     */
    private final String msg;

    BaseResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
