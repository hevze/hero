package com.hero.my.service.core.biz;

import com.hero.business.model.my.HeroUser;
import com.hero.common.cache.RedisClientTemplate;
import com.hero.my.facade.constants.MyConstatns;
import com.hero.my.service.core.dao.HeroUserMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 类说明 ：验证
 *
 * @ClassName HeroUserException
 * @Author hwz.hs
 * @Date 2019/5/16 10:47
 * @Version v_1.0
 */
public class HeroUserCheckBiz {

    @Autowired
    HeroUserMapper heroUserMapper;

    @Autowired
    RedisClientTemplate redisClientTemplate;

    protected void _check_userRegister(HeroUser user){

        switch (user.getAccountType()){
            case MyConstatns.Key.LOGIN_MB:
                if(!isMobile(user.getLoginAccount())){

                }
                break;
            case MyConstatns.Key.LOGIN_WX:
                break;
            case MyConstatns.Key.LOGIN_QQ:
                break;
            case MyConstatns.Key.LOGIN_WB:
                break;
            default:break;
        }

    }


    /**
     * 验证手机号段是否正确
     * @param str
     * @return
     */
    public static boolean isMobile(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean isMatch = false;
        //制定验证条件
        String regex1 = "^[1][3,4,5,7,8][0-9]{9}$";
        String regex2 = "^((13[0-9])|(14[579])|(15([0-3,5-9]))|(16[6])|(17[0135678])|(18[0-9]|19[89]))\\d{8}$";

        p = Pattern.compile(regex2);
        m = p.matcher(str);
        isMatch = m.matches();
        return isMatch;
    }



}
