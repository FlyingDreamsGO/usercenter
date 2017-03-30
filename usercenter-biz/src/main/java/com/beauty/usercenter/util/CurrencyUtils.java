package com.beauty.usercenter.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class CurrencyUtils {

    private static final Logger logger = LoggerFactory.getLogger(CurrencyUtils.class);
    private static final String DOT    = Pattern.quote(".");

    /**
     * 根据精确度乘以相应10的倍数，返回long型整数
     * @param price
     * @param precision
     * @return
     */
    public static long convertString2Long(String price, int precision) {
        if (!isPrecisionString(price, precision)) {
            return 0;
        }
        double priceD = Double.valueOf(price);
        int plusNum = (int)Math.pow(10, precision);
        return (long)(priceD * plusNum);
    }

    /**
     * 根据精确度乘以相应10的倍数，返回String型整数
     * @param price
     * @param precision
     * @return
     */
    public static String convertString2String(String price, int precision) {
        if (!isPrecisionString(price, precision)) {
            return null;
        }
        int plusNum = (int)Math.pow(10, precision);
        BigDecimal bigDecimalPrice = new BigDecimal(price);
        BigDecimal bigDecimal1PlusNum = new BigDecimal(String.valueOf(plusNum));
        return bigDecimalPrice.multiply(bigDecimal1PlusNum).setScale(0).toString();
    }

    /**
     * 检查String格式的数字小数部分是否符合规范
     * @param price
     * @param precision
     * @return
     */
    private static boolean isPrecisionString(String price, int precision) {
        boolean flag = false;
        try {
            if (!isPrecisionString(price)) {
                return false;
            }
            String decimal = price.split(DOT)[1];
            return decimal.length() == precision ? true : false;
        } catch (ArrayIndexOutOfBoundsException e1) {
            logger.error("ArrayIndexOutOfBoundsException e:{}", e1);
            return false;
        } catch (Exception e) {
            logger.error("Exception e:{}", e);
            return false;
        }
    }

    /**
     * 检查String类型带小数价格数字是否符合规范
     * @param price
     * @return
     */
    private static boolean isPrecisionString(String price) {
        boolean flag = false;
        try {
            String[] parts = price.split(DOT);
            if (parts.length != 2) {
                return false;
            } else if (!StringUtils.isNumeric(parts[1]) || !StringUtils.isNumeric(parts[0])) {
                return false;
            }
            return true;
        } catch (ArrayIndexOutOfBoundsException e1) {
            logger.error("ArrayIndexOutOfBoundsException e:{}", e1);
            return false;
        } catch (Exception e) {
            logger.error("Exception e:{}", e);
            return false;
        }
    }

    public static boolean is2PrecisionString(String price) {
        return isPrecisionString(price, 2);
    }

    public static boolean isZeroPrecisionString(String price) {
        try {
            if (!isPrecisionString(price)) {
                return false;
            }
            String[] parts = price.split(DOT);
            if (Integer.valueOf(parts[0]) == 0 && Integer.valueOf(parts[1]) == 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            logger.error("Exception e:{}", e);
            return false;
        }
    }

    public static void main(String[] args) {
        //        long resultL = CurrencyUtils.convertString2Long("123.12", 2);
        //        System.out.println(resultL);
        //        String resultS = CurrencyUtils.convertString2String("123.12", 2);
        //        System.out.println(resultS);
        //
        //        System.out.println(isPrecisionString("123.12", 2));
        //        System.out.println(isPrecisionString("123.123", 2));
        //        System.out.println(isPrecisionString("123.12",3));
        //
        //
        //        Integer i1 = 201;
        //        Integer i2 = 201;
        //        Integer i3 = 1000;
        //        Integer i4 = 1000;
        //        System.out.println(i1==i2);//false
        //        System.out.println(i1.equals(i2));//true
        //        System.out.println(i3 == i4);//false

        //        String price = "39642.38";
        //        int plusNum = 100;
        //
        //        BigDecimal bigDecimal = new BigDecimal(price);
        //        BigDecimal bigDecimal1PlusNum = new BigDecimal(String.valueOf(plusNum));
        //
        //
        //        String s1 = bigDecimal.multiply(bigDecimal1PlusNum).setScale(0).toString();
        //        System.out.println(s1);

        //        double priceD = Double.valueOf(price);
        //        long l = (long)(priceD * plusNum);
        //        double d = priceD * plusNum;
        //        String  s = String.valueOf((long)(priceD * plusNum));
        //
        //        System.out.println(s);

                String price = "0.00"; // "10.00"; "0.01"
                System.out.println(CurrencyUtils.isZeroPrecisionString(price));

    }
}
