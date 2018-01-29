package com.example.laurentgander.physiodatavisualization.Measures;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by laurent.gander on 07/11/2017.
 */

public class DataBaseMeasures extends SQLiteOpenHelper {

    public static  DataBaseMeasures sInstance;

    public static final String DATABASE_NAME = "measures.db";

    public DataBaseMeasures(Context context) {

        super( context, DATABASE_NAME, null, 9);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( MeasureSchema.CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL( MeasureSchema.DROP_TABLE_QUERY );
        onCreate( db );
    }

    public static synchronized DataBaseMeasures getInstance(Context context)
    {
        if (sInstance == null)
        {
            sInstance = new DataBaseMeasures( context.getApplicationContext() );
        }
        return sInstance;
    }
}
