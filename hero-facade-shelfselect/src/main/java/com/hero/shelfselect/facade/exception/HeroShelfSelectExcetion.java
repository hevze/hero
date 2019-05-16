package com.hero.shelfselect.facade.exception;

import com.hero.common.exceptions.BizException;

/**
 * 类说明 ：
 *
 * @ClassName MyExcetion
 * @Author hwz.hs
 * @Date 2019/5/16 11:22
 * @Version v_1.0
 */
public class HeroShelfSelectExcetion extends BizException {

    private static final long serialVersionUID = -3175990411418914329L;


    /**
     * 自选异常
     */
    public static final int _SHELFSELECT_ERROR = 3004001;

    /**
     *
     */
    public static final int _SHELFSELECT_ERROR_USER_TEL = 3004002;



    public HeroShelfSelectExcetion() {
    }

    public HeroShelfSelectExcetion(int code, String msgFormat, Object... args) {
        super(code, msgFormat, args);
    }

    public HeroShelfSelectExcetion(int code, String msg) {
        super(code, msg);
    }

}
