package com.example.network2;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public class RequestParam  {
    private String basicUrl;
    private Map<String,String>param=new HashMap<>();
    public static final MediaType FORM_CONTENT_TYPE
            = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");
    public RequestParam url(String url){
        basicUrl=url;
        return this;
    }
    public RequestParam setParam(String key,String value){
        param.put(key,value);
        return this;
    }
    public Request post(){
        Request request=new Request.Builder()
                .url(basicUrl)
                .post(getPost())
                .build();
        return request;
    }
    public Request get(){
        Request request=new Request.Builder()
                .url(basicUrl+getGet())
                .build();
        return request;
    }

    private RequestBody getPost(){
        StringBuilder str=new StringBuilder();
        for (Map.Entry<String,String> entry:param.entrySet()) {
            str.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        return RequestBody.create(FORM_CONTENT_TYPE,str.substring(0,str.length()-1));
    }
    private String getGet(){
        StringBuilder str=new StringBuilder("?");
        for (Map.Entry<String,String> entry:param.entrySet()) {
            str.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        return str.substring(0,str.length()-1);
    }
}
