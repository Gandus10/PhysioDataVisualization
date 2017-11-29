package com.example.laurentgander.physiodatavisualization.fragments;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.laurentgander.physiodatavisualization.DataBaseMeasures;
import com.example.laurentgander.physiodatavisualization.MainActivity;
import com.example.laurentgander.physiodatavisualization.MeasureService;
import com.example.laurentgander.physiodatavisualization.MeasureServiceConnection;
import com.example.laurentgander.physiodatavisualization.Measures;
import com.example.laurentgander.physiodatavisualization.R;

import java.io.File;
import java.util.Date;

/**
 * Created by laurent.gander on 03/11/2017.
 */

public class MeasuresFragment extends Fragment  implements MeasureServiceConnection{

    private Integer position;
    private SeekBar seekBarIndex, seekBarSommeil;
    private ImageButton imageButtonAssis, imageButtonCouche, imageButtonDebout;
    private Button buttonStart;
    private TextView comments;
    private MeasureService measureService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mesures_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        measureService = new MeasureService();
        retrieveViews(getView());

    }

    private void retrieveViews(View view) {
        seekBarIndex = (SeekBar) view.findViewById(R.id.seekBarCenestecisIndex);
        seekBarSommeil = (SeekBar) view.findViewById(R.id.seekBarHeureSommeil);
        imageButtonAssis = (ImageButton) view.findViewById(R.id.imageButtonAssis);
        imageButtonCouche = (ImageButton) view.findViewById(R.id.imageButtonCouche);
        imageButtonDebout = (ImageButton) view.findViewById(R.id.imageButtonDebout);
        comments = (EditText) view.findViewById(R.id.comments);
        buttonStart = (Button) view.findViewById(R.id.buttonStart);
    }

    private void setUpViews(View view, MainActivity activity) {
        imageButtonCouche.setOnClickListener(v -> position = 1);
        imageButtonAssis.setOnClickListener(v -> position = 2);
        imageButtonDebout.setOnClickListener(v -> position = 3);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(getContext(),MeasureService.class);
                activity.getService().createMeasure(new Measures(seekBarIndex.getProgress(), seekBarSommeil.getProgress(), position, comments.toString(), 0,0,new Date()));
                //measureService.createMeasure(new Measures(seekBarIndex.getProgress(), seekBarSommeil.getProgress(), position, comments.toString(), 0,0,new Date()));
            }
        });
    }

    @Override
    public void onMeasureServiceConnected() {setUpViews(getView(), (MainActivity)getActivity());}

    @Override
    public void onMeasureServiceDisconnected() {

    }
}
