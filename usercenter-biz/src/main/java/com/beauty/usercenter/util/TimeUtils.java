package com.beauty.usercenter.util;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class TimeUtils {
    private static int[] dayCountsOfMonth = new int[]{31,59,90,120,151,181,212,243,273,304,334,365};
    public static final String DATE_FORAMT = "yyyy-MM-dd";
	public static final String TIME_FORAMT = "HH:mm:ss";
	public static final String TIME_HOUR_FORAMT = "HH:mm";
	public static final String DATE_TIME_FORMAT = DATE_FORAMT +" " + TIME_FORAMT ;
    public static final String DATE_NO_LINE_FORMAT = "yyyyMMdd";
    public static final String DATE_NO_LINE_TIME_FORMAT = DATE_NO_LINE_FORMAT +" " + TIME_FORAMT ;

    public static boolean isAfterNow(Date date){
    	if( date == null ){
    		return false ;
    	}
    	long target = date.getTime() ;
    	long current = System.currentTimeMillis() ;
    	return current < target ;
    }
    public static boolean isBeforeNow(Date date){
    	if( date == null ){
    		return false ;
    	}
    	long target = date.getTime() ;
    	long current = System.currentTimeMillis() ;
    	return target < current;
    }
    
    public static String format(Date date, String format){
		if( date == null || format == null || "".equals(format)){
			return null;
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.format(date);
	}
    public static Date parse(String time, String format){
    	if( StringUtils.isBlank(time) ){
    		return null;
    	}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		try {
			return simpleDateFormat.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

    public static String getFormatedCurrentDate()
    {
        SimpleDateFormat dateSDF = new SimpleDateFormat("yyyyMMdd");
        return dateSDF.format(new Date());
    }
    public static String getFormatedCurrentMonth()
    {
        SimpleDateFormat monthSDF = new SimpleDateFormat("yyyyMM");
        return monthSDF.format(new Date());
    }
    public static String getFormatedDate(long time)
    {
        SimpleDateFormat dateSDF = new SimpleDateFormat("yyyyMMdd");
        return dateSDF.format(new Date(time));
    }
    public static String getFormatedDate(Date date)
    {
        SimpleDateFormat dateSDF = new SimpleDateFormat("yyyyMMdd");
        return dateSDF.format(date);
    }
    public static String getFormatedDate(int year,int month,int date)
    {
        return Integer.toString(year)+
                (month >= 10?Integer.toString(month):"0"+month)+
                (date >= 10?Integer.toString(date):"0"+date);
    }
    public static String getFormatedMonth(int year,int month)
    {
        return Integer.toString(year)+
                (month >= 10?Integer.toString(month):"0"+month);
    }
    //how many days from 1,1 to month,date of the year
    private static int getDayCounts(int year,int month,int date)
    {
        if (month==1)
            return date;
        else
            return dayCountsOfMonth[month-2]+date+(month>=3 && isRenNian(year)?1:0);
    }
    //get how many days between date1 and date2
    //getDateRange("20051112","20051114") should return 2;
    //date2 should be later then date1
    public static int getDateRange(String date1,String date2)
    {
        int y1 = Integer.parseInt(date1.substring(0,4));
        int m1 = Integer.parseInt(date1.substring(4,6));
        int d1 = Integer.parseInt(date1.substring(6,8));

        int y2 = Integer.parseInt(date2.substring(0,4));
        int m2 = Integer.parseInt(date2.substring(4,6));
        int d2 = Integer.parseInt(date2.substring(6,8));

        if (y1 == y2)
        {
            return getDayCounts(y2,m2,d2)-getDayCounts(y1,m1,d1);
        }
        else
        {
            int count = 0;
            for (int i = y1 + 1; i < y2; i++) {
                count += isRenNian(i) ? 366 : 365;
            }
            return count+getDayCounts(y2,m2,d2)+
                    (isRenNian(y1)?366:365)-getDayCounts(y1,m1,d1);
        }
		/*	gaisuan
                int days = 0;
                days = 365*(y2-y1);
                days += 30*(m2-m1);
                days += d2-d1;
                return days;*/
    }

    /**
     * format time as HHmmssSSS
     */
    public static String getFormatedCurrentTime9()
    {
        SimpleDateFormat timeSDF9=new SimpleDateFormat("HHmmssSSS");
        return timeSDF9.format(new Date());
    }

    public static String getFormatedCurrentTime6()
    {
        SimpleDateFormat timeSDF6=new SimpleDateFormat("HHmmss");
        return timeSDF6.format(new Date());
    }
    public static String getCurrentTime17ByMillis()
    {
        SimpleDateFormat timeSDF17 = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return timeSDF17.format(new Date());
    }

    public static String getTime17ByMillis(long time)
    {
        SimpleDateFormat timeSDF17 = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return timeSDF17.format(time);
    }

    public static long getTime17ByMillis(String time)
    {
        SimpleDateFormat timeSDF17 = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        long rtTime =0;
        try
        {
            rtTime=timeSDF17.parse(time).getTime();
        }
        catch (Exception ex)
        {
            //ex.printStackTrace();
        }
        return rtTime;
    }


    public static String getCurrentTime12ByMillis()
    {
        SimpleDateFormat timeSDF12=new SimpleDateFormat("yyyyMMddHHmm");
        return timeSDF12.format(new Date());
    }

    public static String getTime12ByMillis(long time)
    {
        SimpleDateFormat timeSDF12=new SimpleDateFormat("yyyyMMddHHmm");
        return timeSDF12.format(time);
    }

    public static long getTime12ByMillis(String time)
    {
        SimpleDateFormat timeSDF12=new SimpleDateFormat("yyyyMMddHHmm");
        long rtTime =0;
        try
        {
            rtTime=timeSDF12.parse(time).getTime();
        }
        catch (Exception ex)
        {
            //ex.printStackTrace();
        }
        return rtTime;
    }

    public static String getTime8ByMillis(long time){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(time);
    }

    public static long getTime8ByMillis(String str) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = sdf.parse(str);
        return date.getTime();
    }

    public static long getMinuteRangeByMillis(long time1,long time2)
    {
        return (time1-time2)/(1000*60);
    }

    public static String getPreYear(String date)
    {
        int y = Integer.parseInt(date.substring(0,4));
        return Integer.toString(y-1);
    }
    public static String getThisYear(String date)
    {
        return date.substring(0,4);
    }
    public static String getYear(String date)
    {
        return date.substring(0,4);
    }
    public static String getDate(String date)
    {
        return date.substring(4,8);
    }
    public static String getNextYear(String date)
    {
        int y = Integer.parseInt(date.substring(0,4));
        return Integer.toString(y+1);
    }
    public static int getLastDateOfMonth(int year,int month)
    {
        if ((month == 4) ||(month == 6) || (month == 9) ||(month == 11))
        {
            return 30;
        }
        else if (month == 2)
        {
            if (isRenNian(year))
                return 29;
            else
                return 28;
        }
        else
        {
            return 31;
        }
    }
    public static String getPreDate(String date,int count)
    {
        for (int i=0;i<count;i++)
            date = getPreDate(date);
        return date;
    }
    public static String getNextDate(String date,int count)
    {
        for (int i=0;i<count;i++)
            date = getNextDate(date);
        return date;
    }
    public static String getPreMonth(String month)
    {
        int y = Integer.parseInt(month.substring(0,4));
        int m = Integer.parseInt(month.substring(4,6));
        if (--m<1)
        {
            m = 12;y--;
        }
        return getFormatedMonth(y,m);
    }
    public static String getNextMonth(String month)
    {
        int y = Integer.parseInt(month.substring(0,4));
        int m = Integer.parseInt(month.substring(4,6));
        if (++m>12)
        {
            m = 1;y++;
        }
        return getFormatedMonth(y,m);
    }

    /**
     * 获取下个月初
     */
    public static String getNextMonthFirstDay(String date){
        return getNextMonth(date)+"01";
    }

    public static String getPreDate(String date)
    {
        int y = Integer.parseInt(date.substring(0,4));
        int m = Integer.parseInt(date.substring(4,6));
        int d = Integer.parseInt(date.substring(6,8));

        if (d>1)
            return getFormatedDate(y,m,d-1);
        if ((m == 2) || (m == 4) ||(m == 6) ||(m == 8) ||(m == 9) ||(m == 11))
        {
            return getFormatedDate(y,m-1,31);
        }
        else if ((m==5) || (m==7) || (m==10) || (m==12))
        {
            return getFormatedDate(y,m-1,30);
        }
        else if (m==3)
        {
            if (isRenNian(y))
                return getFormatedDate(y,2,29);
            else
                return getFormatedDate(y,2,28);
        }
        else
        {	//m == 1 and d==1
            return getFormatedDate(y-1,12,31);
        }
    }
    //n should between 1 to 12
    public static String getDayOfNextNMonth(String date,int n)
    {
        int y = Integer.parseInt(date.substring(0, 4));
        int m = Integer.parseInt(date.substring(4, 6));
        int d = Integer.parseInt(date.substring(6, 8));
        m+=n;
        if (m>12)
        {
            m -= 12;
            y++;
        }
        if (d>28)
        {
            if (m==2)
            {
                if (isRenNian(y))
                    m=29;
                else
                    m=28;
            }
        }
        if (d == 31)
        {
            if ((m==4) || (m==6) || (m==9) || (m==11))
            {
                d = 30;
            }
        }
        return getFormatedDate(y,m,d);
    }
    public static String getDayOfNextYear(String date)
    {
        int y = Integer.parseInt(date.substring(0, 4));
        int m = Integer.parseInt(date.substring(4, 6));
        int d = Integer.parseInt(date.substring(6, 8));
        if ((m==2) && (d==29))
        {
            return getFormatedDate(y+1,2,28);
        }
        else
        {
            return getFormatedDate(y+1,m,d);
        }
    }
    public static String getNextDate(String date)
    {
        int y = Integer.parseInt(date.substring(0,4));
        int m = Integer.parseInt(date.substring(4,6));
        int d = Integer.parseInt(date.substring(6,8));
        if (d<28)
        {
            return getFormatedDate(y,m,d+1);
        }
        else if (d==31)
        {
            if (m == 12)
                return getFormatedDate(y+1,1,1);
            else
                return getFormatedDate(y,m+1,1);
        }
        else if (d==30)
        {
            if ((m == 4) || (m == 6) || (m == 9) || (m == 11))
            {
                return getFormatedDate(y,m+1,1);
            }
            else
            {
                return getFormatedDate(y,m,31);
            }
        }
        else if (d==29)
        {
            if (m == 2)
                return getFormatedDate(y,3,1);
            else
                return getFormatedDate(y,m,30);
        }
        else
        {	//d = 28
            if (m == 2)
            {
                if (isRenNian(y))
                    return getFormatedDate(y,m,29);
                else
                    return getFormatedDate(y,3,1);
            }
            else
            {
                return getFormatedDate(y,m,29);
            }
        }
    }


    public static boolean isTimeValid(int y,int m,int d)
    {
        if ( (y < 1000) || (y > 9999)
                || (m < 1) || (m > 12) || (d < 1) || (d > 31))
            return false;
        if ((d == 31) && ((m == 2) || (m == 4) || (m == 6) || (m == 9) || (m == 11)))
            return false;
        if (((d==30) && ( m == 2)) || ((d==29) && (m==2) && (!isRenNian(y))))
        {
            return false;
        }
        return true;
    }
    public static boolean isTimeValid(int y,int m)
    {
        if ( (y < 1000) || (y > 9999)
                || (m < 1) || (m > 12))
            return false;
        return true;
    }

    public static boolean isDateValid(String date)
    {
        try
        {
            if (date.length()== 8)
            {
                int y = Integer.parseInt(date.substring(0, 4));
                int m = Integer.parseInt(date.substring(4, 6));
                int d = Integer.parseInt(date.substring(6, 8));
                if (TimeUtils.isTimeValid(y,m,d))
                    return true;
            }
        }
        catch (NumberFormatException ne)
        {

        }
        return false;
    }

    public static boolean isRenNian(int year)
    {
        if (year != year/4*4)
        {
            return false;
        }
        if (year != year/100*100)
        {
            return true;
        }
        if (year != year/400*400)
        {
            return false;
        }
        return true;
    }
    public static long getDayStart(String datestr)
    {//YYYYMMDD
        int year = Integer.parseInt(datestr.substring(0,4))-1900;
        int month = Integer.parseInt(datestr.substring(4,6))-1;
        int date = Integer.parseInt(datestr.substring(6,8));

        GregorianCalendar cal = new GregorianCalendar(year+1900,month,date,0,0,0);
                /*Date d = new Date(year,month,date,0,0,0);
                return d.getTime();*/
        return cal.getTimeInMillis();
    }
    public static long getDayEnd(String datestr)
    {//YYYYMMDD
        int year = Integer.parseInt(datestr.substring(0,4))-1900;
        int month = Integer.parseInt(datestr.substring(4,6))-1;
        int date = Integer.parseInt(datestr.substring(6,8));

        GregorianCalendar cal = new GregorianCalendar(year+1900,month,date,23,59,60);
        return cal.getTimeInMillis();

                /*Date d = new Date(year,month,date,23,59,60);
                return d.getTime();*/
    }
    public static String getDateDisplay(String showdate,String year,String month,String date)
    {
        StringBuffer sb = new StringBuffer(16);
        sb.append(showdate.substring(0,4)).append(year).append(showdate.substring(4,6)).
                append(month).append(showdate.substring(6,8)).append(date);
        return sb.toString();
    }

    public static boolean isSchedul(String firstDate,long curryDate,int duration){
        int dayIntenal = getDateRange(firstDate,getTime8ByMillis(curryDate));
        if(dayIntenal == duration){
            return true;
        }
        return false;
    }

    public static boolean isSameWeek(String firstDate,String secendDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyMMdd");
        Date date1 = null;
        Date date2 = null;
        try {
            date1 = format.parse(firstDate);
            date2 = format.parse(secendDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
        if (subYear > 0) {
            return false;
        }
        if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR)){
            return true;
        }
        return false;
    }

    public static boolean isPreWeek(String firstDate,String secendDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyMMdd");
        Date date1 = null;
        Date date2 = null;
        try {
            date1 = format.parse(firstDate);
            date2 = format.parse(secendDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
        if (subYear > 0) {
            return false;
        }
        if (cal1.get(Calendar.WEEK_OF_YEAR)+1 == cal2.get(Calendar.WEEK_OF_YEAR)){
            return true;
        }
        return false;
    }

    public static boolean isSameMonth(String firstDate,String secendDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyMMdd");
        Date date1 = null;
        Date date2 = null;
        try {
            date1 = format.parse(firstDate);
            date2 = format.parse(secendDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
        if (subYear > 0) {
            return false;
        }
        int month1 = cal1.get(Calendar.MONTH) + 1;
        int month2 = cal2.get(Calendar.MONTH) + 1;
        if(month1 == month2){
            return true;
        }
        return false;
    }


    public static boolean isPreMonth(String firstDate,String secendDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyMMdd");
        Date date1 = null;
        Date date2 = null;
        try {
            date1 = format.parse(firstDate);
            date2 = format.parse(secendDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
        if (subYear > 0) {
            return false;
        }
        int month1 = cal1.get(Calendar.MONTH) + 1 +1;
        int month2 = cal2.get(Calendar.MONTH) + 1;
        if(month1 == month2){
            return true;
        }
        return false;
    }

    public static boolean isYyyyMMddHHmmssDate(String inDate) {
        return isValidDate(inDate, DATE_NO_LINE_TIME_FORMAT);
    }

    public static boolean isYyyyMMddDate(String inDate) {
        return isValidDate(inDate, DATE_NO_LINE_FORMAT);
    }

    private static boolean isValidDate(String inDate, String dateFormatString) {
        if (inDate == null) {
            return false;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatString);
        if (inDate.trim().length() != dateFormat.toPattern().length()) {
            return false;
        }
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

    public static void main(String[] args){
        String dateS = "20151012 12:31:30";
        System.out.println(isYyyyMMddHHmmssDate(dateS));
        System.out.println(isYyyyMMddDate(dateS));

    }
}
