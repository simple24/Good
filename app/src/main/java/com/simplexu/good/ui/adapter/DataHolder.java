package com.simplexu.good.ui.adapter;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.simplexu.good.R;
import com.simplexu.good.model.bean.GoodBean;
import com.simplexu.good.utils.TimeUtils;

/**
 * Created by Simple on 2016/5/30.
 */
public class DataHolder extends BaseViewHolder<GoodBean.Result>{

    private TextView mDesc;
    private TextView mWho;
    private TextView mTime;
    private ImageView mImageView;

    public DataHolder(ViewGroup parent) {
        super(parent, R.layout.item_data_home);
        mDesc = $(R.id.tv_desc);
        mWho = $(R.id.tv_who);
        mTime = $(R.id.tv_publishedAt);
        mImageView = $(R.id.iv_head);
    }

    @Override
    public void setData(GoodBean.Result data) {
        super.setData(data);
        mDesc.setText(data.getDesc());
        mWho.setText(data.getWho());
        mTime.setText(TimeUtils.getFormatTime(data.getPublishedAt()));
        if (data.getType().equals("前端")){
            mImageView.setImageResource(R.drawable.img_html_head);
        }else if (data.getType().equals("Android")){
            mImageView.setImageResource(R.drawable.img_android_head);
        }else if (data.getType().equals("iOS")){
            mImageView.setImageResource(R.drawable.img_ios_head);
        }else if (data.getType().equals("休息视频")){
            mImageView.setImageResource(R.drawable.img_relax_head);
        }
    }
}
