package com.hero.core.service;

import java.util.List;
import java.util.Map;

import com.hero.common.cache.RedisClientTemplate;
import com.hero.common.entity.base.BaseEntity;
import com.hero.common.entity.page.PageBean;
import com.hero.common.entity.page.PageParam;
import com.hero.common.utils.str.FastJsonUtils;
import com.hero.core.dao.BaseDao;


/**
 * Service 基类实现
 *
 * @param <T>
 * @author Hill
 */
public abstract class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> {

    protected abstract BaseDao<T> getDao();

    protected RedisClientTemplate getRedisClientTemplate() {
        return null;
    }

    /**
     * 获取redis缓存对象数据
     *
     * @param key
     * @param t
     * @param <T>
     * @return
     */
    protected <T> T getRedisCacheBean(String key, Class<T> t) {
        if (getRedisClientTemplate() == null) {
            throw new NullPointerException("子类需实现getRedisClientTemplate()方法，并反正redis操作对象");
        }
        try {
            return FastJsonUtils.fromJson(getRedisClientTemplate().get(key), t);
        } catch (Exception e) {
            throw new IllegalArgumentException("redis缓存下的该key值数据非jsonObject格式");
        }
    }

    public T getById(long id) {
        return this.getDao().getById(id);
    }

    /**
     * 分页查询 .
     *
     * @param pageParam 分页参数.
     * @param paramMap  业务条件查询参数.
     * @return
     */
    public PageBean listPage(PageParam pageParam, Map<String, Object> paramMap) {
        return this.getDao().listPage(pageParam, paramMap);
    }

    public PageBean listPage(PageParam pageParam, Map<String, Object> paramMap, String sqlId) {
        return this.getDao().listPage(pageParam, paramMap, sqlId);
    }

    /**
     * 根据条件查询 listBy: <br/>
     *
     * @param paramMap
     * @return 返回集合
     */
    public List<T> listBy(Map<String, Object> paramMap) {
        return this.getDao().listBy(paramMap);
    }

    public List<Object> listBy(Map<String, Object> paramMap, String sqlId) {
        return this.getDao().listBy(paramMap, sqlId);
    }

    /**
     * 根据条件查询 listBy: <br/>
     *
     * @param paramMap
     * @return 返回实体
     */
    public T getBy(Map<String, Object> paramMap) {
        return this.getDao().getBy(paramMap);
    }

    public Object getBy(Map<String, Object> paramMap, String sqlId) {
        return this.getDao().getBy(paramMap, sqlId);
    }

    /**
     * 根据序列名称获取下一个值
     *
     * @return
     */
    public String getSeqNextValue(String seqName) {
        return this.getDao().getSeqNextValue(seqName);
    }

}
