package app.lixian.okhttpdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import net.client.CommonOkHttpClient;
import net.listener.DisponseDataHandle;
import net.listener.DisponseDataListener;
import net.request.CommonRequest;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CommonOkHttpClient.Instance().get(CommonRequest.createGetRequest("http://www.cnblogs.com/hegezhou_hot/archive/2010/12/02/1894771.html",null),new DisponseDataHandle(new DisponseDataListener() {
            @Override
            public void onSuccess(Object responseObj) {

            }

            @Override
            public void onFailure(Object responseObj) {

            }
        }));
    }
}
