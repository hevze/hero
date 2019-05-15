package com.hero.api.context;


/**
 * 类说明 ：
 *
 * @ClassName KEYConstant
 * @Author hwz.hs
 * @Date 2018/6/13 13:57
 * @Version v_1.0
 */
public interface KEYConstant {

    /**
     * 获取 X K线数据
     */
    /*** 请求k线 及时价格数据 **/
    int TIMELYK = 0;

    /*** 请求k线 分时数据 **/
    int K1 = 1;

    /*** 请求交易5档线 分时数据 **/
    int K2 = 2;

    /*** 请求1k线 数据 **/
    int KMin1 = 3;

    /*** 请求5k线 数据 **/
    int KMin5 = 4;

    /*** 请求5k线 数据 **/
    int KMin15 = 5;

    /*** 请求30k线 数据 **/
    int KMin30 = 6;

    /*** 请求60k线 数据 **/
    int KMin60 = 7;

    /*** 请求24k线 数据 **/
    int KDay = 8;

    /***  周 **/
    int KWEEK = 9;
    /***  月 **/
    int KMONGTH = 10;
    /** 10档 **/
    int TEN = 11;

}
