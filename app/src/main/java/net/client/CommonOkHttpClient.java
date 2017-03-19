package net.client;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import net.callback.CommonJsonCallBack;
import net.listener.DisponseDataHandle;

import java.util.concurrent.TimeUnit;

/**
 * 简单封装
 */
public class CommonOkHttpClient {

    private static CommonOkHttpClient _instance = null;
    private OkHttpClient client = null;
    private int TIME_OUT = 30;

    private CommonOkHttpClient() {
        //cao 这个版本有点毒,没用最新的okhttp
        //OkHttpClient.Bul
        client =new OkHttpClient();

        /**
         * 支持HTTPS信任所有的证书
         */
       /* client.setHostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });*/
        //client.setSslSocketFactory()

        client.setConnectTimeout(TIME_OUT, TimeUnit.SECONDS);
        client.setReadTimeout(TIME_OUT, TimeUnit.SECONDS);
        client.setWriteTimeout(TIME_OUT, TimeUnit.SECONDS);
        client.setFollowRedirects(true);
        //client.setSslSocketFactory()
//		client.setMaxRetriesAndTimeout(3, 10000);// 重试3次，每次超时10秒
    }

    public static CommonOkHttpClient Instance() {
        if (_instance == null) {
            synchronized (CommonOkHttpClient.class){
                if (_instance == null)
                    _instance = new CommonOkHttpClient();
            }
        }
        return _instance;
    }

    public void get(Request request ,DisponseDataHandle disponseDataHandle){
        Call call = client.newCall(request);
        call.enqueue(new CommonJsonCallBack(disponseDataHandle));
    }

    public void post(Request request ,DisponseDataHandle disponseDataHandle){
        Call call = client.newCall(request);
        call.enqueue(new CommonJsonCallBack(disponseDataHandle));
    }

}
