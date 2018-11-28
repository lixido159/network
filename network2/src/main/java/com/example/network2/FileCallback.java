package com.example.network2;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.util.Arrays;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class FileCallback implements Callback {
    private CommonCallback commonCallback;
    private Handler handler=new Handler(Looper.getMainLooper());

    public FileCallback(CommonCallback commonCallback) {
        this.commonCallback = commonCallback;
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
    public void onResponse(Call call, Response response) throws IOException {
        final Object file=response.body().bytes();
        handler.post(new Runnable() {
            @Override
            public void run() {
                commonCallback.succeed(file);
            }
        });
    }
}
