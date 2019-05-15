package com.hero.common.constants;

import java.util.Map;

/**
 * 类说明 ：
 *
 * @ClassName SystemConstant
 * @Author hwz.hs
 * @Date 2018/8/25 14:21
 * @Version v_1.0
 */
public interface SystemConstant {

    public String OK = "OK";
    public String FAIL = "FAIL";


    class RedisKey{

        public static String APPNAME = "kingstock";

        public static  String SystemName = APPNAME + ":";

        public static final String AGXTOKEN = SystemName + "agxtoken";

        /**
         * COMMON packge
         */
        public static final String COMMONPACKGE = SystemName + "cache:common:";

        /**
         * 资金密码交易
         */
        public static final String IFCHECKMONEYPWD = COMMONPACKGE + "checkmoneypwd";

        /**
         * 资金密码交易
         */
        public static final String APPTOKEN = COMMONPACKGE + "payautoapptoken";


        /**
         * TIMELY、TIMELINE、BUYSELL数据过期时间是相等的，同一时间删除
         */
        public static final String BUYSELL = SystemName + "b_buysell";
        public static final String TIMELY = SystemName + "b_timely";
        public static final String ZIJIN = SystemName + "b_zijin";
        public static final String TIMELINE = SystemName + "b_timeline:";
        public static final String SECONDSLINE = SystemName + "b_secondsline:";
        //k线
        public static final String DAYK = SystemName + "b_dayk:";
        public static final String ONEK = SystemName + "b_onek:";
        public static final String FIVEK = SystemName + "b_fivek:";
        public static final String FIFTEENK = SystemName + "b_fifteenk:";
        public static final String THIRTYK = SystemName + "b_thirtyk:";
        public static final String SIXTYK = SystemName + "b_sixtyk:";
        public static final String WEEKK = SystemName + "b_weekk:";
        public static final String MONTHK = SystemName + "b_monthk:";

        //redis k线分页
        public static final String PAGEDAY = SystemName + "page:day:";
        public static final String PAGEONE = SystemName + "page:one:";
        public static final String PAGEFIVE = SystemName + "page:five:";
        public static final String PAGEFIFTEEN = SystemName + "page:fifteen:";
        public static final String PAGETHIRTY = SystemName + "page:thirty:";
        public static final String PAGESIXTY = SystemName + "page:sixty:";
        public static final String PAGEWEEK = SystemName + "page:week:";
        public static final String PAGEMONTH = SystemName + "page:month:";

        /*** 设置昨收价 ***/
        public static final String YESTCLOSE = SystemName + "yest:close";
    }
}
