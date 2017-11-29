package com.example.laurentgander.physiodatavisualization.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.laurentgander.physiodatavisualization.DataBaseMeasures;
import com.example.laurentgander.physiodatavisualization.MeasureService;
import com.example.laurentgander.physiodatavisualization.Measures;
import com.example.laurentgander.physiodatavisualization.R;

import java.util.Date;

/**
 * Created by laurent.gander on 17/11/2017.
 */

public class MeasuresDetailFragment extends Fragment {

    private TextView measuresDetailTextView;
    private DataBaseMeasures db;
    private Cursor cursor;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.measures_detail_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        retrieveViews(getView());
        setUpViews(getActivity());
    }

    private void retrieveViews(View view) {
        measuresDetailTextView = (TextView)view.findViewById(R.id.measureDetailsTextView);
    }

    private void setUpViews(FragmentActivity activity) {
        MeasureService measureService = new MeasureService();
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                if ("DATA_ACTION".equals(intent.getAction()) == true) {
                    String measureDate = intent.getStringExtra("DATA_EXTRA");

                    Measures measures = (measureDate == null)
                    ? null
                    : measureService.findMeasure(measureDate);
                    updateDetails(measures);
                }

            }
        };

    }
    public void updateDetails(Measures measures) {
        if (measures == null){
            measuresDetailTextView.setText("");
        }else{
            measuresDetailTextView.setText(
                String.format(1 + " " + 2,
                        measures.getCenestesisIndex(),
                        measures.getHeartBeat()
                )
            );
        }
    }

}
