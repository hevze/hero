package com.hero.common.cache;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository("cacheServiceTemplate")
public class CacheServiceTemplate {

    @Autowired
    RedisClientTemplate redisClientTemplate;

    public <T> T findCache(String key , Integer expire, TimeUnit unit, TypeReference<T> clazz, CacheLoadable<T> cacheLoadable){
        String redisJson = redisClientTemplate.get(key);
        if(StringUtils.isNotEmpty(redisJson)){
            return JSON.parseObject(redisJson,clazz);
        }
        synchronized (this){
            redisJson = redisClientTemplate.get(key);
            if(StringUtils.isNotEmpty(redisJson) && !"null".equals(redisJson)){
                return JSON.parseObject(redisJson,clazz);
            }
            T result = cacheLoadable.load();
            if(null != result) {
                redisClientTemplate.setsx(key, JSON.toJSON(result).toString(), expire);
            }
            return result;
        }
    }

}
