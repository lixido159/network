package com.example.network2;

import android.content.Context;
import android.net.Uri;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

public class FileRequestParam {
    private String basicUrl;
    private Map<String,String> param=new HashMap<>();
    private RequestBody requestBody;
    private Context context;
    private MediaType mediaType=MediaType.parse("image/*");
    private String fileName;
    public FileRequestParam(Context context) {
        this.context=context;
    }

    public FileRequestParam url(String basicUrl){
        this.basicUrl=basicUrl;
        return this;
    }
    public FileRequestParam setParam(String key,String value){
        param.put(key, value);
        return this;
    }
    public FileRequestParam setFile(Uri uri,String paramName) {
        try {
            requestBody=RequestBody.create(null,parseUri(uri));
            this.fileName=paramName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }
    public Request build(){
        MultipartBody.Builder builder=new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        for (Map.Entry<String,String> e:param.entrySet()) {
            builder.addFormDataPart(e.getKey(),e.getValue());
        }
        builder.addFormDataPart(fileName,"head-img",requestBody);

        Request request=new Request.Builder()
                .url(basicUrl)
                .post(builder.build())
                .build();
        return request;
    }

    private byte[] parseUri(Uri uri) throws Exception {
        InputStream inputStream= context.getContentResolver().openInputStream(uri);
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        outStream.close();
        inputStream.close();
        return outStream.toByteArray();
    }

}
