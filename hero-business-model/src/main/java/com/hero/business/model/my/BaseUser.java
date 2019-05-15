package com.hero.business.model.my;

import com.hero.common.entity.base.BaseEntity;

/**
 * 类说明 ：
 *
 * @ClassName BaseHeroUser
 * @Author hwz.hs
 * @Date 2019/5/15 9:47
 * @Version v_1.0
 */
public class BaseUser extends BaseEntity{

    private String userName;

    private String userHeaderImg;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserHeaderImg() {
        return userHeaderImg;
    }

    public void setUserHeaderImg(String userHeaderImg) {
        this.userHeaderImg = userHeaderImg;
    }
}
