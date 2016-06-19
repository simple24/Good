package com.simplexu.good.presenter.impl;

import android.util.Log;

import com.simplexu.good.model.bean.GoodBean;
import com.simplexu.good.model.impl.HomeModelImpl;
import com.simplexu.good.model.listener.GoodOnListener;
import com.simplexu.good.presenter.HomePresenter;
import com.simplexu.good.ui.view.IHomeView;

import java.util.List;

/**
 * Created by Simple
 */
public class HomePresenterImpl implements HomePresenter,GoodOnListener{

    IHomeView mIHomeView;
    HomeModelImpl mHomeModelImpl;

    public HomePresenterImpl(IHomeView mIHomeView) {
        this.mIHomeView = mIHomeView;
        this.mHomeModelImpl = new HomeModelImpl(this);
    }

    @Override
    public void getData(String type,int count,int page) {

        //mIHomeView.showProgress();
        mHomeModelImpl.loadGood(type,count,page);
    }

    @Override
    public void onSuccess(List<GoodBean.Result> results) {
        mIHomeView.hideProgress();
        mIHomeView.addDataView(results);
    }

    @Override
    public void onFail(String e) {
        mIHomeView.showError();

        Log.i("MainActivity",e);
    }


}
