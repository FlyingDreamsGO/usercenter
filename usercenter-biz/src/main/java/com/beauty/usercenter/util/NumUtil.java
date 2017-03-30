package com.beauty.usercenter.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class NumUtil {
	private static final Logger logger = LoggerFactory.getLogger(NumUtil.class);
	
	
    public static long getLong(String value) {
        if (StringUtils.isBlank(value)) {
            return 0;
        }
        if (StringUtils.isNumeric(value)) {
            return Long.valueOf(value);
        }
        return 0;
    }
    
    public static long string2Long(String value){
		long result = 0 ;
		if( value != null ){
			try{
				result = Long.parseLong(value);
			}catch(Exception e){
				logger.error("Long.parseLong(value)   value="+value, e);
			}
		}
		return result ;
	}
}
