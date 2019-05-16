package com.hero.index.facade.exception;

import com.hero.common.exceptions.BizException;

/**
 * 类说明 ：
 *
 * @ClassName MyExcetion
 * @Author hwz.hs
 * @Date 2019/5/16 11:22
 * @Version v_1.0
 */
public class HeroIndexExcetion extends BizException {

    private static final long serialVersionUID = -3175990411418914329L;


    /**
     * 我的异常
     */
    public static final int _INDEX_ERROR = 3001001;

    /**
     * 手机号不规范
     */
    public static final int _INDEX_ERROR_USER_TEL = 3001002;





    public HeroIndexExcetion() {
    }

    public HeroIndexExcetion(int code, String msgFormat, Object... args) {
        super(code, msgFormat, args);
    }

    public HeroIndexExcetion(int code, String msg) {
        super(code, msg);
    }


}
