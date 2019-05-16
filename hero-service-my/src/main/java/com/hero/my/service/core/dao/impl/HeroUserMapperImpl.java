package com.hero.my.service.core.dao.impl;

import com.hero.business.model.my.HeroUser;
import com.hero.business.model.my.HeroUserExt;
import com.hero.core.dao.BaseDaoImpl;
import com.hero.my.service.core.dao.HeroUserMapper;
import org.springframework.stereotype.Repository;

/**
 * 类说明 ：
 *
 * @ClassName HeroUserMapperImpl
 * @Author hwz.hs
 * @Date 2019/5/15 15:36
 * @Version v_1.0
 */
@Repository("heroUserMapper")
public class HeroUserMapperImpl extends BaseDaoImpl<HeroUser> implements HeroUserMapper {


    @Override
    public HeroUserExt selectUserByWhere(HeroUser user){
        return super.getSessionTemplate().selectOne("selectUserByWhere",user);
    }
}
