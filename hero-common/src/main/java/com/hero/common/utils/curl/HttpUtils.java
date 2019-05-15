package com.hero.common.utils.curl;

import okhttp3.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class HttpUtils {

    private static OkHttpClient client;
    //超时时间
    public static final int TIMEOUT = 1000 * 10;

    //json请求
    public static final MediaType JSON = MediaType
            .parse("application/json; charset=utf-8");

    static {
        client = new OkHttpClient.Builder()
                .readTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .connectTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .addNetworkInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        String host = request.url().host();
                        HttpUrl.Builder url = request.url().newBuilder();
                        Request newRequest = null;
                        if (host.contains("sina")) {
                            //添加请求头
                            newRequest = request.newBuilder()
                                    .header("Host", host)
                                    .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36")
                                    .header("Referer", host)
                                    .method(request.method(), request.body())
                                    .url(url.build())
                                    .build();
                        } else if(host.contains("dfcfw")){
                            //添加请求头
                            newRequest = request.newBuilder()
                                    .header("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                                    .header("Accept-Encoding","gzip, deflate")
                                    .header("Accept-Language","zh-CN,zh;q=0.9")
                                    .header("Cache-Control","max-age=0")
                                    .header("Connection","keep-alive")
                                    .header("Host", "nufm.dfcfw.com")
                                    .header("Upgrade-Insecure-Requests", "1")
                                    .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36")
                                    .method(request.method(), request.body())
                                    .url(url.build())
                                    .build();
                        }else if(host.contains("sse.com")){
                            //添加请求头
                            newRequest = request.newBuilder()
                                    .header("Accept","*/*")
                                    .header("Accept-Encoding","gzip, deflate")
                                    .header("Accept-Language","zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2")
                                    .header("Connection","keep-alive")
                                    .header("Cookie", "yfx_c_g_u_id_10000042=_ck18040916475915602674706443782; yfx_f_l_v_t_10000042=f_t_1523263679514__r_t_1523263679514__v_t_1523266341328__r_c_0; VISITED_MENU=%5B%228349%22%5D")
                                    .header("Host", "query.sse.com.cn")
                                    .header("Referer", "http://www.sse.com.cn/disclosure/listedinfo/announcement/")
                                    .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:59.0) Gecko/20100101 Firefox/59.0")
                                    .method(request.method(), request.body())
                                    .url(url.build())
                                    .build();
                        }else if(host.contains("huobi")){
                            //添加请求头
                            newRequest = request.newBuilder()
                                    .header("user-agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36")
                                    .header("Content-Type","application/x-www-form-urlencoded")
                                    .method(request.method(), request.body())
                                    .url(url.build())
                                    .build();
                        }else if(host.contains("192.168.0.222")){
                            //添加请求头
                            newRequest = request.newBuilder()
                                    .header("user-agent","web")
                                    .header("Content-Type","application/x-www-form-urlencoded")
                                    .header("timestamp",System.currentTimeMillis() + "")
                                    .method(request.method(), request.body())
                                    .url(url.build())
                                    .build();
                        }else{
                            newRequest = request.newBuilder().method(request.method(), request.body())
                                    .url(url.build())
                                    .build();
                        }
                        Response originalResponse = chain.proceed(newRequest);
                        //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                        return originalResponse.newBuilder().build();

                    }
                })
                .build();
    }

    /**
     * post请求  json数据为body
     */
    public static void postJson(String url, String json, final HttpCallBack callBack) {
        RequestBody body = RequestBody.create(JSON, json);
        final Request request = new Request.Builder().url(url).post(body).build();

        OnStart(callBack);

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                OnError(callBack, e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    onSuccess(callBack, response.body().string());
                } else {
                    OnError(callBack, response.message());
                }
            }
        });
    }

    /**
     * post请求  map是body
     *
     * @param url
     * @param map
     * @param callBack
     */
    public static void postMap(String url, Map<String, String> map, final HttpCallBack callBack) {
        FormBody.Builder builder = new FormBody.Builder();

        //遍历map
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                builder.add(entry.getKey(), entry.getValue().toString());
            }
        }
        RequestBody body = builder.build();
        Request request = new Request.Builder().url(url).post(body).build();
        OnStart(callBack);
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                OnError(callBack, e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    onSuccess(callBack, response.body().string());
                } else {
                    OnError(callBack, response.message());
                }
            }
        });
    }

    /**
     * get 请求
     *
     * @param url
     * @param callBack
     */
    public static void getJson(String url, final HttpCallBack callBack) {
        Request request = new Request.Builder().url(url).build();
        OnStart(callBack);
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                OnError(callBack, e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String url = response.request().url().toString();
                    String info = "";
                    // windows 需要转码，linux  不用转码
                    if(url.indexOf("newFLJK") > -1  || url.indexOf("newSinaHy") > -1 || url.indexOf("disclosure.szse.cn") > -1){
                        byte[] b = response.body().bytes(); //获取数据的bytes
                        info = new String(b, "GBK"); //然后将其转为gb2312
                    }else{
                        info = response.body().string();
                    }
                    onSuccess(callBack, info);
                } else {
                    OnError(callBack, response.message());
                }
            }
        });
    }


    /**
     * post请求  json数据为body
     */
    public static String syncPostJson(String url, String json) {
        Response response = null;
        try {
            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder().url(url).post(body).build();
            Call call = client.newCall(request);
            response = call.execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * post请求  map是body
     *
     * @param url
     * @param map
     */
    public static String syncPostMap(String url, Map<String, String> map) {
        Response response = null;
        try {
            FormBody.Builder builder = new FormBody.Builder();
            //遍历map
            if (map != null) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    builder.add(entry.getKey(), entry.getValue().toString());
                }
            }
            RequestBody body = builder.build();
            Request request = new Request.Builder().url(url).post(body).build();

            Call call = client.newCall(request);
            response = call.execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * post请求  map是body
     *
     * @param url
     */
    public static String syncPostList(String url, List<String> params, String key) {
        Response response = null;
        try {
            FormBody.Builder builder = new FormBody.Builder();
            for (String param:params){
                builder.add(key, param);
            }
            RequestBody body = builder.build();
            Request request = new Request.Builder().url(url).post(body).build();

            Call call = client.newCall(request);
            response = call.execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * get 请求
     *
     * @param url
     */
    public static String syncGetJson(String url) throws RuntimeException {
        Response response = null;
        try {
            Request request = new Request.Builder().url(url).build();
            Call call = client.newCall(request);
            response = call.execute();
            String info = "";
            if(url.indexOf("newFLJK") > -1  || url.indexOf("newSinaHy") > -1 || url.indexOf("disclosure.szse.cn") > -1){
                byte[] b = response.body().bytes(); //获取数据的bytes
                info = new String(b, "GBK"); //然后将其转为gb2312
            }else{
                info = response.body().string();
            }
            return info;
        } catch (IOException e) {
            //throw new RuntimeException("系统请求超时.");
            return "";
        }
    }


    /**
     * get 请求
     *
     * @param url
     */
    public static String syncDELETE(String url) {
        Response response = null;
        try {
            FormBody formBody = new FormBody.Builder().build();
            Request.Builder builder = new Request.Builder().url(url).delete(formBody);
            Request request = builder.build();
            Call call = client.newCall(request);
            response = call.execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void OnStart(HttpCallBack callBack) {
        if (callBack != null) {
            callBack.onstart();
        }
    }

    public static void onSuccess(final HttpCallBack callBack, final String data) {
        if (callBack != null) {

            callBack.onSusscess(data);

        }
    }

    public static void OnError(final HttpCallBack callBack, final String msg) {
        if (callBack != null) {
            callBack.onError(msg);

        }
    }

    public static abstract class HttpCallBack {
        //开始
        public void onstart() {
        }

        ;

        //成功回调
        public abstract void onSusscess(String data);

        //失败
        public void onError(String meg) {
        }

        ;
    }
}
