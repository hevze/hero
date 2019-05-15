package com.hero.index.service.core.biz;

import com.hero.business.model.index.HeroIndexCategory;
import com.hero.business.model.index.HeroIndexInfo;
import com.hero.business.model.index.HeroUserDynamic;
import com.hero.common.utils.str.ObjectAssert;
import com.hero.index.service.core.dao.HeroAdMapper;
import com.hero.index.service.core.dao.HeroUserDynamicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 类说明 ：
 *
 * @ClassName HeroIndexBIz
 * @Author hwz.hs
 * @Date 2019/5/11 11:04
 * @Version v_1.0
 */
@Repository("heroIndexBiz")
public class HeroIndexBiz  {

    @Autowired
    HeroAdMapper heroAdMapper;
    @Autowired
    HeroUserDynamicMapper heroUserDynamicMapper;
    Random random = new Random();
    /**
     * 首页
     * @return
     */
    public HeroIndexInfo index(){
        HeroIndexInfo indexInfo = new HeroIndexInfo();
        // 1.查询轮播:轮播1  文章列广告 2
        List<HeroUserDynamic> carousels = heroAdMapper.selectAds(1);
        indexInfo.setCarousels(carousels);
        // 2. 查询分类
        List<HeroIndexCategory> categories = heroUserDynamicMapper.selectCategorys();
        indexInfo.setCategories(categories);
        return indexInfo;
    }



    /**
     * 获取类别文章
     * @param pageNumber
     * @param pageSize
     * @param categoryId
     * @return
     */
    public List<HeroUserDynamic> list_dynamics(int pageNumber,int pageSize,int categoryId,int userId){
        List<HeroUserDynamic> dynamics = new ArrayList<>();
        if(categoryId == 1){
            // 查询用户关注
           dynamics =  heroUserDynamicMapper.selectAttentionDynamicsByUserId(pageNumber,pageSize,userId);
        }else{
            List<HeroUserDynamic> carousels = heroAdMapper.selectAds(1);
            dynamics = heroUserDynamicMapper.selectDynamics(pageNumber,pageSize,categoryId);
            if(dynamics.size() > 0){
                HeroUserDynamic dynamic = carousels.get(random.nextInt(carousels.size() -1 ));
                dynamic.setDynamicType(0);
                dynamics.add(1,dynamic);
            }
        }
        // 查询所有分类文章
        for(HeroUserDynamic dynamic:dynamics){
            if(null != dynamic.getParentId() && dynamic.getParentId() > 0){
                HeroUserDynamic forwardInfo = heroUserDynamicMapper.getById(dynamic.getParentId());
                if(ObjectAssert.isNotEmpty(forwardInfo)) {
                    dynamic.setForwardInfo(forwardInfo);
                }
            }
        }
        return dynamics;
    }


    /**
     * 根据id查询文章
     * @param dynmicId
     * @param userId  根据用户id查询当前文章用户是已被关注
     * @return
     */
    public HeroUserDynamic one_dynamic(int dynmicId,int userId){
        HeroUserDynamic dynamic = heroUserDynamicMapper.getById(dynmicId);
        dynamic.setAttention(heroUserDynamicMapper.selectDynamicUserIsAttention(dynamic.getCreateBy(),userId));
        if(dynamic.getParentId() > 0){
            HeroUserDynamic forwardInfo = heroUserDynamicMapper.getById(dynamic.getParentId());
            if(ObjectAssert.isNotEmpty(forwardInfo)) {
                dynamic.setForwardInfo(forwardInfo);
            }
        }
        return dynamic;
    }

}
