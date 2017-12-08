package com.example.admin.newsinformation.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.admin.newsinformation.model.Article;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 11/13/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "News.db";
    public static final String TABLE_NAME = "News";
    public static final String COLUMN_NAME = "NAME";
    public static final String COLUMN_TITLE = "TITLE";
    public static final String COLUMN_DESCRIPTION = "DESCRIPTION";
    public static final String COLUMN_URL = "URL";
    public static final String COLUMN_PUBLISHED = "PUBLISHED";
    public static final int DATABASE_VERSION = 1;
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_NAME + " TEXT," + COLUMN_TITLE + " TEXT, " + COLUMN_DESCRIPTION + " TEXT, " + COLUMN_URL + " TEXT, " +
                COLUMN_PUBLISHED  + " TEXT)";
             sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override //called whenever we change the database version, not first creation where we originally set it, but if we change it again
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    public long saveArticle(Article article)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, article.getSource().getName());
        contentValues.put(COLUMN_TITLE, article.getTitle());
        contentValues.put(COLUMN_DESCRIPTION, article.getDescription());
        contentValues.put(COLUMN_URL, article.getUrl());
        contentValues.put(COLUMN_PUBLISHED, article.getPublishedAt());

       long row =  db.insert(TABLE_NAME, null, contentValues);  //returns row number, returns -1 if failed
        return row;
    }

    public boolean containsRows()
    {
        boolean containsRows = false;

        SQLiteDatabase database = this.getWritableDatabase();
        String QUERY = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = database.rawQuery(QUERY, null);//because we're doing Select *, I can pass null, if not, i have to have column names for second argument
        //cursor returns each row of data so I have to loop through results

        if(cursor.moveToFirst())
        {
            do {
                containsRows = true;
                break;
            }while(cursor.moveToNext());

        }
        return containsRows;
    }

}
