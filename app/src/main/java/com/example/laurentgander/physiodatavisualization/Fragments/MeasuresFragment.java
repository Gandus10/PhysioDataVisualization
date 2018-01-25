package com.example.laurentgander.physiodatavisualization.Fragments;

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

import com.example.laurentgander.physiodatavisualization.HeartBeat.PhysioDataActivity;
import com.example.laurentgander.physiodatavisualization.Log.Log;
import com.example.laurentgander.physiodatavisualization.MainActivity;
import com.example.laurentgander.physiodatavisualization.Measures.Measures;
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
    private int index;
    private int sommeil;
    private String comment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            index = savedInstanceState.getInt( "index" );
            sommeil = savedInstanceState.getInt( "sommeil" );
            comment = savedInstanceState.getString( "comment" );
            position = savedInstanceState.getInt( "position" );
        }
        View view = inflater.inflate(R.layout.measure_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            index = savedInstanceState.getInt( "index" );
            sommeil = savedInstanceState.getInt( "sommeil" );
            comment = savedInstanceState.getString( "comment" );
            position = savedInstanceState.getInt( "position" );
        }
        retrieveViews(getView());
        setUpViews(getView(), (MainActivity)getActivity());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("index", seekBarIndex.getProgress());
        outState.putInt("sommeil", seekBarSommeil.getProgress());
        outState.putString( "comment", comments.toString() );
        outState.putInt( "position", position );
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
                if (seekBarIndex.getProgress() != 0 && seekBarSommeil.getProgress() != 0 && position != null && comments.toString() != null)
                {
                    final Intent intent = new Intent( getContext(), PhysioDataActivity.class);
                    intent.putExtra("Index", seekBarIndex.getProgress() );
                    intent.putExtra("Sommeil", seekBarSommeil.getProgress() );
                    intent.putExtra("Position", position );
                    intent.putExtra( "Comment", comments.toString() );
                    LocalBroadcastManager.getInstance(activity).sendBroadcast(intent);
                    startActivityForResult( intent, 1);
                }
                else
                {
                    Toast.makeText( getContext(), "please enter all the fields before starting the measurements",Toast.LENGTH_SHORT );
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult( requestCode,resultCode,data );
        Date date = new Date();
        if(1 == resultCode)
        {
            index = data.getIntExtra( "Index", 0 );
            sommeil = data.getIntExtra( "Sommeil", 0 );
            position = data.getIntExtra( "Position",0 );
            comment = data.getStringExtra( "Comment" );
            avgHeartBeat = data.getIntExtra( "HEART_BEAT", 0 );
            int stressIndex = calculStressIndex(index, sommeil, avgHeartBeat);
            ((MainActivity)getActivity()).getService().createMeasure(index, sommeil, position, comment, avgHeartBeat, stressIndex, date);
        }
    }

    private int calculStressIndex(int index, int sommeil, int heartbeat) {
        //TODO Améliorer l'algo
        return (sommeil * 3 + (index * 4)/100 + heartbeat * 5)/12;
    }

    @Override
    public void onMeasureServiceConnected() {}

    @Override
    public void onMeasureServiceDisconnected() {

    }

}
