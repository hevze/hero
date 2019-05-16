package com.hero.trade.facade.exception;

import com.hero.common.exceptions.BizException;

/**
 * 类说明 ：
 *
 * @ClassName MyExcetion
 * @Author hwz.hs
 * @Date 2019/5/16 11:22
 * @Version v_1.0
 */
public class HeroTradeExcetion extends BizException {

    private static final long serialVersionUID = -3175990411418914329L;


    /**
     * 交易异常
     */
    public static final int _TRADE_ERROR = 3005001;

    /**
     *
     */
    public static final int _TRADE_ERROR_USER_TEL = 3005002;




    public HeroTradeExcetion() {
    }

    public HeroTradeExcetion(int code, String msgFormat, Object... args) {
        super(code, msgFormat, args);
    }

    public HeroTradeExcetion(int code, String msg) {
        super(code, msg);
    }

}
