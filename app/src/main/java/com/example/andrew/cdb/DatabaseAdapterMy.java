package com.example.andrew.cdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAdapterMy {

    private DatabaseHelperLibrary dbHelper;
    private SQLiteDatabase database;

    public DatabaseAdapterMy(Context context){
        dbHelper = new DatabaseHelperLibrary(context.getApplicationContext());
    }

    public DatabaseAdapterMy open(){
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dbHelper.close();
    }

    private Cursor getAllEntries(){
        String[] columns = new String[]{DatabaseHelperMy.COLUMN_ID, DatabaseHelperMy.COLUMN_IMAGE, DatabaseHelperMy.COLUMN_NAME,
                DatabaseHelperMy.COLUMN_NUMBER, DatabaseHelperMy.COLUMN_WRITER, DatabaseHelperMy.COLUMN_ARTIST, DatabaseHelperMy.COLUMN_PUBLISHER};
        return database.query(DatabaseHelperMy.TABLE, columns, null, null, null, null, null);
    }

    public List<ComicLibrary> getLibrary(){
        ArrayList<ComicLibrary> library = new ArrayList<>();
        Cursor cursor = getAllEntries();
        if (cursor.moveToFirst()){
            do {
                int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelperMy.COLUMN_ID));
                String image = cursor.getString(cursor.getColumnIndex(DatabaseHelperMy.COLUMN_IMAGE));
                String name = cursor.getString(cursor.getColumnIndex(DatabaseHelperMy.COLUMN_NAME));
                String number = cursor.getString(cursor.getColumnIndex(DatabaseHelperMy.COLUMN_NUMBER));
                String writer = cursor.getString(cursor.getColumnIndex(DatabaseHelperMy.COLUMN_WRITER));
                String artist = cursor.getString(cursor.getColumnIndex(DatabaseHelperMy.COLUMN_ARTIST));
                String publisher = cursor.getString(cursor.getColumnIndex(DatabaseHelperMy.COLUMN_PUBLISHER));
                library.add(new ComicLibrary(id,image,name,number,writer,artist,publisher));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return library;
    }

    public long getCount(){
        return DatabaseUtils.queryNumEntries(database, DatabaseHelperMy.TABLE);
    }

    public ComicLibrary getComic(long id){
        ComicLibrary library = null;
        String query = String.format("SELECT * FROM %s WHERE %s=?", DatabaseHelperMy.TABLE, DatabaseHelperMy.COLUMN_ID);
        Cursor cursor = database.rawQuery(query, new String[]{ String.valueOf(id)});
        if (cursor.moveToFirst()){
            String image = cursor.getString(cursor.getColumnIndex(DatabaseHelperMy.COLUMN_IMAGE));
            String name = cursor.getString(cursor.getColumnIndex(DatabaseHelperMy.COLUMN_NAME));
            String number = cursor.getString(cursor.getColumnIndex(DatabaseHelperMy.COLUMN_NUMBER));
            String writer = cursor.getString(cursor.getColumnIndex(DatabaseHelperMy.COLUMN_WRITER));
            String artist = cursor.getString(cursor.getColumnIndex(DatabaseHelperMy.COLUMN_ARTIST));
            String publisher = cursor.getString(cursor.getColumnIndex(DatabaseHelperMy.COLUMN_PUBLISHER));
            library = new ComicLibrary(id,image,name,number,writer,artist,publisher);
        }
        cursor.close();
        return library;
    }

    public long insert(ComicLibrary comicLibrary){
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelperMy.COLUMN_IMAGE, comicLibrary.getImage());
        cv.put(DatabaseHelperMy.COLUMN_NAME, comicLibrary.getName());
        cv.put(DatabaseHelperMy.COLUMN_NUMBER, comicLibrary.getNumber());
        cv.put(DatabaseHelperMy.COLUMN_WRITER, comicLibrary.getWriter());
        cv.put(DatabaseHelperMy.COLUMN_ARTIST, comicLibrary.getArtist());
        cv.put(DatabaseHelperMy.COLUMN_PUBLISHER, comicLibrary.getPublisher());

        return database.insert(DatabaseHelperMy.TABLE, null, cv);
    }

    public long delete(long ComicID){
        String whereClause = "_id = ?";
        String[] whereArgs = new String[]{String.valueOf(ComicID)};
        return database.delete(DatabaseHelperMy.TABLE, whereClause, whereArgs);
    }

    public long update(ComicLibrary comicLibrary){
        String whereClause = DatabaseHelperMy.COLUMN_ID + "=" + String.valueOf(comicLibrary.getId());
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelperMy.COLUMN_IMAGE, comicLibrary.getImage());
        cv.put(DatabaseHelperMy.COLUMN_NAME, comicLibrary.getName());
        cv.put(DatabaseHelperMy.COLUMN_NUMBER, comicLibrary.getNumber());
        cv.put(DatabaseHelperMy.COLUMN_WRITER, comicLibrary.getWriter());
        cv.put(DatabaseHelperMy.COLUMN_ARTIST, comicLibrary.getArtist());
        cv.put(DatabaseHelperMy.COLUMN_PUBLISHER, comicLibrary.getPublisher());
        return database.update(DatabaseHelperMy.TABLE, cv, whereClause, null);
    }
}
