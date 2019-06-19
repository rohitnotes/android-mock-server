package com.xpf.android.mock.net;

import com.xpf.android.mock.constants.SpKey;
import com.xpf.android.mock.mock.MockInterceptor;
import com.xpf.android.mock.mock.MockService;
import com.xpf.android.mock.mock.Mocker;
import com.xpf.android.mock.utils.SpUtil;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by x-sir on 2018/8/1 :)
 * Function:Retrofit 2.x 网络请求的封装类
 */
public class RetrofitHelper {

    private static final String TAG = "RetrofitHelper";
    private static final String BASE_URL = "http://api.x-sir.com";
    private static final long DEFAULT_TIMEOUT = 30000L;
    private Retrofit mRetrofit;

    public static RetrofitHelper getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final RetrofitHelper INSTANCE = new RetrofitHelper();
    }

    /**
     * private constructor.
     */
    private RetrofitHelper() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(getOkhttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private OkHttpClient getOkhttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
                .build();

        // 是否需要设置模拟请求数据
        boolean isMock = SpUtil.getInstance().getBoolean(SpKey.IS_MOCK, false);
        if (isMock) {
            okHttpClient = okHttpClient.newBuilder()
                    .addInterceptor(new MockInterceptor(Mocker.create(MockService.class)))
                    .build();
        }

        return okHttpClient;
    }

    /**
     * default service.
     *
     * @return
     */
    public ApiService createService() {
        return createService(ApiService.class);
    }

    /**
     * 这里返回一个泛型类，主要返回的是定义的接口类
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T createService(Class<T> clazz) {
        if (clazz == null) {
            throw new RuntimeException("Api service is null!");
        }
        return mRetrofit.create(clazz);
    }
}
