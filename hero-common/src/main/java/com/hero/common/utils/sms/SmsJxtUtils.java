package com.hero.common.utils.sms;

import com.hero.common.utils.curl.HttpUtils;
import com.hero.common.utils.properties.ResourceUtils;

import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;

/**
 * 类说明 ：
 *
 * @ClassName SmsJxtUtils
 * @Author hwz.hs
 * @Date 2019/4/1 18:24
 * @Version v_1.0
 */
public class SmsJxtUtils {

    static final Map<String, String> PUBLIC_SYSTEM = ResourceUtils.getResource("public_system").getMap();
    private static final String ID = PUBLIC_SYSTEM.get("sms.account");
    private static final String PWD = PUBLIC_SYSTEM.get("sms.password");


    public static Boolean smsSend(String msg,String tel,int temp){
        try{
            String path = "http://service.winic.org/sys_port/gateway/index.asp?id=%s&pwd=%s&to=%s&content=%s&time=%s";
            msg = smsTemplate(temp,msg);
            msg = URLEncoder.encode(msg, "gb2312");
            path = String.format(path,ID,PWD,tel,msg,new Date().getTime());
            String result = HttpUtils.syncGetJson(path);
            String [] datas = result.split("/");
            if(datas[0].equals("000")){
                return true;
            }
            return false;
        }catch (Exception e){
            return false;
        }
    }


    /**
     * 发送短信模板
     * @param temp
     */
    private static String smsTemplate(int temp,String msg){
        /*switch (temp){
            case 0:
                msg =   "您好,尊敬的用户，你的注册验证码为:" + msg + ".";
                break;
            case 1:
                msg =   "您好,尊敬的用户，安全中心帮助您修改登录密码,您的修改登录密码验证码为:" + msg + ".";
                break;
            case 2:
                msg =   "您好,尊敬的用户，安全中心帮助您修改资金密码,您的修改资金密码验证码为:" + msg + ".";
                break;
            default:
                msg =   "您好,尊敬的用户，您的验证码为:" + msg + ".";
                break;
        }*/
        return "你的验证码为:"+msg+"有效时间5分钟，我们不会索要任何验证码不要告知他人，警防诈骗，请不要告知他人.";
    }

}
