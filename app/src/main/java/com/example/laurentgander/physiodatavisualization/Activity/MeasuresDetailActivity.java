package com.example.laurentgander.physiodatavisualization.Activity;

import android.os.Bundle;

import com.example.laurentgander.physiodatavisualization.MainActivity;
import com.example.laurentgander.physiodatavisualization.R;

/**
 * Created by laurent.gander on 28.12.2017.
 */

public class MeasuresDetailActivity extends MainActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.measures_details_activity);
    }

    @Override
    public void onMeasureServiceConnected() {
        // Notify fragments that our activity is connected with the service
        notifyFragmentConnected(R.id.measure_details_fragment);
    }
}
