
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