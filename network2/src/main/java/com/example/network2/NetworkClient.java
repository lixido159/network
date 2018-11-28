package com.example.network2;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class NetworkClient{
    private static NetworkClient mClient = null;
    private OkHttpClient mOkHttpClient;

    public static NetworkClient getInstance() {
        if (mClient == null) {
            mClient = new NetworkClient();
        }
        return mClient;
    }

    public NetworkClient() {
        mOkHttpClient = new OkHttpClient();
    }


    public void enqueue(Request request, Callback callback) {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(callback);
    }


}
