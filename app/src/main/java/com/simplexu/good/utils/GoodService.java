package com.simplexu.good.utils;

import com.simplexu.good.model.bean.GoodBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Simple on 2016/5/28.
 */
public interface GoodService {

    /**
     * 分类数据: http://gank.io/api/data/数据类型/请求个数/第几页

     * 数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
     * 请求个数： 数字，大于0
     * 第几页：数字，大于
     */

    @GET("api/data/{type}/{count}/{page}")
    Observable<GoodBean> getGood(
            @Path("type") String type,
            @Path("count") int count,
            @Path("page") int page
    );
}
