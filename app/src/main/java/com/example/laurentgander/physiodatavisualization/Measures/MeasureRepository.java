package com.example.laurentgander.physiodatavisualization.Measures;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.DateFormat;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by laurent.gander on 21/11/2017.
 */

class MeasureRepository implements MeasureSchema{

    private static SQLiteDatabase db;

    public MeasureRepository(SQLiteDatabase database) {
        this.db = database;
    }


    public List<Measures> getAllListMeasures()
    {
        List<Measures> measureList = new ArrayList<Measures>();
        Cursor res = db.query(TABLE_NAME, tabColumns, null, null, null, null, COLUMNS_1);
        if (res != null)
        {
            res.moveToFirst();
            while(!res.isAfterLast())
            {
                String date = res.getString(7) + " " + res.getString( 8 );
                measureList.add(new Measures(res.getInt(1),res.getInt(2),res.getInt(3), res.getString(4), res.getInt(5), res.getInt(6), date));
                res.moveToNext();
            }
        }
        res.close();
        return measureList;
    }

    public void create(int progress, int seekBarSommeilProgress, Integer position, String comment, int heartBeat, int stressIndex, Date date){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMNS_2, progress);
        contentValues.put(COLUMNS_3,seekBarSommeilProgress);
        contentValues.put(COLUMNS_4,position);
        contentValues.put(COLUMNS_5,comment);
        contentValues.put(COLUMNS_6, heartBeat);
        contentValues.put(COLUMNS_7, stressIndex);
        contentValues.put(COLUMNS_8, DateFormat.format("yyyy.MM.dd",date).toString());
        contentValues.put(COLUMNS_9, DateFormat.format( "HH:mm:ss", date).toString());

        long measureId = db.insert( TABLE_NAME, null, contentValues );

    }
}
