package com.example.laurentgander.physiodatavisualization;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.io.File;

/**
 * Created by laurent.gander on 07/11/2017.
 */

public class DataBaseMeasures extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "dbmeasures.db";

    public DataBaseMeasures(Context context) {

        super( context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( MeasureSchema.CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
