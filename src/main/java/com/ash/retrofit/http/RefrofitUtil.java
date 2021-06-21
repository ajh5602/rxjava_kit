package com.ash.retrofit.http;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ash.retrofit.SecurityUtils;
import com.ash.retrofit.http.service.GeneralService;

import io.reactivex.Flowable;
import retrofit2.Retrofit;


public class RefrofitUtil {

    private static RefrofitUtil refrofitUtil;

    private static GeneralService generalService;

    public static RefrofitUtil getInstance(Context context) {
        if (refrofitUtil == null) {
            synchronized (RefrofitUtil.class) {
                if (refrofitUtil == null) {
                    refrofitUtil = new RefrofitUtil(context);
                }
            }
        }
        return refrofitUtil;
    }

    private RefrofitUtil(Context context) {
        String server_url ="";

//        Log.d("TAG", "RefrofitUtil: server_url= " + server_url);
        if(server_url != null && !"".equals(server_url)) RetrofitCreator.SERVER_URL = server_url;
        Retrofit retrofit = RetrofitCreator.createRetrofit(context);
        if(retrofit == null){
            retrofit = RetrofitCreator.createRetrofit(context);
        }
        generalService = retrofit.create(GeneralService.class);
    }

    /**
     * Retrofit Search url
     * @return
     */

//    public Observable<List<String>> selectList(String search) {
//        return generalService.selectList(search);
//    }
//    public  Observable<String> login(Map<String,String> map) {
//        return generalService.login(map);
//    }
//
//    public  Observable<String> cert(Map<String,String> map) {
//        return generalService.cert(map);
//    }
    public Flowable<ResData> a(String dev) { return generalService.cert(dev); }
    public Flowable<ResData> b(String dev, String body) { return generalService.reqAuth(dev, body); };
    public Flowable<ResData> c(String ver, String dev, String body) { return generalService.reqAuth(ver, dev, body); };
    public Flowable<ResData> d(String dev, String body) { return generalService.emp(dev, body); }
    public Flowable<ResData> e(String dev) { return generalService.noti(dev); }
    public Flowable<ResData> f(String dev) { return generalService.terms(dev); }
    public Flowable<ResData> g(String dev, String body) { return generalService.auth(dev, body); }
    public Flowable<ResData> h(String dev, String body) { return generalService.s_confirm(dev, body); }
    public Flowable<ResData> i(String dev, String body) { return generalService.s_resend(dev, body); }
    public Flowable<ResData> j(String dev, String body) { return generalService.c_resend(dev, body); }
    public Flowable<ResData> k(String dev, String body) { return generalService.c_confirm(dev, body); }
    public Flowable<ResData> l(String ver, String dev, String authtok, String body) { return generalService.u_register(ver, dev, authtok, body); }
    public Flowable<ResData> m(String ver, String dev, String authtok, String body) { return generalService.f_register(ver, dev, authtok, body); }
    public Flowable<ResData> o(String ver, String dev, String authtok, String body) { return generalService.a_emp(ver, dev, authtok, body); }
    public Flowable<ResData> p(String ver, String dev, String authtok, String body) { return generalService.a_face(ver, dev, authtok, body); }
    public Flowable<ResData> q(String ver, String dev, String body) { return generalServic.a_rslt(ver, dev, body); }
    public Flowable<ResData> r(String ver, String dev, String body) { return generalService.a_misRecog(ver, dev, body); }
    public Flowable<ResData> s(String dev) { return generalService.healthChk(dev); }
}
