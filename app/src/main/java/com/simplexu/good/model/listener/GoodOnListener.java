package com.simplexu.good.model.listener;

import com.simplexu.good.model.bean.GoodBean;

import java.util.List;

/**
 * Created by Simple
 */
public interface GoodOnListener {
    void onSuccess(List<GoodBean.Result> results);
    void onFail(String e);
}
