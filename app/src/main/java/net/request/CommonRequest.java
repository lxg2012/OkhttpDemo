package net.request;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

import java.io.File;
import java.util.Map;

/**
 * 可看官方github进行抽取，提取响应头,同步get，异步get等等
 * <好的解析博客></>http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0106/2275.html
 *
 */
public class CommonRequest {


    /**
     *get请求
     *
     * @param url
     * @param params
     * @return
     */
    public static Request createGetRequest(String url,RequestParams params){
        StringBuilder builder = new StringBuilder(url).append("?");
        if(params != null){
            for (Map.Entry<String,String> entry:params.urlParams.entrySet()
            ){
                builder.append(entry.getKey()).append("=").append(entry.getValue()).append("/");
            }
        }
        return new Request.Builder().url(builder.substring(0,builder.length()-1)).get().build();
    }

    /**
     * post请求
     *
     * @param url
     * @param params
     * @return
     */
    public static Request createPostRequest(String url,RequestParams params){
        FormEncodingBuilder builder = new FormEncodingBuilder();
        if(params != null){
            for (Map.Entry<String,String> entry:params.urlParams.entrySet()
                    ){
                builder.add(entry.getKey(), entry.getValue());
            }
        }
        RequestBody requestBody = builder.build();
        return new Request.Builder().url(url).post(requestBody).build();
    }

    public static final MediaType FILE_TYPE = MediaType.parse("application/octet-stream");

    /**
     * 多文件上传
     *
     * @param url
     * @param params
     */
    public static Request createMultyPostRequest(String url,RequestParams params ){

        MultipartBuilder build = new MultipartBuilder();
        if(params != null){
            for (Map.Entry<String,Object> entry:params.fileParams.entrySet()
                    ){
                if(entry.getValue() instanceof File){
                    RequestBody requestBody = RequestBody.create(FILE_TYPE, (File) entry.getValue());
                    build.addFormDataPart(entry.getKey(), ((File) entry.getValue()).getName(), requestBody);
                }else{
                    build.addFormDataPart(entry.getKey(),null);
                }
            }
        }
        return new Request.Builder().url(url).post(build.build()).build();
     //   RequestBody.create(FILE_TYPE,)
    }
}
