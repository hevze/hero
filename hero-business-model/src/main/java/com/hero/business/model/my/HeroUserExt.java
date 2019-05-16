package com.hero.business.model.my;

import java.util.Date;

/**
 * 类说明 ：
 *
 * @ClassName HeroUserExt
 * @Author hwz.hs
 * @Date 2019/5/15 16:29
 * @Version v_1.0
 */
public class HeroUserExt extends HeroUser {

    private String bindTel;

    private String brief;

    private int sex;

    private String userCard;

    private String userCardName;

    private Date userBirthday;

    public String getBindTel() {
        return bindTel;
    }

    public void setBindTel(String bindTel) {
        this.bindTel = bindTel;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getUserCard() {
        return userCard;
    }

    public void setUserCard(String userCard) {
        this.userCard = userCard;
    }

    public String getUserCardName() {
        return userCardName;
    }

    public void setUserCardName(String userCardName) {
        this.userCardName = userCardName;
    }

    public Date getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }
}
