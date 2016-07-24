package com.simplexu.good.ui.adapter;


import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.simplexu.good.model.GoodBean;

/**
 * Created by Simple on 2016/5/28.
 */
public class ImgAdapter extends RecyclerArrayAdapter<GoodBean.Result> {

    public ImgAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ImgHolder(parent);
    }


    /*private List<GoodBean.Result> mData;

    public ImgAdapter(List<GoodBean.Result> mData) {

        this.mData = mData;
    }

    public void addAll(List<GoodBean.Result> mData){
        this.mData = mData;
        notifyDataSetChanged();
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_img_home,parent,false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        String path = mData.get(position).getUrl();
        Log.i("MainActivity",path);
        Uri uri = Uri.parse(path);
        holder.mImageView.setImageURI(uri);
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }



    class MyHolder extends RecyclerView.ViewHolder{

        public SimpleDraweeView mImageView;

        public MyHolder(View itemView) {
            super(itemView);
            mImageView = (SimpleDraweeView) itemView.findViewById(R.id.img_home);
        }
    }*/


}
