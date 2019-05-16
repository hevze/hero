package com.hero.my.facade.exception;

import com.hero.common.exceptions.BizException;

/**
 * 类说明 ：
 *
 * @ClassName MyExcetion
 * @Author hwz.hs
 * @Date 2019/5/16 11:22
 * @Version v_1.0
 */
public class HeroMyExcetion extends BizException {

    private static final long serialVersionUID = -3175990411418914329L;


    /**
     * 我的异常
     */
    public static final int MY_ERROR = 3003001;

    /**
     * 手机号不规范
     */
    public static final int MY_ERROR_USER_TEL = 3003002;



}
