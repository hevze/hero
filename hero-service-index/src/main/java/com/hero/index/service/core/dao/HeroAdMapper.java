package com.hero.index.service.core.dao;


import com.hero.business.model.index.HeroUserDynamic;
import com.hero.core.dao.BaseDao;

import java.util.List;

public interface HeroAdMapper extends BaseDao<HeroUserDynamic>{

    public List<HeroUserDynamic> selectAds(int adType);
}