package com.hero.api.interceptors;

import com.hero.api.context.SpringContext;
import com.hero.api.init.InitProvider;
import com.hero.common.cache.RedisClientTemplate;
import com.hero.common.utils.encry.MD5;
import com.hero.common.utils.jwt.JsonWebTokenUtil;
import com.hero.common.utils.str.Sign;
import io.jsonwebtoken.Claims;
import com.hero.api.GlobalException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 统一客户端请求拦截器
 * <p>
 * https://juejin.im/post/5a37c3f9518825258b741f49
 */
public class ApiInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(ApiInterceptor.class);
    private static final String ACCESSKEY = "accessKey";
    private static final String LOGINNAME = "loginName";
    private RedisClientTemplate redisClientTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        System.out.println(request.getParameterMap());
        logger.info(String.format("请求参数, url: %s, method: %s, uri: %s, params: %s", url, method, uri, queryString));
        if (InitProvider.mCheckSignMap.get(uri) != null) {
            String sign = request.getParameter("sign");
            if (!checkSign(sign, request)) {
                throw new GlobalException(GlobalException.SIGN_ERROR, "数据签名错误!");
            }
        }
        String deviceToken = request.getHeader("Devices-Token");
        request.setAttribute("devicesName", deviceToken);
        //需要验证登录的才取accesskey进行校验
        String accessKey = request.getHeader(ACCESSKEY);
        String loginName = "", clientToken = "";
        if (StringUtils.isNotEmpty(accessKey)) {
            try {
                Claims claims = JsonWebTokenUtil.parseJwt(accessKey,JsonWebTokenUtil.SECRET_KEY);
                loginName = claims.getId();
                clientToken = claims.getSubject();
                request.setAttribute(LOGINNAME, loginName);
            }catch (Exception e){
                throw new GlobalException(GlobalException.USER_PERMISSION, "用户登录失效，请重新登录!");
            }
        }
        if (InitProvider.mCheckLoginMap.get(uri) != null) {
            if (StringUtils.isNotEmpty(clientToken)) {
                if (null == redisClientTemplate) {
                    redisClientTemplate = (RedisClientTemplate) SpringContext.getContext().getBean("redisUtils");
                }
                String sToken = redisClientTemplate.hget(""/*UserConstant.RedisKey.XTOKEN*/, loginName, String.class);
                if (StringUtils.isNotEmpty(sToken) && sToken.equals(accessKey)) {
                    return true;
                } else {
                    throw new GlobalException(GlobalException.USER_PERMISSION, "用户登录失效，请重新登录!");
                }
            } else {
                throw new GlobalException(GlobalException.NO_PERMISSION_CALL, "游客无权限访问,请登录!");
            }
        } else {
            logger.debug("游客身份调用API,{}", uri);
            //游客
//            throw new GlobalException(GlobalException.NO_PERMISSION_CALL, "游客身份无权限调用该API!");
        }
        return true;
    }

    /**
     * 检验sign
     * 签名算法过程：
     * 1.对除签名外的所有请求参数按key做的升序排列,value无需编码。 （假设当前时间的时间戳是12345678）
     * 例如：有c=3,b=2,a=1 三个参，另加上时间戳后， 按key排序后为：a=1，b=2，c=3，_timestamp=12345678。
     * 2 把参数名和参数值连接成字符串，得到拼装字符：a1b2c3_timestamp12345678
     * 3 用申请到的appkey 连接到接拼装字符串头部和尾部，然后进行32位MD5加密，最后将到得MD5加密摘要转化成大写。
     * 示例：假设appkey=test，md5(testa1b2c3_timestamp12345678test)，取得MD5摘要值 C5F3EB5D7DC2748AED89E90AF00081E6 。
     *
     * @param sign
     * @param request
     * @return
     */
    private boolean checkSign(String sign, HttpServletRequest request) {
        if (StringUtils.isEmpty(sign)) {
            //验签sign为空
            return false;
        }
//        String appKey = request.getParameter("appKey");
//        String appSecret = redisClientTemplate.hget("appKey", appKey, String.class);
        String linkString = Sign.createLinkString(getParameterStringMap(request));
        String md5Sign = MD5.getMD5Str(linkString);
        if (sign.equalsIgnoreCase(md5Sign)) {
            return true;
        }
        return false;
    }

    /**
     * 返回值类型为Map<String, String>
     */
    private Map<String, String> getParameterStringMap(HttpServletRequest request) {
        Map<String, String[]> properties = new HashMap<>(request.getParameterMap());//把请求参数封装到Map<String, String[]>中
        properties.remove("sign");
        Map<String, String> returnMap = new HashMap<>();
        String name = "";
        String value = "";
        for (Map.Entry<String, String[]> entry : properties.entrySet()) {
            name = entry.getKey();
            String[] values = entry.getValue();
            if (null == values) {
                value = "";
            } else if (values.length > 1) {
                for (int i = 0; i < values.length; i++) { //用于请求参数中有多个相同名称
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length() - 1);
            } else {
                value = values[0];//用于请求参数中请求参数名唯一
            }
            value = value.replaceAll(" ", "+");
            returnMap.put(name, value);
        }
        return returnMap;
    }


}
