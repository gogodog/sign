package com.pw.sign.controller.helper;

import com.pw.sign.enums.BooleanInt;
import com.pw.sign.service.XUserService;
import com.pw.sign.vo.UserVo;

public class RouteControllerHelper {

    /**
     * 如果需要登陆：已经登陆则不需要登陆，未登录需要登陆
     *
     * @param xuserService
     * @param nLogin
     */
    public static String needLoginHandler(final XUserService xuserService, String nLogin) {
        if (BooleanInt.YES.getEnCode().equals(nLogin)) {
            UserVo user = xuserService.getUserFromCookie();
            if (user == null) {
                return BooleanInt.YES.getEnCode();
            }
        }
        return BooleanInt.NO.getEnCode();
    }
}
