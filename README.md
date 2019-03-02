# 第三组-OneBillion

#### 项目介绍
2016电子商务第三小组

#### 成员
全寅	15222327860	qy15367555118
赵子豪	13102192707	zzh506767805
姜笑	13102192707	13102192707
韩潇君	13821506062	hxj19981009
	



### 上传自己制作的第三方库
#### 介绍
对OKhttp进行了简单的封装，实现了get、post请求

### 使用说明
首先在分别在project和app的gradle文件中加入所示依赖

```
	allprojects {//procject.gradle
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```



```
	dependencies {//app.gradle
	        implementation 'com.github.lixido159:network:2.11'
	}

```



#### 1.得到Request


```
Request request=new RequestParam().url("http://172.17.90.68:8080/course/get")//请求地址
                .setParam("page","0")//请求参数
                .setParam("size","5")
                .post();//.get();//请求类型
```

#### 2.发送请求

```
NetworkClient.getInstance().enqueue(request,new CommonClientCallback(new JsonParse<T>() {
            @Override
            public T parse(String s) {//将json转化为自己想要的类
                return s;
            }
        }, new CommonCallback<T>() {
            @Override
            public void networkError() {

            }

            @Override
            public void succeed(T result) {
                
            }
        }));

```


还可以发送接收文件...懒得写了