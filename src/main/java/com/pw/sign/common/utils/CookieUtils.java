package com.pw.sign.common.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class CookieUtils {

    private static final String UTF8 = "utf-8";

    public static String getCookie(HttpServletRequest request, String cookieName) throws UnsupportedEncodingException {

        Cookie[] cookies =  request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals(cookieName)){
                    return URLDecoder.decode(cookie.getValue(), UTF8);
                }
            }
        }

        return null;
    }

    /**
     * @param response
     * @param cookieName
     * @param value
     * @param cookieMaxAge 单位秒
     */
    public static void setCookie(HttpServletResponse response, String cookieName, String value, int cookieMaxAge) throws UnsupportedEncodingException {
        Cookie cookie = new Cookie(cookieName, URLEncoder.encode(value, UTF8));
        cookie.setPath("/");
        cookie.setMaxAge(cookieMaxAge);
        response.addCookie(cookie);
    }

    public static void deleteCookie(HttpServletResponse response, String cookieName) throws UnsupportedEncodingException {
        setCookie(response,cookieName,null,0);
    }

}
