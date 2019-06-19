package com.xpf.android.mock.net;

import com.xpf.android.mock.entity.BaseEntity;
import com.xpf.android.mock.entity.Xy0001Entity;

import io.reactivex.Flowable;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by x-sir on 2018/8/10 :)
 * Function:
 */
public interface Xy0001Service {

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("{url}")
    Flowable<BaseEntity<Xy0001Entity>> xy0001(@Path(value = "url", encoded = true) String url);
}
