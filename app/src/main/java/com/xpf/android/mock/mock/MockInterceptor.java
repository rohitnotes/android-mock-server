package com.xpf.android.mock.mock;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by x-sir on 2019/1/23 :)
 * Function:
 */
public class MockInterceptor implements Interceptor {

    private Mocker mMocker;

    public MockInterceptor(Mocker mocker) {
        this.mMocker = mocker;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = mMocker.mockResult(request);
        if (response != null) {
            return response;
        }

        try {
            response = chain.proceed(request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }
}


