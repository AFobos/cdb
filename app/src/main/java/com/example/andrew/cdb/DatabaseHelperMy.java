package com.example.andrew.cdb;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.content.ContentValues;

public class DatabaseHelperMy extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "my.db";
    private static final int SCHEMA = 1;
    static final String TABLE = "my";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_NUMBER = "number";
    public static final String COLUMN_WRITER = "writer";
    public static final String COLUMN_ARTIST = "artist";
    public static final String COLUMN_PUBLISHER = "publisher";

    public DatabaseHelperMy(Context context){
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE library (" + COLUMN_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_IMAGE
                + " TEXT, " + COLUMN_NAME + " TEXT, " + COLUMN_NUMBER + " TEXT, "
                + COLUMN_WRITER + " TEXT, " + COLUMN_ARTIST + " TEXT, " + COLUMN_PUBLISHER + " TEXT);");

        db.execSQL("INSERT INTO " + TABLE + " (" + COLUMN_IMAGE + ", " + COLUMN_NAME + ", " + COLUMN_NUMBER
                + ", " + COLUMN_WRITER + ", " + COLUMN_ARTIST + ", " + COLUMN_PUBLISHER + ") VALUES ('0', '0', '0', '0', '0', '0');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+TABLE);
        onCreate(db);
    }
}