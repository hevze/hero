package com.hero.index.facade.service;

import com.hero.business.model.index.HeroIndexInfo;
import com.hero.business.model.index.HeroUserDynamic;

import java.util.List;


/**
 * 类说明 ：
 *
 * @ClassName HeroIndexFacade
 * @Author hwz.hs
 * @Date 2019/5/11 11:10
 * @Version v_1.0
 */
public interface HeroIndexFacade {

    /**
     * 首页
     * @return
     */
    public HeroIndexInfo index();

    /**
     * 获取类别文章
     * @param pageNumber
     * @param pageSize
     * @param categoryId
     * @return
     */
    public List<HeroUserDynamic> list_dynamics(int pageNumber, int pageSize, int categoryId,int userId);


    /**
     * 根据id查询文章
     * @param dynmicId
     * @return
     */
    public HeroUserDynamic one_dynamic(int dynmicId,int userId);
}
