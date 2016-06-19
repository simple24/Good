package com.simplexu.good.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.simplexu.good.model.bean.CollectBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Simple
 */
public class CollectDB {

    private DBHelper dbHelper;
    private SQLiteDatabase db;
    private static  CollectDB mCollectDB;

    private String[] allColums = {DBHelper.COLUMN_ID,DBHelper.COLUMN_COLLECT_URL,DBHelper.COLUMN_COLLECT_DESC,
            DBHelper.COLUMN_COLLECT_WHO,DBHelper.COLUMN_COLLECT_TIME,DBHelper.COLUMN_COLLECT_TYPE};

    private CollectDB(Context context) {
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public synchronized static CollectDB getInstance(Context context) {
        if (mCollectDB == null) {
            mCollectDB = new CollectDB(context);
        }
        return mCollectDB;
    }

    public void saveCollect(CollectBean collectBean){
        if (collectBean != null){
            ContentValues values = new ContentValues();
            values.put(DBHelper.COLUMN_COLLECT_URL,collectBean.getUrl());
            values.put(DBHelper.COLUMN_COLLECT_DESC,collectBean.getDesc());
            values.put(DBHelper.COLUMN_COLLECT_WHO,collectBean.getWho());
            values.put(DBHelper.COLUMN_COLLECT_TIME,collectBean.getPublishedAt());
            values.put(DBHelper.COLUMN_COLLECT_TYPE,collectBean.getType());
            db.insert(DBHelper.TABLE_NAME, null, values);
        }
    }

    public void deleteFavourite(CollectBean collectBean) {
        if (collectBean != null) {
            db.delete(DBHelper.TABLE_NAME, DBHelper.COLUMN_COLLECT_URL + " = ?",
                    new String[]{collectBean.getUrl() + ""});
        }
    }

    public boolean isFavourite(CollectBean collectBean) {
        Cursor cursor = db.query(DBHelper.TABLE_NAME, null, DBHelper.COLUMN_COLLECT_URL + " = ?",
                new String[]{collectBean.getUrl() + ""}, null, null, null);
        if (cursor.moveToNext()) {
            cursor.close();
            return true;
        } else {
            return false;
        }
    }

    public List<CollectBean> loadCollect(){
        List<CollectBean> collectBeanList = new ArrayList<>();
        Cursor cursor = db.query(DBHelper.TABLE_NAME, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
               CollectBean collectBean = new CollectBean();
                collectBean.setUrl(cursor.getString(1));
                collectBean.setDesc(cursor.getString(2));
                collectBean.setWho(cursor.getString(3));
                collectBean.setPublishedAt(cursor.getString(4));
                collectBean.setType(cursor.getString(5));

                collectBeanList.add(collectBean);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return collectBeanList;
    }

    public synchronized void closeDB() {
        if (mCollectDB!= null) {
            db.close();
        }
    }
}

