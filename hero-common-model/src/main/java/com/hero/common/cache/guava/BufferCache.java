package com.hero.common.cache.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BufferCache implements Closeable {
    // CacheBuilder的构造函数是私有的，只能通过其静态方法newBuilder()来获得CacheBuilder的实例
    private static Cache localCacheData;
    private int maxItemSize = 1000;
    private String key = "defaultKey";
    private static final Object lock = new Object();

    public BufferCache(String key, int currencyLevel, int writeExpireTime,
                       int accessExpireTime, int initialCapacity, int maximumSize,
                       int maxItemSize, RemovalListener removalListener) {
        currencyLevel = currencyLevel < 1 ? 1 : currencyLevel;
        initialCapacity = initialCapacity < 100 ? 100 : initialCapacity;
        if (key != null) {
            this.key = key;
        }

        this.maxItemSize = maxItemSize;
        if (localCacheData==null) {
            localCacheData = CacheBuilder.newBuilder()
                    // 设置并发级别为8，并发级别是指可以同时写缓存的线程数
                    .concurrencyLevel(currencyLevel)
                    // 设置写缓存后expireTime秒钟过期
                    .expireAfterWrite(writeExpireTime, TimeUnit.SECONDS)
                    // 设置请求后expireTime秒钟过期
                    .expireAfterAccess(accessExpireTime, TimeUnit.SECONDS)
                    // 设置缓存容器的初始容量为10
                    .initialCapacity(initialCapacity)
                    // 设置缓存最大容量为Integer.MAX_VALUE，超过Integer.MAX_VALUE之后就会按照LRU最近虽少使用算法来移除缓存项
                    .maximumSize(maximumSize)
                    // 设置要统计缓存的命中率
                    .recordStats()
                    // 设置缓存的移除通知
                    .removalListener(removalListener)
                    // build方法中可以指定CacheLoader，在缓存不存在时通过CacheLoader的实现自动加载缓存
                    .build();
        }
        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> localCacheData.invalidate(key)));
    }

    public void addListSync(String key, Object value) {
        synchronized (lock) {
            List<Object> gs = (List<Object>) localCacheData.getIfPresent(key);
            if (gs == null) {
                gs = new ArrayList<>();
            }
            gs.add(value);
            localCacheData.put(key, gs);

            // 如果队列长度超过设定最大长度则清除key
            if (gs.size() > maxItemSize) {
                localCacheData.invalidate(key);
            }
        }
    }

    public void addListSync(Object value) {
        addListSync(this.key, value);
    }

    @Override
    public void close() {
        localCacheData.invalidate(key);
    }


    public static void main(String[] args) {

    }
}
