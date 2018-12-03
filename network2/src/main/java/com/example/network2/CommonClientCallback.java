package com.example.network2;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CommonClientCallback implements Callback {
    private CommonCallback commonCallback;
    private Handler handler=new Handler(Looper.getMainLooper());
    JsonParse jsonParse;

    public CommonClientCallback(JsonParse jsonParse,CommonCallback commonCallback) {
        this.commonCallback = commonCallback;
        this.jsonParse=jsonParse;
    }

    @Override
    public void onFailure(Call call, final IOException e) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                commonCallback.networkError(e);
            }
        });

    }

    @Override
    public void onResponse(final Call call, Response response) throws IOException {
        final String json=response.body().string();
        final Object o=jsonParse.parse(json);
        handler.post(new Runnable() {
            @Override
            public void run() {
                commonCallback.succeed(o);
            }
        });

    }


}
