package com.simplexu.good.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.simplexu.good.model.GoodBean;

/**
 * Created by Simple on 2016/5/30.
 */
public class DataAdapter extends RecyclerArrayAdapter<GoodBean.Result>{

    public DataAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new DataHolder(parent);
    }
}
