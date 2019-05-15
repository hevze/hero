package com.hero.index.service.core.dao;


import com.hero.business.model.index.HeroIndexCategory;
import com.hero.business.model.index.HeroUserDynamic;
import com.hero.core.dao.BaseDao;

import java.util.List;

public interface HeroUserDynamicMapper extends BaseDao<HeroUserDynamic>{

    /**
     * 文章获取
     * @param pageNumber
     * @param pageSize
     * @param categoryId  分类id
     * @return
     */
    List<HeroUserDynamic> selectDynamics(int pageNumber, int pageSize, int categoryId);

    /**
     * 查询当前用户所关注的用户发的文章，评论，转发.
     * @param pageNumber
     * @param pageSize
     * @param userId
     * @return
     */
    List<HeroUserDynamic> selectAttentionDynamicsByUserId(int pageNumber, int pageSize, int userId);

    /**
     * 根据分类获取文章
     * @param categoryId
     * @return
     */
    List<HeroUserDynamic> selectDynamicsByCategory(int categoryId);

    List<HeroUserDynamic> selectDynamicsByCategory(int categoryId, int dynamicType);

    /**
     * 查询首页文章分类
     * @return
     */
    List<HeroIndexCategory> selectCategorys();

    Boolean selectDynamicUserIsAttention(int attentionId, int userId);
}