package com.hero.common.utils.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateStampConvertUtil {
    /**
     * 时间戳转换成日期格式字符串
     *
     * @param seconds 精确到秒的字符串
     * @param format
     * @return
     */
    public static String timeStamp2Date(String seconds, String format) {
        if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
            return "";
        }
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds + "000")));
    }

    /**
     * 日期格式字符串转换成时间戳
     *
     * @param date_str 字符串日期
     * @param format   如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static long date2TimeStamp(String date_str, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(date_str).getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0l;
    }

    /**
     * 取得当前时间戳（精确到秒）
     *
     * @return
     */
    public static String timeStamp() {
        long time = System.currentTimeMillis();
        String t = String.valueOf(time / 1000);
        return t;
    }


    /**

     9:15-9:20 可挂可撤   不会成交
     9:20-9:25 可挂不可撤 26分那一刻成交
     9:25-9:30 可挂不可撤 不会成交
     9:30-11:30  正常交易
     11:30-13:00 可挂不可撤 不成交
     13:00-14:57  正常交易
     14:57-15:00 可挂不可撤 00那一刻成交

     0: 不可交易，1，可挂可撤 ，2，可挂不可撤，3 正常交易
     */
    public static  int tradeStatus(){
        int currentTime = Integer.parseInt(DateUtils.formatDate(new Date(),"HHmm"));
        int n = 0;
        if(915 <= currentTime && currentTime < 920){
            n = 1;
        }else if(920 <= currentTime && currentTime < 925){
            n = 2;
        }else if(926 == currentTime){
            n = 3;
        }else if(927 <= currentTime && currentTime < 930){
            n = 2;
        }else if(930 < currentTime && currentTime < 1130){
            n = 3;
        }else if(1130 < currentTime && currentTime < 1300){
            n = 2;
        }else if(1300 < currentTime && currentTime < 1457){
            n = 3;
        }else if(1457 < currentTime && currentTime < 1459){
            n = 2;
        }else if(1500 == currentTime){
            n = 3;
        }
        return n;
    }


    public static void main(String[] args) {
       /* String timeStamp = "1519459503";
        timeStamp();
        System.out.println("timeStamp=" + timeStamp); //运行输出:timeStamp=1470278082
        System.out.println(System.currentTimeMillis());//运行输出:1470278082980
        //该方法的作用是返回当前的计算机时间，时间的表达格式为当前计算机时间和GMT时间(格林威治时间)1970年1月1号0时0分0秒所差的毫秒数

        String date = timeStamp2Date(1533052800 + "", "MM");
        System.out.println("date=" + date);//运行输出:date=2016-08-04 10:34:42

        String timeStamp2 = date2TimeStamp("2018-02-24 16:05:03", "yyyy-MM-dd HH:mm:ss");
        System.out.println(timeStamp2);  //运行输出:1470278082


        String timeStamp3 = date2TimeStamp("2018-02-24", "yyyy-MM-dd");
        System.out.println(timeStamp3);  //运行输出:1470278082

        String timeStamp4 = date2TimeStamp("2018-02-24 16:05:00", "yyyy-MM-dd HH:mm");
        System.out.println(timeStamp4);  //运行输出:1470278082


        String timeStamp5 = date2TimeStamp("2018-02-24", "MM");
        System.out.println(timeStamp5);  //运行输出:1470278082*/

        SimpleDateFormat dft=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        Calendar  dar=Calendar.getInstance();
        dar.setTime(date);
        dar.add(Calendar.HOUR_OF_DAY, -2);
        System.out.println(dar.getTime().getTime());

        dar.add(Calendar.HOUR_OF_DAY, 2);
        System.out.println(dft.format(dar.getTime()));

    }
}
