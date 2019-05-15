package com.hero.business.model.index;

import com.hero.common.entity.base.BaseIdEntity;

/**
 * 类说明 ：
 *
 * @ClassName HeroIndexCategory
 * @Author hwz.hs
 * @Date 2019/5/14 16:43
 * @Version v_1.0
 */
public class HeroIndexCategory extends BaseIdEntity{

    private String categoryCode;
    private String categoryName;

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
