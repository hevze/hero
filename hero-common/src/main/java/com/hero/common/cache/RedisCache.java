package com.hero.common.cache;

import org.springframework.cache.Cache;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;


/**
 * 
 * ClassName: RedisCache <br/>
 * Function: 根据SPring API 自定义一个缓存类 ，实现Redis 缓存。<br/>
 * date: 2014-7-28 上午11:10:52 <br/>
 * 
 * @author laich
 */

@Component("redisCache")
public class RedisCache implements Cache {
	/**缓存的命名属性**/
	private String name;

	public RedisClientTemplate cache = new RedisClientTemplate();

	/**
	 * 清空所有的缓存
	 */
	public void clear() {
//		cache.flushAll();
	}

	@Override
	public void evict(Object key) {
//		cache.del(key);
	}

	/**
	 * 根据Key值获得缓存数据
	 */
	public ValueWrapper get(Object key) {
		ValueWrapper result = null;
//		Object thevalue = cache.get(key);
//		if (thevalue != null) {
//			result = new SimpleValueWrapper(thevalue);
//		}
		return result;
	}

	@Override
	public <T> T get(Object o, Class<T> aClass) {
		return null;
	}

	@Override
	public <T> T get(Object o, Callable<T> callable) {
		return null;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Object getNativeCache() {
		return cache;
	}

	/**添加缓存*/
	public void put(Object arg0, Object arg1) {
//		cache.save(arg0, arg1,20000);
	}

	@Override
	public ValueWrapper putIfAbsent(Object o, Object o1) {
		return null;
	}

	public RedisCache() {
	}

	public RedisCache(String name) {
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
