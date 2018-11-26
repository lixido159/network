package com.example.network2;

public interface CommonCallback<T> {
    void networkError();
    void succeed(T result);

}
