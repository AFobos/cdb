package com.example.andrew.cdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAdapterLibrary {

    private DatabaseHelperLibrary dbHelper;
    private SQLiteDatabase database;
    private Cursor cursor;

    public DatabaseAdapterLibrary(Context context){
        dbHelper = new DatabaseHelperLibrary(context.getApplicationContext());
    }

    public DatabaseAdapterLibrary open(){
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dbHelper.close();
    }

    private Cursor getAllEntries(){
        String[] columns = new String[]{DatabaseHelperLibrary.COLUMN_ID, DatabaseHelperLibrary.COLUMN_IMAGE, DatabaseHelperLibrary.COLUMN_NAME,
        DatabaseHelperLibrary.COLUMN_NUMBER, DatabaseHelperLibrary.COLUMN_WRITER, DatabaseHelperLibrary.COLUMN_ARTIST, DatabaseHelperLibrary.COLUMN_PUBLISHER};
        return database.query(DatabaseHelperLibrary.TABLE, columns, null, null, null, null, null);
    }

    public List<ComicLibrary> getLibrary(){
        ArrayList<ComicLibrary> library = new ArrayList<>();
        cursor = getAllEntries();
        if (cursor.moveToFirst()){
            do {
                int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelperLibrary.COLUMN_ID));
                String image = cursor.getString(cursor.getColumnIndex(DatabaseHelperLibrary.COLUMN_IMAGE));
                String name = cursor.getString(cursor.getColumnIndex(DatabaseHelperLibrary.COLUMN_NAME));
                String number = cursor.getString(cursor.getColumnIndex(DatabaseHelperLibrary.COLUMN_NUMBER));
                String writer = cursor.getString(cursor.getColumnIndex(DatabaseHelperLibrary.COLUMN_WRITER));
                String artist = cursor.getString(cursor.getColumnIndex(DatabaseHelperLibrary.COLUMN_ARTIST));
                String publisher = cursor.getString(cursor.getColumnIndex(DatabaseHelperLibrary.COLUMN_PUBLISHER));
                library.add(new ComicLibrary(id,image,name,number,writer,artist,publisher));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return library;
    }

    public long getCount(){
        return DatabaseUtils.queryNumEntries(database, DatabaseHelperLibrary.TABLE);
    }

    public ComicLibrary getComic(long id){
        ComicLibrary library = null;
        String query = String.format("SELECT * FROM %s WHERE %s=?", DatabaseHelperLibrary.TABLE, DatabaseHelperLibrary.COLUMN_ID);
        cursor = database.rawQuery(query, new String[]{ String.valueOf(id)});
        if (cursor.moveToFirst()){
                String image = cursor.getString(cursor.getColumnIndex(DatabaseHelperLibrary.COLUMN_IMAGE));
                String name = cursor.getString(cursor.getColumnIndex(DatabaseHelperLibrary.COLUMN_NAME));
                String number = cursor.getString(cursor.getColumnIndex(DatabaseHelperLibrary.COLUMN_NUMBER));
                String writer = cursor.getString(cursor.getColumnIndex(DatabaseHelperLibrary.COLUMN_WRITER));
                String artist = cursor.getString(cursor.getColumnIndex(DatabaseHelperLibrary.COLUMN_ARTIST));
                String publisher = cursor.getString(cursor.getColumnIndex(DatabaseHelperLibrary.COLUMN_PUBLISHER));
                library = new ComicLibrary(id,image,name,number,writer,artist,publisher);
        }
        cursor.close();
        return library;
    }

    public long insert(ComicLibrary comicLibrary){
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelperLibrary.COLUMN_IMAGE, comicLibrary.getImage());
        cv.put(DatabaseHelperLibrary.COLUMN_NAME, comicLibrary.getName());
        cv.put(DatabaseHelperLibrary.COLUMN_NUMBER, comicLibrary.getNumber());
        cv.put(DatabaseHelperLibrary.COLUMN_WRITER, comicLibrary.getWriter());
        cv.put(DatabaseHelperLibrary.COLUMN_ARTIST, comicLibrary.getArtist());
        cv.put(DatabaseHelperLibrary.COLUMN_PUBLISHER, comicLibrary.getPublisher());

        return database.insert(DatabaseHelperLibrary.TABLE, null, cv);
    }

    public long delete(long ComicID){
        String whereClause = "_id = ?";
        String[] whereArgs = new String[]{String.valueOf(ComicID)};
        return database.delete(DatabaseHelperLibrary.TABLE, whereClause, whereArgs);
    }

    public long update(ComicLibrary comicLibrary){
        String whereClause = DatabaseHelperLibrary.COLUMN_ID + "=" + String.valueOf(comicLibrary.getId());
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelperLibrary.COLUMN_IMAGE, comicLibrary.getImage());
        cv.put(DatabaseHelperLibrary.COLUMN_NAME, comicLibrary.getName());
        cv.put(DatabaseHelperLibrary.COLUMN_NUMBER, comicLibrary.getNumber());
        cv.put(DatabaseHelperLibrary.COLUMN_WRITER, comicLibrary.getWriter());
        cv.put(DatabaseHelperLibrary.COLUMN_ARTIST, comicLibrary.getArtist());
        cv.put(DatabaseHelperLibrary.COLUMN_PUBLISHER, comicLibrary.getPublisher());
        return database.update(DatabaseHelperLibrary.TABLE, cv, whereClause, null);
    }
    public List<String> getPublishers(){
        cursor = getAllEntries();
        ArrayList<String> publisher = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                String publ = cursor.getString(cursor.getColumnIndex(DatabaseHelperLibrary.COLUMN_PUBLISHER));
                publisher.add(publ);
            }
            while (cursor.moveToNext());
        }
        return  publisher;
    }
}
