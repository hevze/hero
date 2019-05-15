package com.hero.common.cache;

import com.hero.common.utils.serialize.SerializeUtils;
import com.hero.common.utils.str.FastJsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.*;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

//@Repository("redisClientTemplate")
public class RedisClientTemplate {

    private static final Logger log = LoggerFactory.getLogger(RedisClientTemplate.class);


    @Autowired
    private ShardedJedisPool shardedJedisPool;


    public synchronized ShardedJedis getRedisClient() {
        try {
            ShardedJedis shardJedis = shardedJedisPool.getResource();
            return shardJedis;
        } catch (Exception e) {
            log.error("getRedisClent error", e);
        }
        return null;
    }


    /**
     * 发布
     *
     * @param channel
     * @param value
     * @return
     */
    public Long publish(String channel, String value) {
        Long result = null;
        ShardedJedis shardedJedis = getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        Jedis jedis = null;
        try {
            Jedis[] jedisArray = new Jedis[]{};
            jedisArray = shardedJedis.getAllShards().toArray(jedisArray);
            jedis = jedisArray[0];
            result = jedis.publish(channel, value);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            jedis.close();
            returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 获取单个值
     *
     * @param key
     * @return
     */
    public String get(String key) {
        String result = null;
        ShardedJedis shardedJedis = getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        boolean broken = false;
        try {
            result = shardedJedis.get(key);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            returnResource(shardedJedis, broken);
        }
        return result;
    }


    public String get(String key, ShardedJedis shardedJedis, boolean isReturn) {
        String result = null;
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.get(key);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            if (isReturn) {
                returnResource(shardedJedis, broken);
            }
        }
        return result;
    }

    public String set(String key, Object value, ShardedJedis shardedJedis, boolean isReturn) {
        String result = null;
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.set(key.getBytes(), SerializeUtils.serialize(value));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            if (isReturn) {
                returnResource(shardedJedis, broken);
            }
        }
        return result;
    }

    /**
     * @param key
     * @param field
     * @param value
     * @return
     */
    public Long hset(String key, String field, String value) {
        Long result = null;
        ShardedJedis shardedJedis = getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.hset(key, field, value);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            returnResource(shardedJedis, broken);
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public <T> T hget(String key, String field, Class<T> clazz) {
        ShardedJedis shardedJedis = getRedisClient();
        if (shardedJedis == null) {
            return null;
        }
        String value = null;
        boolean broken = false;
        try {
            value = shardedJedis.hget(key, field);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            returnResource(shardedJedis, broken);
        }
        if (value == null) {
            return null;
        }
        if (clazz == String.class) {
            return (T) value;
        }
        return FastJsonUtils.fromJson(value, clazz);
    }

    /**
     * 获取set的所有数据
     *
     * @param key
     * @return
     */
    public Map<String, String> hgetAll(String key) {
        ShardedJedis shardedJedis = getRedisClient();
        if (shardedJedis == null) {
            return null;
        }
        Map<String, String> value = null;
        boolean broken = false;
        try {
            value = shardedJedis.hgetAll(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            returnResource(shardedJedis, broken);
        }
        if (value == null) {
            return null;
        }
        return value;
    }

    public Set<String> hkeys(String key){
        ShardedJedis shardedJedis = getRedisClient();
        if (shardedJedis == null) {
            return null;
        }
        Set<String> value = null;
        boolean broken = false;
        try {
            value = shardedJedis.hkeys(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            returnResource(shardedJedis, broken);
        }
        if (value == null) {
            return null;
        }
        return value;
    }

    /**
     * 设置单个值
     *
     * @param key
     * @param value
     * @return
     */
    public String set(String key, Object value) {

        String result = null;


        ShardedJedis shardedJedis = getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.set(key, value.toString());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            returnResource(shardedJedis, broken);
        }
        return result;
    }

    public String pipelineSet(String key, Object value) {
        Response<String> result = null;

        ShardedJedis shardedJedis = getRedisClient();
        if (shardedJedis == null) {
            return result.get();
        }
        boolean broken = false;
        try {
            ShardedJedisPipeline pipe = shardedJedis.pipelined();
            result = pipe.set(key, value.toString());
            pipe.sync();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            returnResource(shardedJedis, broken);
        }
        return result.get();
    }

    public String pipelineGet(String key) {
        Response<String> result = null;

        ShardedJedis shardedJedis = getRedisClient();
        if (shardedJedis == null) {
            return result.get();
        }
        boolean broken = false;
        try {
            ShardedJedisPipeline pipe = shardedJedis.pipelined();
            result = pipe.get(key);
            pipe.sync();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            returnResource(shardedJedis, broken);
        }
        return result.get();
    }


    /**
     * 把一个或多个元素添加到指定集合
     *
     * @param key
     * @param members
     * @return
     */
    public Long append(String key, String members) {
        Long result = null;
        ShardedJedis shardedJedis = getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.append(key, members);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            returnResource(shardedJedis, broken);
        }
        return result;
    }

    public String setsx(String key, String value, Integer seconds) {
        String result = null;

        ShardedJedis shardedJedis = getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.setex(key, seconds, value);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            returnResource(shardedJedis, broken);
        }
        return result;
    }

