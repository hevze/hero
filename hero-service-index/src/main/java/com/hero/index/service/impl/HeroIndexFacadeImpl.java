package com.hero.index.service.impl;

import com.hero.business.model.index.HeroIndexInfo;
import com.hero.business.model.index.HeroUserDynamic;
import com.hero.index.facade.service.HeroIndexFacade;
import com.hero.index.service.core.biz.HeroIndexBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 类说明 ：
 *
 * @ClassName HeroIndexFacadeImpl
 * @Author hwz.hs
 * @Date 2019/5/11 11:10
 * @Version v_1.0
 */
@Component("heroIndexFacade")
public class HeroIndexFacadeImpl implements HeroIndexFacade{
    @Autowired
    HeroIndexBiz heroIndexBiz;


    /**
     * 首页
     * @return
     */
    public HeroIndexInfo index(){
        return heroIndexBiz.index();
    }

    /**
     * 获取类别文章
     * @param pageNumber
     * @param pageSize
     * @param categoryId
     * @return
     */
    public List<HeroUserDynamic> list_dynamics(int pageNumber, int pageSize, int categoryId,int userId){
        return heroIndexBiz.list_dynamics(pageNumber,pageSize,categoryId,userId);
    }


    /**
     * 根据id查询文章
     * @param dynmicId
     * @return
     */
    public HeroUserDynamic one_dynamic(int dynmicId,int userId){
        return heroIndexBiz.one_dynamic(dynmicId,userId);
    }
}
