package com.example.laurentgander.physiodatavisualization.Activity;

import android.Manifest;
import android.app.Activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import android.widget.TextView;

import com.example.laurentgander.physiodatavisualization.Measures.Measures;
import com.example.laurentgander.physiodatavisualization.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by laurent.gander on 28.12.2017.
 */

public class MeasuresDetailActivity extends AppCompatActivity{
    private TextView cenestesisIndex;
    private TextView heartBeat;
    private TextView date;
    private TextView heure;
    private TextView stressIndex;
    private TextView sommeil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED)
        {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, 1);
        }
        setContentView(R.layout.measures_details_activity);
        retrieveViews(  );
        setUpViews( this );
    }

    private void retrieveViews() {
        cenestesisIndex = (TextView)findViewById(R.id.cenestecis_index);
        heartBeat = (TextView)findViewById(R.id.heart_beat);
        date = (TextView)findViewById(R.id.date);
        heure = (TextView)findViewById(R.id.hour);
        stressIndex = (TextView)findViewById(R.id.stress_index);
        sommeil = (TextView)findViewById( R.id.hour_of_sleep );
    }

    private void setUpViews(Activity activity) {

        Intent i = getIntent();
        Measures measure = (Measures)getIntent().getSerializableExtra( "Measure" );

        updateDetails( measure );

    }
    public void updateDetails(Measures measure) {
        if (measure == null){
            cenestesisIndex.setText("");
            heartBeat.setText("");
            stressIndex.setText("");
            date.setText("");
            heure.setText("");
            sommeil.setText( "" );

        }else{
            cenestesisIndex.setText(Integer.toString(measure.getCenestesisIndex()));
            heartBeat.setText( Integer.toString( measure.getHeartBeat()));
            stressIndex.setText( Integer.toString(measure.getStressIndex() ));
            date.setText( measure.getDate().substring( 0,10 ));
            heure.setText( measure.getDate().substring( 11, 16) );
            sommeil.setText( Integer.toString(measure.getSleepingHours() ));
        }
    }




}
