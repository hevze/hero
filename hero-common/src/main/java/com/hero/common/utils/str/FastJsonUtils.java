package com.hero.common.utils.str;


import com.alibaba.fastjson.JSON;

import java.util.List;

public class FastJsonUtils {
    public static <T> T fromJson(String s, Class<T> t) {
        return JSON.parseObject(s, t);
    }

    public static <T> List<T> fromJsonArray(String s, Class<T> t) {
        return JSON.parseArray(s, t);
    }

    public static  String toJson(Object t) {
        return JSON.toJSONString(t);
    }

}
