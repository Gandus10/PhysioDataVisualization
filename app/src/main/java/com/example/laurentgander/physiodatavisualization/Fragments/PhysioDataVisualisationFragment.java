package com.example.laurentgander.physiodatavisualization.Fragments;

import android.support.v4.app.Fragment;

import com.example.laurentgander.physiodatavisualization.Measures.MeasureServiceConnection;

/**
 * Created by laurent.gander on 16.01.2018.
 */

public class PhysioDataVisualisationFragment extends Fragment  implements MeasureServiceConnection{

    @Override
    public void onMeasureServiceConnected() {

    }

    @Override
    public void onMeasureServiceDisconnected() {

    }
}
