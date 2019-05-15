package com.hero.common.utils.date;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 类说明 ：
 *
 * @ClassName SyncTime
 * @Author hwz.hs
 * @Date 2019/3/30 18:17
 * @Version v_1.0
 */
public class SyncTime {


    private static int sleepMinutes = 0;

    private static final long EPOCH_OFFSET_MILLIS;

    private static final String[] hostName = {"time-a.nist.gov", "time-nw.nist.gov", "time.nist.gov"};
    private static Logger logger = Logger.getLogger(SyncTime.class);

    static {

        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));

        // Java使用的参照标准是1970年，而时间服务器返回的秒是相当1900年的，算一下偏移

        calendar.set(1900, Calendar.JANUARY, 1, 0, 0, 0);

        EPOCH_OFFSET_MILLIS = Math.abs(calendar.getTime().getTime());

    }


    public static void main(String[] args) {
        GetWebTime();
    }

    /**
     * 通过调用本地命令date和time修改计算机时间
     *
     * @param date
     */

    private static void setComputeDate(Date date) {

        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));

        c.setTime(date);

        int year = c.get(Calendar.YEAR);

        int month = c.get(Calendar.MONTH) + 1;

        int day = c.get(Calendar.DAY_OF_MONTH);

        int hour = c.get(Calendar.HOUR_OF_DAY);

        int minute = c.get(Calendar.MINUTE);

        int second = c.get(Calendar.SECOND);


        c.setTime(new Date());

        int year_c = c.get(Calendar.YEAR);

        int month_c = c.get(Calendar.MONTH) + 1;

        int day_c = c.get(Calendar.DAY_OF_MONTH);

        int hour_c = c.get(Calendar.HOUR_OF_DAY);

        int minute_c = c.get(Calendar.MINUTE);

        String ymd = year + "-" + month + "-" + day;
        String time = hour + ":" + minute + ":" + second;
        try {
            // 日期不一致就修改一下日期
            if (year != year_c || month != month_c || day != day_c) {

                String cmd = "cmd /c date " + ymd;

                Process process = Runtime.getRuntime().exec(cmd);

                process.waitFor();

            }


            // 时间不一致就修改一下时间

            if (hour != hour_c || minute != minute_c) {

                String cmd = "cmd  /c  time " + time;

                Process process = Runtime.getRuntime().exec(cmd);

                process.waitFor();

            }

        } catch (IOException ex) {

            logger.info(ex.getMessage());

        } catch (InterruptedException ex) {

            logger.info(ex.getMessage());

        }

    }



    public static void GetWebTime() {
        try {
            URL url = new URL("http://time.tianqi.com/beijing/");//取得资源对象
            URLConnection uc = url.openConnection();//生成连接对象
            uc.connect(); //发出连接
            Date date = new Date(uc.getDate());
            String systemName = getSystem();
            System.out.println(" ---------------->>>> 同步北京系统时间  ");
            if (systemName.equals("windows")) {
                System.out.println("windows 时间修改");
                setComputeDate(date);
            } else if (systemName.equals("linux")) {
                System.out.println("linux 时间修改");
                String dateTime = DateUtils.formatDate(date,"yyyy-MM-dd HH:mm:ss");
                String command = "date -s " + "'" + dateTime + "'";// 格式：yyyy-MM-dd HH:mm:ss
                System.out.println("---->>> 修改时间命令：" + command);
                try {
                    // 起作用的代码其实就下面这一行, 参数是linux中要执行的代码
                    Runtime.getRuntime().exec(command).waitFor();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("操作系统无法识别");
            }

        } catch (Exception e) {
            logger.info(e);
        }

    }


    private static  String getSystem(){
        // 获取用户输入
        String password = "";
        try {
            String address = InetAddress.getLocalHost().getHostAddress().toString();
            if(address.equals("192.168.0.222")){
                password = "windows";
            }else{
                password = "linux";
            }
        } catch (UnknownHostException e) {
            return "linux";
        }
        return password;
    }
}
