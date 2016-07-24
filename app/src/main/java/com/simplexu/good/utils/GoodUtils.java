package com.simplexu.good.utils;

import com.simplexu.good.config.ApiData;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Simple on 2016/5/28.
 */
public class GoodUtils {

    private Retrofit mRetrofit;
    private GoodService mGoodService;

    public GoodUtils() {

        OkHttpClient.Builder mHttpBuilder = new OkHttpClient.Builder();
        mHttpBuilder.connectTimeout(ApiData.DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        mRetrofit = new Retrofit.Builder()
                .client(mHttpBuilder.build())
                .baseUrl(ApiData.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        mGoodService = mRetrofit.create(GoodService.class);
    }

    //访问时创建单例模式
    private static class SingletonHolder{
        private static final GoodUtils INSTANCE = new GoodUtils();
    }

    //获取单例
    public static GoodUtils getInstance(){
        return SingletonHolder.INSTANCE;
    }

    public void getGood(Subscriber subscriber,String type, int count, int page){
        mGoodService.getGood(type, count, page)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
