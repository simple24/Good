package com.simplexu.good.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.widget.ImageView;

/**
 * 图片保存工具类
 */
public class FileUtils {
	
	private static final String CACHE_DIR = Environment.getExternalStorageDirectory() + "/Good";

	/**
	 * SDCARD是否挂载
	 * @return
	 */
	public static boolean isMounted(){
		String state = Environment.getExternalStorageState();
		return state.equals(Environment.MEDIA_MOUNTED);
	}

	/**
	 * 获取SDCARD的文件的绝对物理路径
	 * @return
	 */
	public static String getSDCARDDIR() {
		return Environment.getExternalStorageDirectory().getAbsolutePath();
	}

	public static String getFilename(String url) {
		return url.substring(url.lastIndexOf("/") + 1);
	}

	/**
	 * 保存图片
	 * @param url
	 * @param bitmap
	 * @throws FileNotFoundException
	 */
	public static void saveImage(Context context,String url, Bitmap bitmap, ImageView imageView) {

		if (!isMounted()) {
			return;
		}

		File dir = new File(CACHE_DIR);
		if (!dir.exists()) {
			dir.mkdirs();
		}

		File picFile = new File(dir, getFilename(url));
		try {
			FileOutputStream outputStream = new FileOutputStream(picFile);

			boolean isCompress = bitmap.compress(CompressFormat.JPEG, 100, outputStream);
			if (isCompress){
				Snackbar.make(imageView,"图片已保存",Snackbar.LENGTH_SHORT).show();
			}else {
				Snackbar.make(imageView,"图片未保存",Snackbar.LENGTH_SHORT).show();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			MediaStore.Images.Media.insertImage(context.getContentResolver(),picFile.getAbsolutePath(),
                    getFilename(url),null);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Uri uri = Uri.fromFile(picFile);
		context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,uri));
	}

}
