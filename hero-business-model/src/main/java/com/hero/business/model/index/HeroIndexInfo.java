package com.hero.business.model.index;

import java.io.Serializable;
import java.util.List;

/**
 * 类说明 ：
 *
 * @ClassName HeroIndexInfo
 * @Author hwz.hs
 * @Date 2019/5/14 16:39
 * @Version v_1.0
 */
public class HeroIndexInfo implements Serializable{

    private List<HeroUserDynamic> carousels;

    private List<HeroIndexCategory> categories;

    public List<HeroUserDynamic> getCarousels() {
        return carousels;
    }

    public void setCarousels(List<HeroUserDynamic> carousels) {
        this.carousels = carousels;
    }

    public List<HeroIndexCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<HeroIndexCategory> categories) {
        this.categories = categories;
    }
}
