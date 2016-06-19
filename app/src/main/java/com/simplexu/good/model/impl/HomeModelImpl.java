package com.simplexu.good.model.impl;

import com.simplexu.good.model.IHomeModel;
import com.simplexu.good.model.bean.GoodBean;
import com.simplexu.good.model.listener.GoodOnListener;
import com.simplexu.good.utils.GoodUtils;

import java.util.List;

import rx.functions.Action1;

/**
 * Created by Simple
 */
public class HomeModelImpl implements IHomeModel{

    private Action1<GoodBean> mAction1;
    private Action1<Throwable> mAction2;
    private List<GoodBean.Result> results;
    private GoodOnListener mGoodOnListener;

    public HomeModelImpl(GoodOnListener mGoodOnListener) {
        this.mGoodOnListener = mGoodOnListener;
    }

    @Override
    public void loadGood(String type,int count,int page) {

        mAction1 = new Action1<GoodBean>() {
            @Override
            public void call(GoodBean goodBean) {
                results = goodBean.getResults();
                mGoodOnListener.onSuccess(results);
            }
        };

        mAction2 = new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                mGoodOnListener.onFail(throwable.toString());
            }
        };

        GoodUtils.getInstance().getGood(mAction1,mAction2,type,count,page);

    }
}
