package com.example.network2;

import java.io.IOException;

public interface CommonCallback<T> {
    void networkError(IOException e);
    void succeed(T result);

}
