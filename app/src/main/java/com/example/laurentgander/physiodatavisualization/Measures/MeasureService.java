package com.example.laurentgander.physiodatavisualization.Measures;

import android.app.Service;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.Date;
import java.util.List;

/**
 * Created by laurent.gander on 21/11/2017.
 */

public class MeasureService  extends Service{

    public static final String ACTION_CREATE_MEASURE = "create_measure";

    private final LocalBinder binder = new LocalBinder();

    private SQLiteDatabase database;

    private MeasureRepository measureRepository;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        DataBaseMeasures dataBaseMeasures = DataBaseMeasures.getInstance( this );

        database = dataBaseMeasures.getWritableDatabase();
        measureRepository = new MeasureRepository(database);
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    public List<Measures> findMeasures(){

        return measureRepository.getAllListMeasures();
    }

    public void createMeasure(int progress, int seekBarSommeilProgress, Integer position, String comment, int heartBeat, int stressIndex, Date date)
    {
        measureRepository.create(progress, seekBarSommeilProgress, position, comment, heartBeat ,stressIndex, date);
        Intent intent = new Intent(ACTION_CREATE_MEASURE);
        sendBroadcast(intent);
    }

    public class LocalBinder extends Binder {
        public MeasureService getService(){return MeasureService.this;}
    }
}
