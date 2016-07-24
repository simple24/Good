package com.simplexu.good.ui.view;

import com.simplexu.good.model.GoodBean;

import java.util.List;

/**
 * Created by Simple on 2016/5/28.
 */
public interface IHomeView {

    void showProgress();

    void addDataView(List<GoodBean.Result> mData);

    void hideProgress();

    void showError();
}
