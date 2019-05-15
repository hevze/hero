package com.hero.common.cache.guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public abstract class AbstractGuavaCache<K, V> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractGuavaCache.class);

    /**
     * loadingCache 核心实例
     */
    private volatile LoadingCache<K, V> loadingCache;

    /**
     * 并发级别
     */
    private int concurrencyLevel;

    /**
     * 缓存超时时间
     */
    private long expireTime;

    /**
     * 初始容量
     */
    private int initialCapacity;

    /**
     * 最大容量
     */
    private long maxSize;
    /**
     * 设置缓存的移除通知
     */
    private RemovalListener<K, V> removalListener;

    public void setRemovalListener(RemovalListener<K, V> removalListener) {
        this.removalListener = removalListener;
    }

    protected AbstractGuavaCache(int concurrencyLevel, long expireTime, int initialCapacity, long maxSize) {
        this.concurrencyLevel = concurrencyLevel < 1 ? 1 : concurrencyLevel;
        this.expireTime = expireTime;
        this.initialCapacity = initialCapacity < 100 ? 100 : initialCapacity;
        this.maxSize = maxSize;
    }

    /**
     * 〈一句话功能简述〉<br>
     * 〈功能详细描述〉
     *
     * @return
     * @Param
     * @see [相关类/方法]（可选）
     * @since [产品/模块版本] （可选）
     **/
    public LoadingCache<K, V> getCache() {
        // 双重检查锁-防止重复加载
        if (loadingCache == null) {
            synchronized (this) {
                if (loadingCache == null) {
                    loadingCache = CacheBuilder.newBuilder()
                            // 设置并发级别为8
                            .concurrencyLevel(concurrencyLevel)
                            // 设置写缓存后expireTime秒钟过期
                            .expireAfterWrite(3, TimeUnit.SECONDS)
                            // 设置多久不读缓存废弃
                            .expireAfterAccess(expireTime, TimeUnit.SECONDS)
                            // 设置初始条数
                            .initialCapacity(initialCapacity)
                            // 设置最大条数,超过则按照LRU策略淘汰缓存
                            .maximumSize(Integer.MAX_VALUE)
                            // 设置要统计缓存的命中率
                            .recordStats()
                            // 设置缓存的移除通知
                            .removalListener(removalListener == null ? (RemovalListener<K, V>) notification -> {
                                if (LOGGER.isDebugEnabled()) {
                                    LOGGER.debug("{} was moved,cause is {}", notification.getKey(),
                                            notification.getCause());
                                }
                            } : removalListener)
                            .build(
                                    // 设置缓存不存在时,自动加载入缓存
                                    new CacheLoader<K, V>() {
                                        @Override
                                        public V load(K k) {
                                            try {
                                                return fetchData(k);
                                            }catch (Exception e){
                                                return null;
                                            }
                                        }
                                    });
                }
            }
            if (LOGGER.isDebugEnabled()) {
                LOGGER.info("本地缓存{}初始化成功", this.getClass().getSimpleName());
            }
        }
        return loadingCache;
    }

    /**
     * 〈一句话功能简述〉<br>
     * 〈功能详细描述〉载入数据
     *
     * @return
     * @Param
     * @see [相关类/方法]（可选）
     * @since [产品/模块版本] （可选）
     **/
    protected abstract V fetchData(K k);

    public int getConcurrencyLevel() {
        return concurrencyLevel;
    }

    public void setConcurrencyLevel(int concurrencyLevel) {
        this.concurrencyLevel = concurrencyLevel;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }

    public int getInitialCapacity() {
        return initialCapacity;
    }

    public void setInitialCapacity(int initialCapacity) {
        this.initialCapacity = initialCapacity;
    }

    public long getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(long maxSize) {
        this.maxSize = maxSize;
    }
}
