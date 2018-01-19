package com.example.laurentgander.physiodatavisualization;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.Date;

/**
 * Created by laurent.gander on 06.01.2018.
 */

public class CallReceiver extends PhoneCallReceiver{
    private LogRepository logRepository;
    private SQLiteDatabase database;
    private DatabaseLog db;


    @Override
    protected void onIncomingCallStarted(Context context, Date start) {
        db = new DatabaseLog(context);
        database = db.getWritableDatabase();
        logRepository = new LogRepository(database);
        logRepository.create(new Log("Appel entrant", start));
    }

    @Override
    protected void onOutgoingCallStarted(Context context, Date start) {
        db = new DatabaseLog(context);
        database = db.getWritableDatabase();
        logRepository = new LogRepository(database);
        logRepository.create(new Log("Appel sortant", start));

    }


    @Override
    protected void onMissedCall(Context context, Date start) {
        db = new DatabaseLog(context);
        database = db.getWritableDatabase();
        logRepository = new LogRepository(database);
        logRepository.create(new Log("Appel manqué", start));
    }
}
