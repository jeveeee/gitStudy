package com.ideamerry.repair.util;

import org.springframework.util.StringUtils;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

    /**
     * 获取今天的0点时间
     * 2014年7月12日
     */
    public static Date getTodayStartTime() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }

    /**
     * 格式 化日期，返回符合格式的字符串
     *
     * @param date
     * @param formater 如:yyyy-MM-dd HH:mm:ss
     * @return
     */
    private static String getDateStrCompact(Date date, String formater) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat format = new SimpleDateFormat(formater);
        String str = format.format(date);
        return str;
    }

    /**
     * 格式 化日期，返回符合格式的字符串
     *
     * @param date
     * @return
     */
    public static String dateToString(Date date, String formatStr) {
        if (formatStr == null || "".equals(formatStr.trim())) {
            formatStr = "yyyy-MM-dd HH:mm:ss";
        }
        return getDateStrCompact(date, formatStr);
    }

    //获取明天的结束时间
    public static Date getEndDayOfTomorrow() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayEnd());
        cal.add(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    //获取当天的结束时间
    public static Date getDayEnd() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    /**
     * 格式化日期字符串，返回符合格式的date
     *
     * @param dateStr
     * @param formater 如:yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static Date StringToDate(String dateStr, String formater) {
        Date date = null;
        if (formater == null || "".equals(formater.trim())) {
            formater = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat format = new SimpleDateFormat(formater);
        if (!StringUtils.isEmpty(dateStr)) {
            return date;
        }
        try {
            date = format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 将日期增减 n 天
     *
     * @param date 参照日期,如果为null则取当前日期
     * @param days 增减的天数 (为正数则增加，为负数则减少)
     * @return Date
     */
    public static Date addDate(Date date, int days) {
        return addDate(date, days, Calendar.DATE);
    }

    /**
     * 取某一日期增减 n 值后的日期, n 由 dateField 决定是年、月、日 根据增加or减少的时间得到新的日期
     *
     * @param date      参照日期
     * @param counts    增减的数值
     * @param dateField int 需操作的日期字段, 取值范围如下 Calendar.YEAY 年 Calendar.MONTH 月
     *                  Calendar.DATE 日 .... Calendar.SECOND 秒
     * @return Date
     */
    private static Date addDate(Date date, int counts, int dateField) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(dateField, counts);
        return calendar.getTime();
    }

    /**
     * 返回毫秒时间
     *
     * @param date
     * @return
     */
    public static long getTimeInMillis(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getTimeInMillis();
    }

    /**
     * 返回七天前的日期
     *
     * @return
     */
    public static String getStatetime() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -7);
        Date monday = c.getTime();
        String preMonday = sdf.format(monday);
        return preMonday;
    }

    public static String getDays(String startDate, String endDate) throws ParseException {
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
    /*天数差*/
        Date fromDate1 = simpleFormat.parse(startDate);
        Date toDate1 = simpleFormat.parse(endDate);
        long from1 = fromDate1.getTime();
        long to1 = toDate1.getTime();

    /*小时差*/
        int hours = (int) ((to1 - from1) / (1000 * 60 * 60));
        System.out.println("两个时间之间的小时差为：" + hours);
        DecimalFormat df=new DecimalFormat("0.0");//设置保留位数

        return df.format((float)hours/24)+"天";
    }


    /**
     * 获取今天
     * @return String
     * */
    public static String getToday(){
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }
    /**
     * 获取昨天
     * @return String
     * */
    public static String getYestoday(){
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DATE,-1);
        Date time=cal.getTime();
        return new SimpleDateFormat("yyyy-MM-dd").format(time);
    }
    /**
     * 获取本月开始日期
     * @return String
     * **/
    public static String getMonthStart(){
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.MONTH, 0);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date time=cal.getTime();
        return new SimpleDateFormat("yyyy-MM-dd").format(time)+" 00:00:00";
    }
    /**
     * 获取本月最后一天
     * @return String
     * **/
    public static String getMonthEnd(){
        Calendar cal=Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date time=cal.getTime();
        return new SimpleDateFormat("yyyy-MM-dd").format(time)+" 23:59:59";
    }
    /**
     * 获取本周的第一天
     * @return String
     * **/
    public static String getWeekStart(){
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.WEEK_OF_MONTH, 0);
        cal.set(Calendar.DAY_OF_WEEK, 2);
        Date time=cal.getTime();
        return new SimpleDateFormat("yyyy-MM-dd").format(time)+" 00:00:00";
    }
    /**
     * 获取本周的最后一天
     * @return String
     * **/
    public static String getWeekEnd(){
        Calendar cal=Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, cal.getActualMaximum(Calendar.DAY_OF_WEEK));
        cal.add(Calendar.DAY_OF_WEEK, 1);
        Date time=cal.getTime();
        return new SimpleDateFormat("yyyy-MM-dd").format(time)+" 23:59:59";
    }
    /**
     * 获取本年的第一天
     * @return String
     * **/
    public static String getYearStart(){
        return new SimpleDateFormat("yyyy").format(new Date())+"-01-01 00:00:00";
    }

    /**
     * 获取本年的最后一天
     * @return String
     * **/
    public static String getYearEnd(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH,calendar.getActualMaximum(Calendar.MONTH));
        calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date currYearLast = calendar.getTime();
        return new SimpleDateFormat("yyyy-MM-dd").format(currYearLast)+" 23:59:59";
    }
}
