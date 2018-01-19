package com.example.laurentgander.physiodatavisualization;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by laurent.gander on 06.01.2018.
 */

public class SMSReceiver extends PhoneSMSReceiver {

    private LogRepository logRepository;
    private SQLiteDatabase database;
    private DatabaseLog db;
    @Override
    protected void onIncomingSMS(Context context, Date date) {
        db = new DatabaseLog(context);
        database = db.getWritableDatabase();
        logRepository = new LogRepository(database);
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.FRANCE);

        try {
            logRepository.create(new Log("SMS entrant",df.parse(date.toString())));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
