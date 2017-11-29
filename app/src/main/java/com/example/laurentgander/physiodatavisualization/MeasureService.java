package com.example.laurentgander.physiodatavisualization;

import android.app.Service;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.icu.util.Measure;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

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

        DataBaseMeasures dataBaseMeasures = new DataBaseMeasures(this);

        database = dataBaseMeasures.getWritableDatabase();
        measureRepository = new MeasureRepository(database);
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        database.close();

        return super.onUnbind(intent);
    }

    public List<Measures> findMeasures(){return measureRepository.getAllListMeasures();}

    public Measures findMeasure(String date){return measureRepository.getMeasuresByDate(date);}

    public Measures createMeasure(Measures measure)
    {
        measure = measureRepository.create(measure);

        Intent intent = new Intent(ACTION_CREATE_MEASURE);

        sendBroadcast(intent);

        return measure;
    }

    public class LocalBinder extends Binder {
        public MeasureService getService(){return MeasureService.this;}
    }
}
