package com.hero.common.utils.pay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.hero.common.utils.aliyun.constant.CompileConstant;
import com.hero.common.utils.pay.base.OrderInfoUtil2_0;
import com.hero.common.utils.pay.model.AliPayModel;
import com.hero.common.utils.pay.model.PayModel;
import com.hero.common.utils.str.FastJsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 类说明 ：支付宝支付工具类：实现支付，转账（提现功能）
 *
 * @ClassName ZfbPayUtils
 * @Author hwz.hs
 * @Date 2019/1/28 14:49
 * @Version v_1.0
 */
public class ZfbPayUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZfbPayUtils.class);
        private static AlipayClient alipayClient;
    private static ZfbPayUtils instance=new ZfbPayUtils();


    public ZfbPayUtils() {
        //实例化客户端
        alipayClient = new DefaultAlipayClient(CompileConstant.PAYALIADDRES, CompileConstant.PAYALIAPPID, CompileConstant.PAYALIPRIVATEKEY, "json", CompileConstant.CHARSET, CompileConstant.PAYALIPUBLICKEY, "RSA2"); //获得初始化的AlipayClient
    }

    public static ZfbPayUtils getInstance(){
        return instance;
    }

    /**
     * 阿里回调
     *
     * @param requestParams
     * @return
     * @throws Exception
     */
    public static PayModel alipayCallback(Map requestParams) throws Exception {
        LOGGER.info("- -------------支付宝回调接口成功---------开始执行-------------" + requestParams);
        PayModel payEntity = new PayModel();
        // app 应用支付公钥
        String alipaypublicKey = CompileConstant.PAYALIAPPPUBLICKEY;
        // 获取支付宝POST过来反馈信息
        Map<String, String> params = new HashMap<>();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用。
            // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        LOGGER.info("支付宝回调信息:" + params);
        boolean flag = false;
        try {
            // 支付宝验签：如果验签成功，支付宝则不会在回调，
            // 支付宝验签：如果验签失败，则会根据以下情况：25小时以内完成8次通知（通知的间隔频率一般是：4m,10m,10m,1h,2h,6h,15h）；
            flag = AlipaySignature.rsaCheckV1(params, alipaypublicKey.trim(), CompileConstant.CHARSET,"RSA2");
            LOGGER.info("回调结果:" + flag);
            if (flag) {
                String orderNo = params.get("out_trade_no").toString(); // 订单号
                String thirdOrderNo = params.get("trade_no").toString(); // 第三方订单号
                payEntity.setOut_trade_no(orderNo);
                payEntity.setThird_trade_no(thirdOrderNo);
                // 返回200 成功状态
                payEntity.setState(200);
            }
        } catch (AlipayApiException e) {
            payEntity.setState(40040001);
            return payEntity;
        }
        return payEntity;
    }


    /**
     *  支付宝 ---》加签参数
     *
     * @return
     */
    public static String alipaySignature(PayModel aliPayModel) {
        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        aliPayModel.setOut_trade_no(OrderInfoUtil2_0.getOutTradeNo());
        aliPayModel.setOrderPayType(CompileConstant.ZHIFUBAO);
        model.setBody(aliPayModel.getBody());
        model.setSubject(aliPayModel.getSubject());
        model.setOutTradeNo(aliPayModel.getOut_trade_no());
        // 暂时不用 失效时间
        //model.setTimeoutExpress("30m");
        model.setTotalAmount(aliPayModel.getTotal_amount()+"");//(aliPayModel.getTotal_amount()+"");
        model.setProductCode(aliPayModel.getProduct_code() == null ? "HERO_PAY" : aliPayModel.getProduct_code());
        request.setBizModel(model);
        // 生产环境:TODO: 需要配置回调地址IP + PORT
        String callBackPublicIp = CompileConstant.PAY_ALI_CALL_BACK_IPPORT;
        String notify_url = String.format(CompileConstant.PAY_ALI_CALL_BACK, callBackPublicIp);
        request.setNotifyUrl(notify_url);
        try {
            //这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            return response.getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 支付宝向用户转账
     * @param bizNo  逻辑单号
     * @param amount 转账金额 "1.21"单位元
     * @param account 支付宝账号
     * @param userName 支付宝真实姓名
     * @return
     */
    public static Map<String,String> alipayWithdrawal(String bizNo,String amount,String account,String userName){
         Map<String,String> resultMap=new HashMap<String,String>();
         AliPayModel vo = new AliPayModel();
         vo.setOut_biz_no(bizNo);
         vo.setPayee_type("ALIPAY_LOGONID");
         vo.setAmount(amount);
         vo.setPayee_account(account);
         vo.setPayer_show_name(userName);
         vo.setPayee_real_name(userName);
         vo.setRemark("支付宝转账");
         String json = FastJsonUtils.toJson(vo);
         // 设置请求参数
         AlipayFundTransToaccountTransferRequest alipayRequest = new AlipayFundTransToaccountTransferRequest();
         alipayRequest.setBizContent(json);
         AlipayFundTransToaccountTransferResponse response=null;
        try {
            response = alipayClient.execute(alipayRequest);
            if("10000".equals(response.getCode())){
                resultMap.put("success", "true");
                resultMap.put("des", "转账成功");
            }else{
                resultMap.put("success", "false");
                resultMap.put("des", response.getSubMsg());
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
            resultMap.put("success", "false");
            resultMap.put("des", "转账失败！");
        }
        return resultMap;
    }


}
