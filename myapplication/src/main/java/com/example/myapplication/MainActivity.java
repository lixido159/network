package com.example.myapplication;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.network2.CommonCallback;
import com.example.network2.CommonClientCallback;
import com.example.network2.FileRequestParam;
import com.example.network2.JsonParse;
import com.example.network2.NetworkClient;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.ByteBuffer;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        imageView=findViewById(R.id.img);
        startActivityForResult(intent,0);


    }

    @Override
    protected void onActivityResult(int requestCode, final int resultCode, @Nullable Intent data) {
        if (resultCode==RESULT_OK){
            Request request=new FileRequestParam(this).setParam("name","全寅把")
                    .url("http://172.28.240.165:8080/upload")
                    .setFile(data.getData(),"file")
                    .build();
            final Handler handler=new Handler(Looper.getMainLooper());
            new OkHttpClient().newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, final Response response) throws IOException {
                    final Object bytes=response.body().bytes();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            byte[]a=(byte[])bytes;
                            imageView.setImageBitmap(BitmapFactory.decodeByteArray(a,0,a.length));


                        }
                    });
                }
            });
        }
    }
}
