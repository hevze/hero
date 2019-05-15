package com.hero.index.service.core.dao.impl;


import com.hero.business.model.index.HeroUserDynamic;
import com.hero.core.dao.BaseDaoImpl;
import com.hero.index.service.core.dao.HeroAdMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("heroAdMapper")
public class HeroAdMapperImpl extends BaseDaoImpl<HeroUserDynamic> implements HeroAdMapper{

    @Override
    public List<HeroUserDynamic> selectAds(int adType) {
        return super.getSessionTemplate().selectList("selectAds",adType);
    }
}