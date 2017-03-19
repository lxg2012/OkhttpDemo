package net.callback;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import net.listener.DisponseDataHandle;
import net.listener.DisponseDataListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * CallBack获取Response封装
 */
public class CommonJsonCallBack implements Callback{

    /**
     * 依据自己公司服务器返回值
     */
    protected final String RESULT_CODE ="ecode";
    protected final int RESULT_CODE_VALUE =0;
    protected final String ERROR_MSG ="emsg";
    protected final String EMPTY_CODE ="";

    protected final int NET_ERROR =-1;
    protected final int JSON_ERROR =-2;
    protected final int OTHER_ERROR =-3;

    private DisponseDataListener mDisponseDataListener;
    private Class<?> mClass;
    private Handler mHandler;


    public CommonJsonCallBack(DisponseDataHandle disponseDataHandle) {
        mDisponseDataListener = disponseDataHandle.mDisponseDataListener;
        mClass = disponseDataHandle.mClass;

        //返回主线程的handle
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void onFailure(Request request, final IOException e) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mDisponseDataListener.onFailure(e);
            }
        });
    }

    @Override
    public void onResponse(final Response response) throws IOException {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                  handleResponse(response.body().toString());
            }
        });
    }

    private void handleResponse(String result) {

        if(TextUtils.isEmpty(result)){
            mDisponseDataListener.onFailure(NET_ERROR);
        }else{

            try {
                JSONObject resultObj = new JSONObject(result);
                if(resultObj.has(RESULT_CODE)){
                    if(resultObj.optInt(RESULT_CODE)!=RESULT_CODE_VALUE){
                        mDisponseDataListener.onFailure(JSON_ERROR);
                    }
                    else{
                        if(mClass == null){
                            mDisponseDataListener.onSuccess(resultObj);
                        }else{
                            //解析数据..........


                            mDisponseDataListener.onSuccess(resultObj);
                        }
                    }
                }
            } catch (JSONException e) {
                mDisponseDataListener.onFailure(e);
            }
        }
    }
}
