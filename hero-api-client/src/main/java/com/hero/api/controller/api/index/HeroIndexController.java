package com.hero.api.controller.api.index;

import com.hero.api.BaseRespose;
import com.hero.business.model.index.HeroIndexInfo;
import com.hero.business.model.index.HeroUserDynamic;
import com.hero.index.facade.service.HeroIndexFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 类说明 ：
 *
 * @ClassName HeroIndexController
 * @Author hwz.hs
 * @Date 2019/5/11 11:42
 * @Version v_1.0
 */
@RestController
@RequestMapping("api/index")
@EnableAutoConfiguration
public class HeroIndexController {

    @Autowired
    HeroIndexFacade heroIndexFacade;


    /**
     * 首页API
     * @return
     */
    @RequestMapping(value = "", method = {RequestMethod.GET})
    @ResponseBody
    public BaseRespose<HeroIndexInfo> index() {
        try {
            HeroIndexInfo indexs = heroIndexFacade.index();
            return new BaseRespose<>(indexs, "获取首页信息");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



    /**
     * 获取分类文章
     * @return
     */
    @RequestMapping(value = "list_dynamic", method = {RequestMethod.GET})
    @ResponseBody
    public BaseRespose<List<HeroUserDynamic>> list_dynamic(@RequestParam("pageNumber") int pageNumber,@RequestParam("pageSize") int pageSize,@RequestParam("categoryId") int categoryId) {
        try {
            int userId = 4;
            List<HeroUserDynamic> dynamics = heroIndexFacade.list_dynamics(pageNumber,pageSize,categoryId,userId);
            return new BaseRespose<>(dynamics, "获取实盘账户成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 根据id获取文章信息
     * @return
     */
    @RequestMapping(value = "one_dynamic", method = {RequestMethod.GET})
    @ResponseBody
    public BaseRespose<HeroUserDynamic> one_dynamic(@RequestParam("dynamicId") int dynamicId) {
        try {
            int userId = 4;
            HeroUserDynamic resultDynamic = heroIndexFacade.one_dynamic(dynamicId,userId);
            return new BaseRespose<>(resultDynamic, "获取实盘账户成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
