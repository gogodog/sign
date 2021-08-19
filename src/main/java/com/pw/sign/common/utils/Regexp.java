package com.pw.sign.common.utils;

import java.util.regex.Pattern;

public class Regexp {

    public static final String TEL_REGEXP = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";

    public static boolean isMobile(String i) {
        Pattern pattern = Pattern.compile(TEL_REGEXP);
        return pattern.matcher(i).matches();
    }
}
