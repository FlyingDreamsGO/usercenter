package com.beauty.usercenter.util;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;


public class ListUtils {
	public static final String SPERATOR = ",";

	
	
	public static List<Long> parse2LongArray(String string) {
		if (StringUtils.isBlank(string)) {
            return null;
        }
		String[] strs = string.split(SPERATOR);
		List<Long> list = new ArrayList<Long>();
		for (int i = 0; i < strs.length; i++) {
			String val = strs[i];
			if (val != null && !val.trim().equals("")) {
				Long v = Long.parseLong(val);
				list.add(v);
			}
		}
		
		return list;
	}
	public static <T> String listToString(List<T> list){
		return listToString(list, null, null);
	}
	public static <T> String listToString(List<T> list, String prefix, String suffix ){
		if( list != null ){
			prefix = prefix == null ? "" : prefix ;
			suffix = suffix == null ? "" : suffix ;
			StringBuffer sb = new StringBuffer(prefix) ;
			int size = list.size() ;
			for(int i=0 ; i<size ; i++ ){
				sb.append(list.get(i)) ;
				if( i != size -1 ){
					sb.append(SPERATOR);
				}
			}
			sb.append(suffix);
			return sb.toString() ;
		}
		return null ;
	}
    public static List<Long> longArray2List(long[] ts){
    	if( ts != null ){
    		List<Long> list =new ArrayList<Long>() ;
    		for( long t : ts ){
    			list.add(t) ;
    		}
    		return list ;
    	}
    	return null ;
    }
    
    public static String longArray2String(long[] array ){
		if( array != null ){
			StringBuffer sb = new StringBuffer() ;
			int size = array.length ;
			for(int i=0 ; i<size ; i++ ){
				sb.append(array[i]) ;
				if( i != size -1 ){
					sb.append(SPERATOR);
				}
			}
			return sb.toString() ;
		}
		return null ;
	}
    
	
	/**
	 * 是否在数组中
	 * @param val
	 * @param vals
	 * @return
	 */
	public static <T> boolean inArray(T val, T [] vals){
		if( val != null && vals != null && vals.length > 0 ){
			for( T v : vals ){
				if( v != null &&( v == val || v.equals(val)) ){
					return true;
				}
			}
		}
		return false;
	}
	public static boolean inArray(int val, int [] vals){
		if( vals != null && vals.length > 0 ){
			for( long v : vals ){
				if(  v == val ){
					return true;
				}
			}
		}
		return false;
	}
//
    public static List<String> string2List(String str) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        String[] array = StringUtils.split(str, SPERATOR);
        if (array == null || array.length == 0) {
            return null;
        }
        
        List<String> list = new ArrayList<String>();
        for (String s : array) {
        	if( !StringUtils.isBlank(s) ){
        		list.add(s);
        	}
        }
        return list;
    }
//    
//	public static String[] stringList2Array(List<String> list) {
//		if (list != null && list.size() > 0) {
//			int size = list.size();
//			if (size > 0) {
//				String[] array = new String[size];
//				for (int i = 0; i < size; i++) {
//					array[i] = list.get(i);
//				}
//				return array;
//			}
//		}
//		return null;
//	}
//    
//	public static long[] longList2Array(List<Long> list) {
//		if (list != null && list.size() > 0 ) {
//			int size = list.size() ;
//			if( size > 0 ){
//				long[] array = new long[size] ;
//				for( int i=0 ; i<size ; i++ ){
//					array[i] = list.get(i);
//				}
//				return array ;
//			}
//		}
//		return null;
//	}
//	
//	public static <T> String listToString(List<T> list){
//		return listToString(list, null, null) ;
//	}
//	
	public static <T> String array2String(T[] array){
		return array2String(array, null, null) ;
	}
	public static <T> String array2String(T[] array, String prefix, String suffix ){
		if( array != null ){
			prefix = prefix == null ? "" : prefix ;
			suffix = suffix == null ? "" : suffix ;
			StringBuffer sb = new StringBuffer(prefix) ;
			int size = array.length ;
			for(int i=0 ; i<size ; i++ ){
				sb.append(array[i]) ;
				if( i != size -1 ){
					sb.append(SPERATOR);
				}
			}
			sb.append(suffix);
			return sb.toString() ;
		}
		return null ;
	}
//    public static String strAddSperator(String str){
//        String result="";
//        if(str==null||"".equals(str)){
//            return null;
//        }
//        String[] strs=StringUtils.split(str,SPERATOR);
//        for(int i=0;i<strs.length;i++){
//            result+=","+strs[i];
//            if(i==strs.length-1){
//                result=result+",";
//            }
//        }
//        return result;
//    }
//    
    public static <T> List<T> array2List(T[] ts){
    	if( ts != null ){
    		List<T> list =new ArrayList<T>(ts.length) ;
    		for( T t : ts ){
    			list.add(t) ;
    		}
    		return list ;
    	}
    	return null ;
    }
	

}
