package com.xpf.android.mock.net;

import android.database.Observable;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by x-sir on 2018/8/1 :)
 * Function:
 */
public interface ApiService {

    // GET 无参数请求
    @GET("{url}")
    Call<ResponseBody> getRequest(
            @Path("url") String url);

    // GET 带参数请求
    @GET("{url}")
    Call<ResponseBody> getMapParam(
            @Path("url") String url,
            @QueryMap Map<String, String> maps);

    //  POST Map body 请求
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("{url}")
    Call<ResponseBody> postMapBody(
            @Path(value = "url", encoded = true) String url,
            @Body Map<String, String> maps);

    // POST RequestBody 必须要设置 encoded = true, 否则 url 有编码问题
    // 使用@Path时，path对应的路径不能包含”/”，否则会将其转化为%2F，在遇到想动态的拼接多节url时，还是使用@Url吧
    @Headers({"Content-Type:application/json", "Accept:application/json"})
    @POST("{url}")
    Call<ResponseBody> postRequestBody(
            @Path(value = "url", encoded = true) String url,
            @Body RequestBody body);

    //  POST Url body 请求(适用于动态域名访问，当url为全域名时，会使用url的全域访问，当为非全域时，会拼接到BASE_URL的后面)
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST
    Call<ResponseBody> postUrlBody(
            @Url String url,
            @Body Map<String, String> maps);

    // 提交 FORM 表单数据
    @FormUrlEncoded
    @POST("{url}")
    Call<ResponseBody> postFormData(
            @Path("url") String url,
            @FieldMap Map<String, String> params);

    // 单文件/图片上传
    @Multipart
    @POST("{url}")
    Observable<ResponseBody> upLoadFile(
            @Path("url") String url,
            @Part("image\"; filename=\"image.jpg") RequestBody requestBody);

    // 多文件/图片上传
    @POST("{url}")
    Call<ResponseBody> uploadFiles(
            @Path("url") String url,
            @Path("headers") Map<String, String> headers,
            @Part("filename") String description,
            @PartMap() Map<String, RequestBody> maps);

    // 文件下载
    @Streaming
    @GET
    Observable<ResponseBody> downloadFile(
            @Url String fileUrl);
}
