package com.simplexu.good.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.simplexu.good.model.bean.CollectBean;

/**
 * Created by Simple on 2016/6/3.
 */
public class CollectAdapter extends RecyclerArrayAdapter<CollectBean> {

    public CollectAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new CollectHolder(parent);
    }


}
