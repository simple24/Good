package com.simplexu.good.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Simple on 2016/6/4.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "db_data.db";
    public static final String TABLE_NAME = "tb_data";
    public static final int DB_VERSION = 1;
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_COLLECT_URL = "Url";
    public static final String COLUMN_COLLECT_DESC = "Desc";
    public static final String COLUMN_COLLECT_WHO = "Who";
    public static final String COLUMN_COLLECT_TIME = "PublishedAt";
    public static final String COLUMN_COLLECT_TYPE = "Type";

    public static final String DATABASE_CREATE
            = "CREATE TABLE " + TABLE_NAME
            + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_COLLECT_URL + " INTEGER UNIQUE, "
            + COLUMN_COLLECT_DESC + " TEXT, "
            + COLUMN_COLLECT_WHO + " TEXT,"
            + COLUMN_COLLECT_TIME + " TEXT,"
            + COLUMN_COLLECT_TYPE + " TEXT);";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
