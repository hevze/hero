package com.hero.common.utils.str;

import java.util.UUID;

/**
 * 字符串工具类
 *
 * @author Hill
 */
public class StringUtil  {

    /**
     * 判断字符串是否为空(自动截取首尾空白)
     *
     * @param str 源字符串
     * @return
     */
    public static boolean isEmpty(String str) {
        return isEmpty(str, true);
    }


    /**
     * 判断字符串是否为空
     *
     * @param str  源字符串
     * @param trim 是否截取首尾空白
     * @return
     */
    public static boolean isEmpty(String str, boolean trim) {
        return str == null ? true : "".equals(str.trim());
    }

    /**
     * 生成GUID
     *
     * @return
     */
    public static String getGuid() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll("-", "").toUpperCase();
    }

}
