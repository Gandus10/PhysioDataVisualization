package com.example.laurentgander.physiodatavisualization.Log;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by laurent.gander on 09.01.2018.
 */

public class DatabaseLog extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "log.db";

    public DatabaseLog(Context context) {

        super(context, DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {db.execSQL(LogSchema.CREATE_TABLE_QUERY);}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
