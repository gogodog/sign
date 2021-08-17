package com.pw.sign.common.utils;

public class NumUtils {

    public static final char[] cnArr = new char[]{'一','二','三','四','五','六','七','八','九','十'};

    public static String intToCnIn10(int index){
        return String.valueOf(cnArr[index]);
    }
}
