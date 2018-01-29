package com.example.laurentgander.physiodatavisualization.Fragments;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.laurentgander.physiodatavisualization.R;

/**
 * Created by laurent.gander on 01/11/2017.
 */

public class HomeFragment extends PhysioDataVisualisationFragment{
    private TextView textView;
    private ImageView logo;
    private Button button_menu;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        retrieveViews(getView());
        setUpView(getActivity());
    }



    private void retrieveViews(View view) {
        textView = (TextView) view.findViewById( R.id.text_presentation );
        logo = (ImageView) view.findViewById( R.id.home_logo );
        button_menu = (Button) view.findViewById( R.id.bouton_menu );
    }

    private void setUpView(FragmentActivity activity) {
        textView.setText( "Bienvenue sur Physio Data Visualisation" );
        logo.setImageResource( R.drawable.icon_menu );
        button_menu.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MeasuresFragment measuresFragment = new MeasuresFragment();
                getFragmentManager().beginTransaction().replace( R.id.main_fragment, measuresFragment ).commit();
                measuresFragment.onMeasureServiceConnected();
            }
        } );
    }

    @Override
    public void onMeasureServiceConnected() {

    }

    @Override
    public void onMeasureServiceDisconnected() {

    }
}
