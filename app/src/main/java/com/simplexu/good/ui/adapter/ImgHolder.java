package com.simplexu.good.ui.adapter;

import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.simplexu.good.R;
import com.simplexu.good.model.GoodBean;
import com.squareup.picasso.Picasso;

/**
 * Created by Simple on 2016/5/28.
 */
public class ImgHolder extends BaseViewHolder<GoodBean.Result>{

    private ImageView mImageView;

    public ImgHolder(ViewGroup parent) {
        super(parent, R.layout.item_img_home);
        mImageView = $(R.id.img_home);
    }

    @Override
    public void setData(GoodBean.Result data) {
        super.setData(data);
        String path = data.getUrl();
        Log.i("path",path);

        Picasso.with(getContext())
                .load(path)
                .into(mImageView);


        /*DiskLruCache mDiskLruCache = null;
        try {
            File cacheDir = ImgUtils.getDiskCacheDir(getContext(),"bitmap");
            if(!cacheDir.exists()){
                cacheDir.mkdirs();
            }
            int version = ImgUtils.getAppVersion(getContext());
            mDiskLruCache = DiskLruCache.open(cacheDir,version,1,10*1024*1024);

            String key = ImgUtils.hashkeyForDisk(path);
            DiskLruCache.Editor editor = mDiskLruCache.edit(key);
            if (editor != null){
                OutputStream outputStream = editor.newOutputStream(0);
                if (loadImg(path)){
                    editor.commit();
                }else {
                    editor.abort();
                }
                mDiskLruCache.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            String key = ImgUtils.hashkeyForDisk(path);
            DiskLruCache.Snapshot snapshot = mDiskLruCache.get(key);
            if (snapshot != null){
                InputStream inputStream = snapshot.getInputStream(0);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                mImageView.setImageBitmap(bitmap);
            }else {
                loadImg(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }

    /*private boolean loadImg(String path) {
        Picasso.with(getContext())
                .load(path)
                .into(mImageView);
        return true;
    }*/


}
