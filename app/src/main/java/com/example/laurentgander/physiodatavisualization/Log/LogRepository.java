package com.example.laurentgander.physiodatavisualization.Log;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by laurent.gander on 09.01.2018.
 */

public class LogRepository implements LogSchema{
    private SQLiteDatabase db;
    public LogRepository(SQLiteDatabase database) {
        this.db = database;
    }

    public List<Log> getAllLog()
    {
        List<Log> logList = new ArrayList<Log>();
        Cursor res = db.query(TABLE_NAME, tabColumns, null, null, null, null, COLUMNS_1);
        if (res != null)
        {
            res.moveToFirst();
            while(!res.isAfterLast())
            {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date date = format.parse(res.getString(3));
                    logList.add(new Log(res.getString(1),date));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                res.moveToNext();
            }
        }
        res.close();
        return logList;
    }

    public Log create(Log log)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMNS_2, log.getType());
        contentValues.put(COLUMNS_3, DateFormat.format("yyyy-MM-dd",log.getDate()).toString());

        long logId = db.insert(TABLE_NAME,null,contentValues);
        log.setId(logId);

        return log;
    }
}
