package com.hero.common.cache.guava;

import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalListeners;
import com.google.common.cache.RemovalNotification;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * 默认缓存策略
 */
public class DefaultGuavaCacheManage {

    public static final Logger LOGGER = LoggerFactory.getLogger(DefaultGuavaCacheManage.class);

    /**
     * 缓存包装
     */
    private static AbstractGuavaCache<String, Object> wrapper;

    /**
     * 〈一句话功能简述〉<br>
     * 〈功能详细描述〉初始化缓存容器
     *
     * @return
     * @Param
     * @see [相关类/方法]（可选）
     * @since [产品/模块版本] （可选）
     **/
    @SuppressWarnings("unchecked")
    public static boolean initGuaveCache() {
        wrapper = DefaultGuavaCache.getInstance();
        if (wrapper != null) {
            return true;
        } else {
            LOGGER.error("Fail to init Guava Cache !");
            return false;
        }
    }

    /**
     * 〈一句话功能简述〉<br>
     * 〈功能详细描述〉通过key获取缓存值
     *
     * @return
     * @Param key 缓存key
     * @see [相关类/方法]（可选）
     * @since [产品/模块版本] （可选）
     **/
    public static Object get(String key) {
        Object object = null;
        try {
            object = wrapper.getCache().get(key);
        } catch (ExecutionException e) {
            LOGGER.error("get Guava cache exception");
        }
        return object;
    }

    /**
     * 〈一句话功能简述〉<br>
     * 〈功能详细描述〉通过key获取缓存值,如果没有采取一定措施
     *
     * @return callable 回调函数
     * @Param key 缓存的key
     * @see [相关类/方法]（可选）
     * @since [产品/模块版本] （可选）
     **/
    public static Object getOrDo(String key, Callable callable) {
        Object object = null;
        try {
            wrapper.getCache().get(key, callable);
        } catch (ExecutionException e) {
            LOGGER.error("getOrDo Guava cache exception");
        }
        return object;
    }

    /**
     * 〈一句话功能简述〉<br>
     * 〈功能详细描述〉清除指定key缓存
     *
     * @return
     * @Param key 缓存的key
     * @see [相关类/方法]（可选）
     * @since [产品/模块版本] （可选）
     **/
    public static void invalidate(String key) {
        wrapper.getCache().invalidate(key);
    }

    /**
     * 〈一句话功能简述〉<br>
     * 〈功能详细描述〉清除所有缓存
     *
     * @return
     * @Param
     * @see [相关类/方法]（可选）
     * @since [产品/模块版本] （可选）
     **/
    public static void inalidateAll() {
        wrapper.getCache().invalidateAll();
    }

    /**
     * 静态内部类实现单例
     *
     */
    static class DefaultGuavaCache extends AbstractGuavaCache<String, Object> implements RemovalListener<String, Object> {

        private static AbstractGuavaCache guavaCache = new DefaultGuavaCache(
                1,
                10 * 1000,
                100,
                10 * 1000);

        public static AbstractGuavaCache getInstance() {
            return guavaCache;
        }


        private DefaultGuavaCache(int concurrencyLevel, long expireTime, int initialCapacity, long maxSize) {
            super(concurrencyLevel, expireTime, initialCapacity, maxSize);
            ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("cache-thread-%d").build();
            ExecutorService pool = new ThreadPoolExecutor(4, 10, 0L,
                    TimeUnit.MILLISECONDS,
                    new LinkedBlockingDeque<>(256),
                    namedThreadFactory,
                    new ThreadPoolExecutor.AbortPolicy());
            // 异步监听
            RemovalListener<String, Object> async = RemovalListeners.asynchronous(this, pool);
            setRemovalListener(async);
        }

        @Override
        protected Object fetchData(String s) {
            //具体操作
            return null;
        }

        @Override
        public void onRemoval(RemovalNotification<String, Object> removalNotification) {

        }
    }
}
