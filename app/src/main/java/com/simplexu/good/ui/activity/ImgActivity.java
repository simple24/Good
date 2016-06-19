package com.simplexu.good.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.simplexu.good.R;
import com.simplexu.good.utils.FileUtils;
import com.simplexu.good.utils.ShareUtils;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.FileNotFoundException;

public class ImgActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private ImageView mImageView;

    private String Url;
    private String Desc;

    private Bitmap mBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img);

        Intent intent = getIntent();
        Url = intent.getStringExtra("Url");
        Desc = intent.getStringExtra("Desc");

        initToolbar();
        initImg();
    }

    /**
     * Toolbar的设置
     */
    private void initToolbar() {

        mToolbar = (Toolbar) findViewById(R.id.img_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(Desc);
    }

    /**
     * ImageView的设置
     */
    private void initImg() {

        mImageView = (ImageView) findViewById(R.id.iv_img);
        Picasso.with(this)
                .load(Url)
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        mImageView.setImageBitmap(bitmap);
                        mBitmap = bitmap;
                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_img,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case android.R.id.home:
                finish();
                break;
            case R.id.action_share:
                ShareUtils.shareImage(this, Uri.parse(Url));
                break;
            case R.id.action_save:
                FileUtils.saveImage(this,Url,mBitmap,mImageView);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
