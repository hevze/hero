package com.hero.common.utils.str;

import java.util.List;

/**
 * 类说明 ：
 *
 * @ClassName ObjectAssert
 * @Author hwz.hs
 * @Date 2018/12/25 15:35
 * @Version v_1.0
 */
public class ObjectAssert {

    protected ObjectAssert() {}

    public static boolean isEmpty(Object obj)
    {
        if (obj == null)
        {
            return true;
        }
        if ((obj instanceof List))
        {
            return ((List) obj).size() == 0;
        }
        if ((obj instanceof String))
        {
            return ((String) obj).trim().equals("");
        }
        return false;
    }


    public static boolean isNotEmpty(Object obj)
    {
        return !isEmpty(obj);
    }


}
