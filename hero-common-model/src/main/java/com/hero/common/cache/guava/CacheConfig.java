//package net.huaer.common.cache.guava;
//
//import com.google.common.cache.RemovalCause;
//import com.google.common.cache.RemovalListener;
//import com.google.common.cache.RemovalListeners;
//import com.google.common.cache.RemovalNotification;
//import com.google.common.util.concurrent.ThreadFactoryBuilder;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//
//import java.util.List;
//import java.util.concurrent.*;
//
//@Configuration
//@PropertySource(value = "bufferCache.properties", ignoreResourceNotFound = true)
//public class CacheConfig<T> implements ApplicationContextAware {
//    private ApplicationContext ctx;
//
//    @Bean("buffCache")
////    @ConditionalOnProperty(prefix = "cache", value = "currencyLevel")
//    public BufferCache guildBuffCache(@Value("${cache.key}") String key,
//                                      @Value("${cache.currencyLevel}") int currencyLevel,
//                                      @Value("${cache.writeExpireTime}") int writeExpireTime,
//                                      @Value("${cache.accessExpireTime}") int accessExpireTime,
//                                      @Value("${cache.initialCapacity}") int initialCapacity,
//                                      @Value("${cache.maximumSize}") int maximumSize,
//                                      @Value("${cache.maxItemSize}") int maxItemSize) {
//        //使用 guava 开源框架的 ThreadFactoryBuilder 给线程池的线程设置名字
//        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("cache-thread-%d").build();
//        ExecutorService pool = new ThreadPoolExecutor(4, 10, 0L,
//                TimeUnit.MILLISECONDS,
//                new LinkedBlockingDeque<>(256),
//                namedThreadFactory,
//                new ThreadPoolExecutor.AbortPolicy());
//        // 异步监听
//        RemovalListener<String, List<T>> async = RemovalListeners
//                .asynchronous(new MyRemovalListener(), pool
//                        /*ExecutorServiceUtil.getExecutorServiceByType(ExecutorServiceUtil.ExecutorServiceType.BACKGROUND)*/);
//        return new BufferCache(key, currencyLevel, writeExpireTime,
//                accessExpireTime, initialCapacity, maximumSize, maxItemSize,
//                async);
//    }
//
//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext)
//            throws BeansException {
//        ctx = applicationContext;
//    }
//
//    // 创建一个监听器
//    private class MyRemovalListener implements RemovalListener<String, List<T>> {
//        @Override
//        public void onRemoval(RemovalNotification<String, List<T>> notification) {
//            RemovalCause cause = notification.getCause();
//
//            // 当超出缓存队列限制大小时或者key过期或者主动清除key时更新数据
//            if (cause.equals(RemovalCause.SIZE)
//                    || cause.equals(RemovalCause.EXPIRED)
//                    || cause.equals(RemovalCause.EXPLICIT)) {
//                //根据不同业务场景调用不同业务方法进行写入操作
//            }
//        }
//    }
//}