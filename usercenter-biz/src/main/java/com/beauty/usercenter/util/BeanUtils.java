package com.beauty.usercenter.util;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class BeanUtils {

    private final static Logger logger = LoggerFactory.getLogger(BeanUtils.class);

    public static String printPro(Object ob) {
        return ToStringBuilder.reflectionToString(ob);
//        String toString = "";
//        if (ob != null) {
//            Class cls = ob.getClass();
//            String name = cls.getSimpleName();
//            Field[] fields = cls.getDeclaredFields();
//            for (int i = 0; i < fields.length; i++) {
//                Field f = fields[i];
//                f.setAccessible(true);
//                try {
//                    toString += f.getName() + ":" + f.get(ob) + ",";
//                } catch (IllegalAccessException e) {
//                    logger.error("printPro occur IllegalAccessException:" + e.getMessage(), e);
//                }
//            }
//            toString = name + "{" + toString.substring(0, toString.length() - 1) + "}";
//        }
//        return toString;
    }


    public static String printPro(List obList) {
        String toString = "";
        if (CollectionUtils.isNotEmpty(obList)) {
            Class cls = obList.getClass();
            String name = cls.getSimpleName();
            for(Object ob:obList){
                toString += printPro(ob)+";";
            }
            toString =name+"["+toString.substring(0, toString.length() - 1)+"]";
        }
        return toString;
    }

//    public static void main(String[] args) {
//        CreditRewardDTO creditRewardDTO = new CreditRewardDTO();
//        creditRewardDTO.setUserId(1007300400L);
//        creditRewardDTO.setRuleCode("01000000001");
//        creditRewardDTO.setAccessType(1);
//        creditRewardDTO.setAccessTransId("100000000001");
//
//        CreditRuleItemQueryDTO creditRuleItemQueryDTO=new CreditRuleItemQueryDTO();
//        creditRuleItemQueryDTO.setRuleCode("10000000110");
//
//        List<CreditRewardDTO> list=new ArrayList<CreditRewardDTO>();
//        list.add(creditRewardDTO);
//        list.add(creditRewardDTO);
//        String str = printPro(list);
//        System.out.println("printPro:" + str);
//    }

    /**
     * 方法描述：将o1对象中的值copy到o2对象中去
     * @param: o1 源对象,o2 目标对象
     * @param source
     * @param target
     */
    public static void copy(Object source, Object target) {
        org.springframework.beans.BeanUtils.copyProperties(source, target);
    }
}
