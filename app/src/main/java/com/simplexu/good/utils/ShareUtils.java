package com.simplexu.good.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by Simple on 2016/6/4.
 */
public class ShareUtils {

    public static void shareText(Context context,String uri){

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");

        intent.putExtra(Intent.EXTRA_TEXT,uri);
        context.startActivity(Intent.createChooser(intent,"文章共享"));
    }

    public static void shareImage(Context context, Uri uri){

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/jpeg");

        intent.putExtra(Intent.EXTRA_STREAM, uri);
        context.startActivity(Intent.createChooser(intent,"美图分享"));
    }
}
