package com.example.laurentgander.physiodatavisualization;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.util.Measure;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by laurent.gander on 21/11/2017.
 */

class MeasureRepository  implements MeasureSchema{

    private SQLiteDatabase db;


    private MeasureRepository measureRepository;

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
                //SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                //Date date = format.parse(res.getString(7));
                measureList.add(new Measures(res.getInt(1),res.getInt(2),res.getInt(3), res.getString(4), res.getInt(5), res.getInt(6), null));
            }


        }
        res.close();
        return measureList;
    }

    public Measures getMeasuresByDate(String date)
    {
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE DATE = " + date, null);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date dateMeasure = null;
        try {
            dateMeasure = format.parse(res.getString(7));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Measures measures = new Measures(res.getInt(1),res.getInt(2),res.getInt(3), res.getString(4), res.getInt(5), res.getInt(6), dateMeasure);
        return measures;
    }

    public Measures create(Measures measures){
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMNS_2, measures.getCenestesisIndex());
        contentValues.put(COLUMNS_3,measures.getSleepingHours());
        contentValues.put(COLUMNS_4,measures.getPosition());
        contentValues.put(COLUMNS_5,measures.getComments());
        contentValues.put(COLUMNS_6, measures.getHeartBeat());
        contentValues.put(COLUMNS_7, measures.getStressIndex());
        contentValues.put(COLUMNS_8,measures.getDate().getTime());

        long measureId = db.insert(TABLE_NAME, null, contentValues);

        measures.setId(measureId);

        return measures;
    }

    public boolean insertData(Integer cynestesisIndex, Integer hoursSleep, Integer position, String comments, Date date){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMNS_2,cynestesisIndex);
        contentValues.put(COLUMNS_3,hoursSleep);
        contentValues.put(COLUMNS_4,position);
        contentValues.put(COLUMNS_5,comments);
        contentValues.put(COLUMNS_6, 0);
        contentValues.put(COLUMNS_7, 2);
        contentValues.put(COLUMNS_8,date.getTime());
        long result = db.insert(TABLE_NAME,null,contentValues);
        if(result == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}
