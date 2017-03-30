package com.beauty.usercenter.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BooleanUtils {
    private Logger logger = LoggerFactory.getLogger(BooleanUtils.class);

    private static final String Y_SIGNAL = "Y";
    private static final String N_SIGNAL = "N";

    private static boolean isBooleanFormatString(String inSignal, String format) {
        if (StringUtils.isBlank(inSignal)) {
            return false;
        }
        if (StringUtils.isBlank(format)) {
            return false;
        }
        return format.equalsIgnoreCase(inSignal);
    }

    public static boolean isYFormatString(String in) {
        return isBooleanFormatString(in, Y_SIGNAL);
    }

    public static boolean isNFormatString(String in) {
        return isBooleanFormatString(in, N_SIGNAL);
    }

    public static boolean isYOrNFormatString(String in) {
        if (isYFormatString(in)) {
            return true;
        }
        if (isNFormatString(in)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String[] list = {"y", "Y", "n", "N", "O"};

        for (int i = 0; i < list.length; i++) {
            System.out.println(list[i] + "==================");
            System.out.println(list[i] + " isYOrNFormatString:" + isYOrNFormatString(list[i]));
            System.out.println(list[i] + " isYFormatString:" + isYFormatString(list[i]));
            System.out.println(list[i] + " isNFormatString:" + isNFormatString(list[i]));
        }
    }
}
