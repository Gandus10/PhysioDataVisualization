package com.example.laurentgander.physiodatavisualization.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.laurentgander.physiodatavisualization.Activity.PhysioDataActivity;
import com.example.laurentgander.physiodatavisualization.MainActivity;
import com.example.laurentgander.physiodatavisualization.R;

import java.util.Date;


/**
 * Created by laurent.gander on 03/11/2017.
 */

public class MeasuresFragment extends PhysioDataVisualisationFragment {

    private Integer position;
    private SeekBar seekBarIndex, seekBarSommeil;
    private ImageButton imageButtonAssis, imageButtonCouche, imageButtonDebout;
    private Button buttonStart;
    private TextView comments;
    private int avgHeartBeat;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(Integer.valueOf( getArguments().getInt("Average Beat")) != null)
        {
            avgHeartBeat = getArguments().getInt("Average Beat");
        }
        else{
            avgHeartBeat = 0;
        }
        View view = inflater.inflate(R.layout.measure_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        retrieveViews(getView());
        setUpViews(getView(), (MainActivity)getActivity());
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
                Date date = new Date();
                final Intent intent = new Intent(activity, PhysioDataActivity.class);
                LocalBroadcastManager.getInstance(activity).sendBroadcast(intent);
                startActivity(intent);
                int stressIndex = calculStressIndex(seekBarIndex.getProgress(), seekBarSommeil.getProgress(), avgHeartBeat);
                if (seekBarSommeil.getProgress() != 0 && seekBarSommeil.getProgress() != 0 && position != null && comments.toString() != "")
                {
                    activity.getService().createMeasure(seekBarIndex.getProgress(), seekBarSommeil.getProgress(), position, comments.toString(), avgHeartBeat,stressIndex, date);
                }

                Toast.makeText( getContext(), "please enter all the fields before starting the measurements",Toast.LENGTH_SHORT );
            }
        });
    }

    private int calculStressIndex(int progress, int progress1, int avgHeartBeat) {
        //TODO
        return 0;
    }

    @Override
    public void onMeasureServiceConnected() {}

    @Override
    public void onMeasureServiceDisconnected() {

    }

}
