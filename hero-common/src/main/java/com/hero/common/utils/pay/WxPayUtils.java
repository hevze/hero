package com.hero.common.utils.pay;

import com.google.common.collect.ImmutableMap;
import com.hero.common.utils.aliyun.constant.CompileConstant;
import com.hero.common.utils.pay.base.MapUtils;
import com.hero.common.utils.pay.base.OrderInfoUtil2_0;
import com.hero.common.utils.pay.base.PayCommonUtil;
import com.hero.common.utils.pay.base.XMLUtil;
import com.hero.common.utils.pay.model.PayModel;
import org.jdom.JDOMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.*;

/**
 * 类说明 ：微信支付工具类
 *
 * @ClassName WxPayUtils
 * @Author hwz.hs
 * @Date 2019/1/28 14:48
 * @Version v_1.0
 */
public class WxPayUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZfbPayUtils.class);


    /**
     * 微信支付
     *
     * @param weixinPayModel
     * @return
     */
    public static Map<String, Object> getWeixinPayOrderInfo(PayModel weixinPayModel) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        // 设置回调地址-获取当前的地址拼接回调地址
        String domain = weixinPayModel.getLocal_addr();

        // 生产环境
        String callBackPublicIp = "";//CompileConstant.PAYCALLPUBLICIP;
        String callBackPrivateIp ="";// CompileConstant.PAYCALLPRIVATEIP;
        Integer callBackPort = 0;//CompileConstant.PAYCALLPORT;
        String notify_url = String.format(CompileConstant.PAY_WEIXIN_CALL_BACK, callBackPublicIp,
                callBackPort);
        // 测试改动本地公网ip
        weixinPayModel.setLocal_ip(callBackPrivateIp);

        LOGGER.info("微信回调地址：" + notify_url);
        // 测试环境
        weixinPayModel.setOut_trade_no(OrderInfoUtil2_0.getOutTradeNo()); // 商户订单号
        SortedMap<String, Object> parameters = prepareOrder(notify_url, weixinPayModel);
        // 设置签名
        String sign = PayCommonUtil.createSign("UTF-8", parameters);
        parameters.put("sign", sign);
        // 封装请求参数结束
        String requestXML = PayCommonUtil.getRequestXml(parameters);

        // 调用统一下单接口
        String result = PayCommonUtil.httpsRequest(CompileConstant.PAYWEIXINURL, "POST", requestXML);
        LOGGER.info("调用统一下单接口返回信息:" + result);
        if (result.indexOf("FAIL") != -1) {
            // TODO: 返回失败信息
            //throw new Exception(PayException.GET_WEIXIN_DOC_ERR, "调用统一下单接口失败...");
        }
        LOGGER.info("- -----统一下定单开始 用户：" + weixinPayModel.getUserName() + " 商品id：" + weixinPayModel.getOut_trade_no()
                + " ip:" + weixinPayModel.getLocal_ip() + " 金额:" + weixinPayModel.getTotal_amount());
        try {
            /**
             * 统一下单接口返回正常的prepay_id，再按签名规范重新生成签名后，将数据传输给APP。参与签名的字段名为appId，
             * partnerId，prepayId，nonceStr，timeStamp，package。注意：package的值格式为Sign
             * =WXPay
             **/
            Map<String, String> map = XMLUtil.doXMLParse(result);
            SortedMap<String, Object> parameterMap2 = new TreeMap<String, Object>();
            parameterMap2.put("appid", CompileConstant.PAYWEIXINAPPID);
            parameterMap2.put("partnerid", CompileConstant.PAYWEIXINMCHID);
            parameterMap2.put("prepayid", map.get("prepay_id"));
            parameterMap2.put("package", "Sign=WXPay");
            parameterMap2.put("noncestr", PayCommonUtil.CreateNoncestr());
            // 本来生成的时间戳是13位，但是ios必须是10位，所以截取了一下
            parameterMap2.put("timestamp",
                    Long.parseLong(String.valueOf(System.currentTimeMillis()).toString().substring(0, 10)));
            String sign2 = PayCommonUtil.createSign("UTF-8", parameterMap2);
            parameterMap2.put("sign", sign2);
            resultMap.put("code", "200");
            resultMap.put("msg", parameterMap2);
            weixinPayModel.setOrderPayType(CompileConstant.WEIXIN);
            weixinPayModel.setThird_trade_no(map.get("prepay_id").trim());
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    /**
     * 微信回调
     *
     * @param inputStream
     * @return
     */
    public static String weixinPayCallback(InputStream inputStream) {
        PayModel payEntity = new PayModel();
        try {
            LOGGER.info("- -------------微信回调接口成功---------开始执行-------------");
            // 读取参数
            StringBuffer sb = new StringBuffer();
            String s;
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            while ((s = in.readLine()) != null) {
                sb.append(s);
            }
            in.close();
            inputStream.close();
            // 解析xml成map
            Map<String, String> m = new HashMap<>();
            m = XMLUtil.doXMLParse(sb.toString());
            for (Object keyValue : m.keySet()) {
                LOGGER.info(keyValue + "=" + m.get(keyValue));
            }
            // 过滤空 设置 TreeMap
            SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
            Iterator it = m.keySet().iterator();
            while (it.hasNext()) {
                String parameter = (String) it.next();
                String parameterValue = m.get(parameter);

                String v = "";
                if (null != parameterValue) {
                    v = parameterValue.trim();
                }
                packageParams.put(parameter, v);
            }
            // 判断签名是否正确
            String resXml = "";
            String resStr = "";
            if (PayCommonUtil.isTenpaySign("UTF-8", packageParams)) {
                if ("SUCCESS".equals(packageParams.get("result_code") + "")) {
                    // 这里是支付成功
                    ////////// 执行自己的业务逻辑////////////////
                    String mch_id = (String) packageParams.get("mch_id"); // 商户号
                    String openid = (String) packageParams.get("openid"); // 用户标识
                    String out_trade_no = (String) packageParams.get("out_trade_no"); // 商户订单号
                    String total_fee = (String) packageParams.get("total_fee");
                    String transaction_id = (String) packageParams.get("transaction_id"); // 微信支付订单号
                    payEntity.setOut_trade_no(out_trade_no);
                    payEntity.setThird_trade_no(transaction_id);
                    if (!CompileConstant.PAYWEIXINMCHID.equals(mch_id)) {
                        LOGGER.info("支付失败,错误信息：" + "参数错误");
                        resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                                + "<return_msg><![CDATA[参数错误]]></return_msg>" + "</xml> ";
                    } else {


                        /*if (!"1".equals(order.getOrderStatus())) {// 判断订单的状态是否支付完成
                            LOGGER.info("订单被修改了，还进入了修改订单");
                            // 订单状态的修改。根据实际业务逻辑执行

                            resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                                    + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
                            resStr = "订单支付成功";
                            LOGGER.info(resStr);
                        } else {
                            resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                                    + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
                            resStr = "订单已处理";
                            LOGGER.info(resStr);
                        }*/
                    }

                } else {
                    resStr = "支付失败,错误信息：" + packageParams.get("err_code");
                    LOGGER.info(resStr);
                    resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                            + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
                }

            } else {
                resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                        + "<return_msg><![CDATA[通知签名验证失败]]></return_msg>" + "</xml> ";
                resStr = "通知签名验证失败";
                LOGGER.info(resStr);
            }
            return resStr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 生成微信订单信息
     *
     * @param notifyUrl
     * @param weixinPayModel
     * @return
     */
    private static SortedMap<String, Object> prepareOrder(String notifyUrl, PayModel weixinPayModel) {

        Map<String, Object> oparams = ImmutableMap.<String, Object>builder().put("appid", CompileConstant.PAYWEIXINAPPID)// 服务号的应用号
                .put("body", weixinPayModel.getSubject())// 商品描述
                .put("mch_id", CompileConstant.PAYWEIXINMCHID)// 商户号
                .put("nonce_str", PayCommonUtil.CreateNoncestr())// 16随机字符串(大小写字母加数字)
                .put("out_trade_no", weixinPayModel.getOut_trade_no())// 商户订单号
                .put("total_fee", weixinPayModel.getTotal_amount().multiply(new BigDecimal(100)).setScale(0))// 支付金额
                // 单位分
                // 注意:前端负责传入分
                .put("spbill_create_ip", weixinPayModel.getLocal_ip())// IP地址
                .put("notify_url", notifyUrl) // 微信回调地址
                .put("trade_type", CompileConstant.PAYWEIXINTRADETYPE)// 支付类型 app
                .build();
        LOGGER.info("weixin pay notify_url:" + notifyUrl);
        return MapUtils.sortMap(oparams);
    }
}
