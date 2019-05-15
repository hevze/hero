package com.hero.index.service.core.dao.impl;


import com.hero.business.model.index.HeroIndexCategory;
import com.hero.business.model.index.HeroUserDynamic;
import com.hero.common.entity.page.PageBean;
import com.hero.common.entity.page.PageParam;
import com.hero.core.dao.BaseDao;
import com.hero.core.dao.BaseDaoImpl;
import com.hero.index.service.core.dao.HeroUserDynamicMapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("heroUserDynamicMapper")
public class HeroUserDynamicMapperImpl extends BaseDaoImpl<HeroUserDynamic> implements HeroUserDynamicMapper{

    @Override
    public List<HeroUserDynamic> selectDynamics(int pageNumber, int pageSize, int categoryId){
        PageParam pageParam = new PageParam(pageNumber, pageSize);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("categoryId",categoryId);
        PageBean pageBean = super.listPage(pageParam, map,"selectDynamics");
        return pageBean.getRecordList(HeroUserDynamic.class);
    }


    @Override
    public List<HeroUserDynamic> selectAttentionDynamicsByUserId(int pageNumber, int pageSize, int userId) {
        PageParam pageParam = new PageParam(pageNumber, pageSize);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId",userId);
        PageBean pageBean = super.listPage(pageParam, map,"selectAttentionDynamicsByUserId");
        return pageBean.getRecordList(HeroUserDynamic.class);
    }

    @Override
    public List<HeroUserDynamic> selectDynamicsByCategory(int categoryId){
        return super.getSessionTemplate().selectList("selectDynamics",categoryId);
    }

    @Override
    public List<HeroUserDynamic> selectDynamicsByCategory(int categoryId,int dynamicType){
        Map<String,Object> map = new HashMap<>();
        map.put("categoryId",categoryId);
        map.put("dynamicType",dynamicType);
        return super.getSessionTemplate().selectList("selectDynamics",map);
    }


    @Override
    public List<HeroIndexCategory> selectCategorys(){
        return super.getSessionTemplate().selectList("selectCategorys");
    }


    @Override
    public Boolean selectDynamicUserIsAttention(int attentionId,int userId){
        Map<String,Object> map = new HashMap<>();
        map.put("attentionId",attentionId);
        map.put("userId",userId);
        return super.getSessionTemplate().selectOne("selectDynamicUserIsAttention",map);
    }

}