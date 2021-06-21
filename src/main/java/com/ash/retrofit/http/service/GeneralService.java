package com.ash.retrofit.http.service;

import com.ash.retrofit.http.ResData;

import io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GeneralService {
    @POST("")
    Flowable<ResData> a(@Header("dev") String dev);
    @POST("")
    Flowable<ResData> b(@Header("dev") String dev, @Body String strBody);
    @POST("")
    Flowable<ResData> c(@Path("ver") String ver, @Header("dev") String dev, @Body String strBody);
    @POST("")
    Flowable<ResData> d(@Header("dev") String dev, @Body String strBody);
    @POST("")
    Flowable<ResData> e(@Header("dev") String dev);
    @POST("")
    Flowable<ResData> f(@Header("dev") String dev);
    @POST("")
    Flowable<ResData> g(@Header("dev") String dev, @Body String strBody);
    @POST("")
    Flowable<ResData> h(@Header("dev") String dev, @Body String strBody);
    @POST("")
    Flowable<ResData> i(@Header("dev") String dev, @Body String strBody);
    @POST("")
    Flowable<ResData> j(@Header("dev") String dev, @Body String strBody);
    @POST("")
    Flowable<ResData> k(@Header("dev") String dev, @Body String strBody);
    @POST("")
    Flowable<ResData> l(@Path("ver") String ver, @Header("dev") String dev, @Header("authToken") String authtok, @Body String strBody);
    @POST("")
    Flowable<ResData> m(@Path("ver") String ver, @Header("dev") String dev, @Header("authToken") String authtok, @Body String strBody);
    @POST("")
    Flowable<ResData> n(@Path("ver") String ver, @Header("dev") String dev, @Header("authToken") String authtok, @Body String strBody);
    @POST("")
    Flowable<ResData> o(@Path("ver") String ver, @Header("dev") String dev, @Header("authToken") String authtok, @Body String strBody);
    @Headers({"Content-Type: application/json"})
    @POST("")
    Flowable<ResData> p(@Path("ver") String ver, @Header("dev") String dev, @Body String strBody);
    @POST("auth/{version}/misRecog")
    Flowable<ResData> q(@Path("ver") String ver, @Header("dev") String dev, @Body String strBody);
    @POST("healthCheck")
    Flowable<ResData> r(@Header("dev") String dev);

}