    public String setsx(String key, String value, Integer seconds, ShardedJedis shardedJedis, boolean isReturn) {
        String result = null;
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.setex(key, seconds, value);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            if (isReturn) {
                returnResource(shardedJedis, broken);
            }
        }
        return result;
    }


    public void returnResource(ShardedJedis shardedJedis) {
        shardedJedisPool.returnResource(shardedJedis);
    }

    public void returnResource(ShardedJedis shardedJedis, boolean broken) {
        if (broken) {
            shardedJedisPool.returnBrokenResource(shardedJedis);
        } else {
            shardedJedisPool.returnResource(shardedJedis);
        }
    }


    public void disconnect() {
        ShardedJedis shardedJedis = getRedisClient();
        shardedJedis.disconnect();
    }


    public Boolean exists(String key) {
        Boolean result = false;
        ShardedJedis shardedJedis = getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.exists(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            returnResource(shardedJedis, broken);
        }
        return result;
    }

    public String type(String key) {
        String result = null;
        ShardedJedis shardedJedis = getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.type(key);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 在某段时间后失效
     *
     * @param key
     * @param seconds
     * @return
     */
    public Long expire(String key, int seconds) {
        Long result = null;
        ShardedJedis shardedJedis = getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.expire(key, seconds);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 在某个时间点失效
     *
     * @param key
     * @param unixTime
     * @return
     */
    public Long expireAt(String key, long unixTime) {
        Long result = null;
        ShardedJedis shardedJedis = getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.expireAt(key, unixTime);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            returnResource(shardedJedis, broken);
        }
        return result;
    }

    public Long del(String key) {
        Long result = null;
        ShardedJedis shardedJedis = getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.del(key);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            returnResource(shardedJedis, broken);
        }
        return result;
    }

    public Long hdel(String key, String field) {
        Long result = null;
        ShardedJedis shardedJedis = getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.hdel(key, field);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * 设置 map
     *
     * @param key
     */
    public <T> void setMap(String key, Map<String, T> map) {
        try {
            ShardedJedis shardedJedis = getRedisClient();
            boolean broken = false;
            try {
                shardedJedis.set(key.getBytes(), ObjectTranscoder.serialize(map));
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                broken = true;
            } finally {
                returnResource(shardedJedis, broken);
            }
        } catch (Exception e) {
            log.error("Set key error : " + e);
        }
    }

    /**
     * 获取map
     *
     * @param key
     * @return list
     */
    public <T> Map<String, T> getMap(String key) {
        ShardedJedis shardedJedis = getRedisClient();
        if (shardedJedis == null || !shardedJedis.exists(key.getBytes())) {
            return null;
        }
        boolean broken = false;
        Map<String, T> map = null;
        try {
            byte[] in = shardedJedis.get(key.getBytes());
            map = (Map<String, T>) ObjectTranscoder.deserialize(in);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            returnResource(shardedJedis, broken);
        }
        return map;
    }


    /**
     * 添加队列的值
     *
     * @param key
     * @param value
     * @return
     */
    public String lpush(String key, Object value) {
        String result = null;

        ShardedJedis shardedJedis = getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            Long size = shardedJedis.lpush(key, value.toString());
            System.out.println(key + ": 当前未被处理消息条数为:" + size);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            returnResource(shardedJedis, broken);
        }
        return result;
    }


    /**
     * 获取队列
     *
     * @param key
     * @return
     */
    public String rpop(String key) {
        String result = null;

        ShardedJedis shardedJedis = getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.rpop(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            returnResource(shardedJedis, broken);
        }
        return result;
    }

    /**
     * Redis Zrevrangebyscore 返回有序集中指定分数区间内的所有的成员。有序集成员按分数值递减(从大到小)的次序排列。
     * 具有相同分数值的成员按字典序的逆序(reverse lexicographical order )排列。
     * 除了成员按分数值递减的次序排列这一点外， ZREVRANGEBYSCORE 命令的其他方面和 ZRANGEBYSCORE 命令一样。
     *
     * @param key
     * @param max
     * @param min
     * @param offset
     * @param count
     * @return 指定区间内，带有分数值(可选)的有序集成员的列表。
     */
    public LinkedHashSet<String> zrevrangebyscore(String key, String max, String min, int offset, int count) {
        LinkedHashSet<String> result = null;
        ShardedJedis shardedJedis = getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = (LinkedHashSet<String>) shardedJedis.zrevrangeByScore(key, max, min, offset, count);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            returnResource(shardedJedis, broken);
        }
        return result;
    }


    /**
     * Redis Hmget 命令用于返回哈希表中，一个或多个给定字段的值。
     * 如果指定的字段不存在于哈希表，那么返回一个 nil 值。
     *
     * @param key
     * @param item
     * @return 一个包含多个给定字段关联值的表，表值的排列顺序和指定字段的请求顺序一样。
     */
    public List<String> hmget(String key, String... item) {
        List<String> result = null;
        ShardedJedis shardedJedis = getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.hmget(key, item);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            returnResource(shardedJedis, broken);
        }
        return result;
    }


    /**
     * * Redis Zadd 命令用于将一个或多个成员元素及其分数值加入到有序集当中。
     * 如果某个成员已经是有序集的成员，那么更新这个成员的分数值，并通过重新插入这个成员元素，来保证该成员在正确的位置上。
     * 分数值可以是整数值或双精度浮点数。
     * 如果有序集合 key 不存在，则创建一个空的有序集并执行 ZADD 操作。
     * 当 key 存在但不是有序集类型时，返回一个错误。
     *
     * @param key
     * @param score
     * @param member
     * @return  被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员。
     */
    public Long zadd(String key, double score, String member) {
        Long result = null;
        ShardedJedis shardedJedis = getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.zadd(key, score, member);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            returnResource(shardedJedis, broken);
        }
        return result;
    }

}
