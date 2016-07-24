package com.simplexu.good.presenter;

import com.simplexu.good.model.GoodBean;
import com.simplexu.good.ui.view.IHomeView;
import com.simplexu.good.utils.GoodUtils;

import java.util.List;

import rx.Subscriber;

/**
 * Created by Simple
 */
public class HomePresenter {

    IHomeView mIHomeView;
    private List<GoodBean.Result> results;

    public HomePresenter(IHomeView mIHomeView) {
        this.mIHomeView = mIHomeView;
    }

    public void getData(String type,int count,int page) {

        Subscriber subscriber = new Subscriber<GoodBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(GoodBean goodBean) {
                results = goodBean.getResults();
                mIHomeView.hideProgress();
                mIHomeView.addDataView(results);
            }
        };

        GoodUtils.getInstance().getGood(subscriber,type,count,page);
    }

}
