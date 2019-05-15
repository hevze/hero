package com.hero.common.utils.aliyun.constant;

import com.hero.common.utils.properties.ResourceUtils;

import java.util.Map;

/**
 * 类说明 ：
 *
 * @ClassName CompileConstant
 * @Author hwz.hs
 * @Date 2019/1/28 15:06
 * @Version v_1.0
 */
public interface CompileConstant {

    public final static String CHARSET = "UTF-8";

    //0支付宝；1：微信'',2,内购,3，华尔支付
    public final static int ZHIFUBAO = 0;
    public final static int WEIXIN = 1;
    public final static int IOSIAP = 2;
    public final static int HEROPAY = 3;


    /**
     * 系统文件配置 加载。
     */
    public static Map<String, String> PUBLIC_SYSTEM = ResourceUtils.getResource("public_system").getMap();

    /*****************************************************支付宝****************************************************/
    /**
     * 阿里网关
     */
    public final static String PAYALIADDRES = PUBLIC_SYSTEM.get("third.party.ali.pay.host");
    /**
     *  阿里-APPID
     */
    public final static String PAYALIAPPID=PUBLIC_SYSTEM.get("third.party.ali.pay.appid");
    /**
     *  阿里-APP 公钥
     */
    public final static String PAYALIAPPPUBLICKEY=PUBLIC_SYSTEM.get("third.party.ali.pay.app.public.key");
    /**
     *  阿里-公钥
     */
    public final static String PAYALIPUBLICKEY=PUBLIC_SYSTEM.get("third.party.ali.pay.public.key");
    /**
     *  阿里-私钥
     */
    public final static String PAYALIPRIVATEKEY=PUBLIC_SYSTEM.get("third.party.ali.pay.private.key");

    /**
     * 支付宝 回调地址
     */
    public final static  String PAY_ALI_CALL_BACK=PUBLIC_SYSTEM.get("third.party.ali.call.back");

    /**
     * 支付宝 回调地址
     */
    public final static  String PAY_ALI_CALL_BACK_IPPORT=PUBLIC_SYSTEM.get("third.party.ali.call.back");

    /*****************************************************微信****************************************************/

    /**
     * 微信appid
     */
    public final static String PAYWEIXINAPPID=PUBLIC_SYSTEM.get("third.party.weixin.appid");
    /**
     * 微信mchId
     */
    public final static String PAYWEIXINMCHID=PUBLIC_SYSTEM.get("third.party.weixin.mchid");
    /**
     * 微信API KEY
     */
    public final static String PAYWEIXINAPIKEY=PUBLIC_SYSTEM.get("third.party.weixin.api.key");
    /**
     * 微信TRADE TYPE
     */
    public final static String PAYWEIXINSIGNTYPE=PUBLIC_SYSTEM.get("third.party.weixin.sign.type");
    /**
     * 微信TRADE TYPE
     */
    public final static String PAYWEIXINTRADETYPE=PUBLIC_SYSTEM.get("third.party.weixin.trade.type");
    /**
     * 微信支付URL
     */
    public final static String PAYWEIXINURL=PUBLIC_SYSTEM.get("third.party.weixin.url");

    /**
     * 微信 回调地址
     */
    public final static  String PAY_WEIXIN_CALL_BACK=PUBLIC_SYSTEM.get("third.party.weixin.call.back");


    /*****************************************************苹果内购****************************************************/
    /**
     * 苹果内购地址
     */
    public final static String PAYIPHONEURL=PUBLIC_SYSTEM.get("third.party.iphone.url");

    /**
     * 苹果内购测试地址
     */
    public final static String PAYIPHONEURLTEST=PUBLIC_SYSTEM.get("third.party.iphone.url.test");

}
