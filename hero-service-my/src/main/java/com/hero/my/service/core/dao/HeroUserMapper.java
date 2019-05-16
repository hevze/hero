package com.hero.my.service.core.dao;


import com.hero.business.model.my.HeroUser;
import com.hero.business.model.my.HeroUserExt;
import com.hero.core.dao.BaseDao;

public interface HeroUserMapper extends BaseDao<HeroUser> {

    HeroUserExt selectUserByWhere(HeroUser user);
}